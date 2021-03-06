package aop.creator;

import aop.advisor.Advisor;
import aop.creator.impl.GenericAopFactory;
import aop.proxy.AopProxy;
import bean.factory.BeanFactory;

import java.util.List;

/**
 * @ClassName : AopFactory
 * @Author : TCW
 * @Date: 2020-02-16 11:31
 * aop工厂方法
 */
public interface AopFactory {

    /**
     * 创建代理提供对象
     * @param target
     * @param advisor
     * @param beanFactory
     * @param beanName
     * @return
     */
    AopProxy createAopProxyInstance(Object target, List<Advisor> advisor, BeanFactory beanFactory, String beanName);

    /**
     * 用于判断使用哪种代理方式来完成增强功能
     * 简单判断：类实现了接口就用JDK代理 没实现接口就用cglib代理
     * @param target
     * @return
     */
    default boolean judgeUseWhichProxyMode(Object target){
        Class<?>[] interfaces = target.getClass().getInterfaces();
        return interfaces.length>0;
    }

    /**
     * 创建代理提供对象
     * @return
     */
    static AopFactory createProxyInstance(){
        return new GenericAopFactory();
    }
}
