package chariot.hibernate.sandbox.relationships.data.entities.one2manyset;

import chariot.hibernate.sandbox.common.BookRating;
import chariot.hibernate.sandbox.util.RandomReviewGeneratorImpl;
import chariot.hibernate.sandbox.util.ReviewCreator;
import chariot.hibernate.sandbox.util.ReviewGenerator;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by krimple @ Sep 12, 2010 4:25:49 PM
 */
@ContextConfiguration(locations = {"classpath:META-INF/spring/applicationContext*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = false)
public class One2ManySetTest {

    Logger log = Logger.getLogger(getClass());

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ReviewGenerator generator;

    @Test
    @Transactional
    public void testCreateBookAndReviews() {

        Session session = sessionFactory.getCurrentSession();

        Book book = new Book();

        book.setIsbn("12341234");
        book.setTitle("You are getting sleepy...");

        // let's create a few reviews...

        for (int i = 0; i < 10; i++) {

            Review review = (Review) generator.getRandomRating(new ReviewCreator<Review>() {
                public Review createReview(BookRating rating, String reviewText, Date reviewDate) {
                    Review review = new Review();
                    review.setRating(rating);
                    review.setReview(reviewText);
                    review.setReviewDate(reviewDate);
                    return review;
                }
            });

            book.addReview(review);
            session.save(review);


        }           
    }
}
