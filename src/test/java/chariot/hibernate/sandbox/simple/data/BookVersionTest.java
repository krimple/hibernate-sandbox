package chariot.hibernate.sandbox.simple.data;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.StaleObjectStateException;
import org.hibernate.classic.Session;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.assertNotNull;

/**
 * Created by krimple @ Sep 12, 2010 11:40:11 AM
 */

@ContextConfiguration(locations = {"classpath:META-INF/spring/applicationContext*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class BookVersionTest {

    Logger log = Logger.getLogger(getClass());
    
    @Autowired
    private SessionFactory sessionFactory;

    BookWithVersion book;

    @Before
    public void before() {
        book = new BookWithVersion();
        book.setIsbn("2345151334");
        book.setTitle("But am I really here?");
        book.setListPrice(new BigDecimal("120.00"));
        book.setPublicationDate(new Date());

        Session beforeSession = sessionFactory.openSession();
        beforeSession.save(book);
        beforeSession.flush();
        beforeSession.close();
    }

    @Test
    public void testConflict() {

        Session session1 = sessionFactory.openSession();
        Session session2 = sessionFactory.openSession();
        BookWithVersion bookFromSession1 = (BookWithVersion) session1.get(BookWithVersion.class, book.getId());
        BookWithVersion bookFromSession2 = (BookWithVersion) session1.get(BookWithVersion.class, book.getId());

        // user 1 changes
        bookFromSession1.setTitle("It really isn't here");

        // user 2 changes
        bookFromSession2.setTitle("Yes it is");
        session2.update(bookFromSession2);

        // user 2 flushes / closes
        session2.flush();
        session2.close();

        // user 1 tries to save and gets error
        session1.update(bookFromSession1);
        try {
            session1.flush();
        } catch (StaleObjectStateException e) {
            log.trace("Entity with failure: " + e.getEntityName());
            log.trace("ID of failure: " + e.getIdentifier());
            log.trace("Message: " + e.getMessage());
            // clear out data in session
            return;
        } finally {
            session1.close();            
        }

        Assert.fail("Update of book 1 should throw the StaleObjectStateException.");

    }

    @After
    public void cleanup() {
        Session session = sessionFactory.openSession();
        BookWithVersion bookToDelete = (BookWithVersion) session.get(BookWithVersion.class, book.getId());
        book = null;
        session.delete(bookToDelete);

    }
}
