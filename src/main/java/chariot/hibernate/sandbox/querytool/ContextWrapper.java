package chariot.hibernate.sandbox.querytool;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by krimple @ Sep 14, 2010 10:35:49 PM
 */
public class ContextWrapper {

    private SessionFactory sessionFactory;

    private Session session;

    private Map<String, Object> bucket;

    public ContextWrapper() {
        reload();
    }

    private ApplicationContext ctx;

    public void reload() {
        ctx = new ClassPathXmlApplicationContext(
            "META-INF/spring/applicationContext*.xml");
        sessionFactory = ctx.getBean("sessionFactory", SessionFactory.class);
        session = sessionFactory.openSession();
        bucket = new HashMap<String, Object>();
    }

    public ApplicationContext getContext() {
        return ctx;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Session newSession() {
        return sessionFactory.openSession();
    }           

    public void put(String key, Object value) {
       bucket.put(key, value);
    }

    public Object get(String key) {
        return bucket.get(key);
    }    
}
