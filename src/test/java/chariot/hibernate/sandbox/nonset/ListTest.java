package chariot.hibernate.sandbox.nonset;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.transaction.TransactionManager;

/**
 * Created by krimple @ Sep 16, 2010 10:10:01 AM
 */

@ContextConfiguration(locations =
        {"classpath:META-INF/spring/applicationContext*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ListTest {

    Logger log = Logger.getLogger(getClass());

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private PlatformTransactionManager txManager;

    private TransactionStatus txStatus;

    void beginTransaction() {
        txStatus = txManager.getTransaction(new DefaultTransactionDefinition());
    }

    void rollbackTransaction() {
        if (txManager != null) txManager.rollback(txStatus);
    }

    void commitTransaction() {
        if (txManager != null) txManager.commit(txStatus);
    }

    @Test
    public void testListInsert() {
        beginTransaction();
        Session session = sessionFactory.openSession();
        Customer customer = new Customer();
        customer.setName("Crazy Eddie");
        session.save(customer);

        for (int i = 1; i < 100; i++) {
            Purchase p = new Purchase();
            p.setQuantity(1+ (int)(Math.random() * 100.0d));
            p.setWidget("Custom Widget #" + (i+1));
            session.save(p);
            customer.addPurchase(p);
        }

        session.update(customer);

        session.flush();

        commitTransaction();

        beginTransaction();
        
        Session session2 = sessionFactory.openSession();
        
        Customer customer2 = (Customer) session.createCriteria(Customer.class)
                .setFetchMode("purchases", FetchMode.JOIN)
                .add(Restrictions.eq("id", customer.getId())).uniqueResult();

        System.err.println(customer2);

        commitTransaction();
    }


}
