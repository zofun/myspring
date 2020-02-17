import bean.User;
import context.app.ApplicationContext;
import context.app.impl.XmlApplicationContext;
import org.junit.Test;

/**
 * @ClassName : Context
 * @Author : TCW
 * @Date: 2020-02-17 17:52
 */
public class ContextTest {


    @Test
    public void test() throws Exception {

        ApplicationContext context=new XmlApplicationContext("file:F:\\spring.xml");
        User user = (User) context.getBean("user");
        user.sayHello();
    }
}
