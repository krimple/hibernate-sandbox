package chariot.hibernate.sandbox.querytool;

import groovy.ui.Console;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by krimple @ Sep 14, 2010 10:15:20 PM
 */
public class ConsoleRunner {
    private static ApplicationContext ctx;

    public static void main(String[] args) {
        Console console = new Console();

        ContextWrapper ctxWrapper = new ContextWrapper();

        console.setVariable("ctx", ctxWrapper.getContext());
        console.setVariable("wrapper", ctxWrapper);
        console.setVariable("sessionFactory", ctxWrapper.getSessionFactory());
        console.run();
    }

    
}
