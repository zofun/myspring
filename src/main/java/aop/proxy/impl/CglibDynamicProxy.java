package aop.proxy.impl;

import aop.AopUtils;
import aop.advisor.Advisor;
import aop.proxy.AopProxy;
import bean.beanDefinition.BeanDefinition;
import bean.factory.BeanFactory;
import bean.factory.DefaultBeanFactory;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @ClassName : CglibDynamicProxy
 * @Author : TCW
 * @Date: 2020-02-16 14:51
 * cglib动态代理实现
 */
public class CglibDynamicProxy implements MethodInterceptor,AopProxy {

    private static final Log logger = LogFactory.getLog(JDKDynamicProxy.class);
    //加强器 用于生成代理对象
    private Enhancer enhancer = new Enhancer();
    private Object target;
    private List<Advisor> advisors;
    private BeanFactory beanFactory;
    private String beanName;

    public CglibDynamicProxy(Object target, List<Advisor> advisors, BeanFactory beanFactory, String beanName) {
        this.target = target;
        this.advisors = advisors;
        this.beanFactory = beanFactory;
        this.beanName = beanName;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Exception {
        return AopUtils.applyAdvice(target, o, advisors, objects, method, beanFactory);
    }

    @Override
    public Object getProxy() {
        return getProxy(target.getClass().getClassLoader());
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {

        logger.info("为" + target + "创建cglib代理");

        enhancer.setSuperclass(target.getClass());
        enhancer.setClassLoader(classLoader);
        enhancer.setInterfaces(target.getClass().getInterfaces());
        enhancer.setCallback(this);
        Constructor<?>[] constructors = target.getClass().getConstructors();
        Object res = null;
        if(constructors.length > 0){
            BeanDefinition bd = ((DefaultBeanFactory) beanFactory).getBeanDefinition(beanName);
            if(bd.getConstructor() != null){
                return enhancer.create(bd.getConstructor().getParameterTypes(), bd.getConstructorArg().toArray());
            }else {
                return enhancer.create();
            }
        }else {
            return enhancer.create();
        }
    }
}
