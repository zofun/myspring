package aop.creator.impl;

import aop.advisor.Advisor;
import aop.creator.AopFactory;
import aop.proxy.AopProxy;
import aop.proxy.impl.CglibDynamicProxy;
import aop.proxy.impl.JDKDynamicProxy;
import bean.factory.BeanFactory;

import java.util.List;

/**
 * @ClassName : GenericAopFactory
 * @Author : TCW
 * @Date: 2020-02-16 16:31
 * 代理工厂
 */
public class GenericAopFactory implements AopFactory {
    @Override
    public AopProxy createAopProxyInstance(Object target, List<Advisor> advisor, BeanFactory beanFactory, String beanName) {
        //判断是使用jdk动态代理还是cglib动态代理
        boolean flag = judgeUseWhichProxyMode(target);
        if(flag){
            //jdk动态代理
            return new JDKDynamicProxy(target,advisor,beanFactory);
        }else {
            //cglib动态代理
            return new CglibDynamicProxy(target,advisor,beanFactory,beanName);
        }

    }
}
