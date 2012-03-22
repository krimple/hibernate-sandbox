package chariot.hibernate.sandbox.simple.data;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by krimple @ Sep 12, 2010 11:40:11 AM
 */

@ContextConfiguration(locations = {"classpath:META-INF/spring/applicationContext*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = false)
public class SimpleMappingTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    @Transactional
    public void testCreateBook() {

        Session session = sessionFactory.getCurrentSession();

        BookUsingSequence book = new BookUsingSequence();
        book.setIsbn("2345151334");
        book.setTitle("But am I really here?");
        book.setListPrice(new BigDecimal("120.00"));
        book.setPublicationDate(new Date());

        session.save(book);

        assertNotNull(book.getId());
    }
}
