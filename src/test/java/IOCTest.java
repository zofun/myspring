import bean.User;
import bean.beanDefinition.impl.DefaultBeanDefinition;
import bean.factory.DefaultBeanFactory;
import org.junit.Test;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class IOCTest {

    static DefaultBeanFactory factory = new DefaultBeanFactory();

    /**
     * 测试构造器依赖注入
     * @throws Exception
     */
    @Test
    public void test() throws Exception {

        DefaultBeanDefinition bd=new DefaultBeanDefinition();
        bd.setBeanName("userA");
        bd.setClazz(User.class);
        bd.setSingleton(true);

        //构造器依赖
        List<Object> args = new LinkedList<>();
        args.add("tcw");
        args.add(22);
        bd.setConstructorArg(args);

        factory.register(bd,bd.getBeanName());


        User user =(User) factory.getBean(bd.getBeanName());
        System.out.println(user.toString());
    }

    /**
     * 测试属性依赖注入
     * @throws Exception
     */
    @Test
    public void testB() throws Exception {
        DefaultBeanDefinition bd=new DefaultBeanDefinition();
        bd.setBeanName("userB");
        bd.setClazz(User.class);
        bd.setSingleton(true);

        //注入属性依赖
        Map<String, Object> values = new HashMap<>();
        values.put("name", "tom");
        values.put("age",15);

        bd.setPropertyKeyValue(values);

        factory.register(bd,bd.getBeanName());

        User user =(User) factory.getBean(bd.getBeanName());
        System.out.println(user.toString());

    }

    @Test
    public void testC() throws Exception {
        DefaultBeanDefinition bd=new DefaultBeanDefinition();
        bd.setBeanName("userC");
        bd.setClazz(User.class);
        bd.setSingleton(true);

        //注入属性依赖
        Map<String, Object> values = new HashMap<>();
        values.put("name", "tom");
        values.put("age",15);
        bd.setPropertyKeyValue(values);

        //设置初始化方法
        bd.setBeanInitMethodName("init");
        factory.register(bd,bd.getBeanName());

        final User user = (User) factory.getBean(bd.getBeanName());
        System.out.println(user.toString());
    }
}
