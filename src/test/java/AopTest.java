import advice.AroundAdvice;
import advice.BeforeAdvice;
import aop.creator.impl.AopProxyCreator;
import aop.advisor.impl.RegexMatchAdvisor;
import aop.pointcut.impl.RegexExpressionPointCutResolver;
import bean.User;
import bean.beanDefinition.impl.DefaultBeanDefinition;
import bean.factory.DefaultBeanFactory;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName : AopTest
 * @Author : TCW
 * @Date: 2020-02-16 16:54
 */
public class AopTest {

    static DefaultBeanFactory factory=new DefaultBeanFactory();
    @Test
    public void test() throws Exception {

        DefaultBeanDefinition bd=new DefaultBeanDefinition();
        bd.setBeanName("user");
        bd.setClazz(User.class);
        bd.setSingleton(true);

        //构造器依赖
        List<Object> args = new LinkedList<>();
        args.add("tcw");
        args.add(22);
        bd.setConstructorArg(args);
        //注入user对象
        factory.register(bd,bd.getBeanName());


        bd = new DefaultBeanDefinition();
        bd.setClazz(BeforeAdvice.class);
        //注入通知
        factory.register(bd, "BeforeAdvice");

        AopProxyCreator aapc = new AopProxyCreator();
        aapc.setBeanFactory(factory);
        // 向AdvisorAutoProxyCreator注册Advisor
        factory.registerBeanPostProcessor(aapc);
        aapc.register(new RegexMatchAdvisor("BeforeAdvice", "execution(* bean.User.*())", new RegexExpressionPointCutResolver()));


        final Object bean = factory.getBean("user");

        User user = (User) bean;
        user.sayHello();
    }


}
