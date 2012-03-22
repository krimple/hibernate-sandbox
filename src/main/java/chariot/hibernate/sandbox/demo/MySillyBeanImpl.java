package chariot.hibernate.sandbox.demo;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by krimple @ Sep 14, 2010 1:12:38 PM
 */

@Service("mySillyBean")  
public class MySillyBeanImpl implements MySillyBean {

    public double foo() {
        return Math.random();
    }
}
