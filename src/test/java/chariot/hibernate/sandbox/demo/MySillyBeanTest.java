package chariot.hibernate.sandbox.demo;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by krimple @ Sep 14, 2010 1:56:19 PM
 */
@ContextConfiguration(locations = {
        "classpath:META-INF/spring/applicationContext.xml"
        ,"classpath:META-INF/spring/applicationContext-hibernate.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MySillyBeanTest {

    @Autowired
    MySillyBean bean;

    @Test
    public void testRandom() {
        MySillyBean mySillyBean = bean;
        double val1 = mySillyBean.foo();
        double val2 = mySillyBean.foo();
        Assert.assertTrue( val2 != val1);
    }
}
