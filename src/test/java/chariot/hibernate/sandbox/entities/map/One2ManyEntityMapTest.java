package chariot.hibernate.sandbox.entities.map;

import chariot.hibernate.sandbox.common.BookRating;
import chariot.hibernate.sandbox.util.ReviewCreator;
import chariot.hibernate.sandbox.util.ReviewGenerator;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by krimple @ Sep 13, 2010 12:19:23 AM
 */
@ContextConfiguration(locations = {"classpath:META-INF/spring/applicationContext*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
public class One2ManyEntityMapTest {

    Logger log = Logger.getLogger(getClass());

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ReviewGenerator generator;

    private Book seedBook;

    @Before
    public void loadProtoBook() {
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();
        seedBook = new Book();
        seedBook.setIsbn("12341234");
        seedBook.setTitle("You are getting sleepy...");

        // let's create a few reviews...


        for (int i = 0; i < 10; i++) {

            Review review = (Review) generator.getRandomRating(new ReviewCreator<Review>() {
                public Review createReview(BookRating rating, String reviewText, Date reviewDate) {
                    Review review = new Review();
                    review.setRating(rating);
                    review.setReview(reviewText);
                    review.setReviewDate(reviewDate);
                    // create a silly natural key (possible to get dupe values but unlikely)
                    review.setRandomVal((long)(Math.random()* 100000000000.0d));
                    return review;
                }
            });
            session.save(review);
            seedBook.addReview(review);

        }

        session.save(seedBook);

        session.flush();
        transaction.commit();
        session.close();
    }

    @Test
    @Transactional 
    public void examineBook() {

        Session session = sessionFactory.getCurrentSession();

        log.warn("Beginning test...");
        Query query = session.createQuery("from Book b where b.id = ?").setLong(0, seedBook.getId());
        Book book = (Book) query.uniqueResult();
        Assert.assertNotNull(book);
        Assert.assertEquals(10, book.getReviews().keySet().size());
        log.debug("Book is: " + book);
        log.debug("Review are : " + book.getReviews());

    }
}
