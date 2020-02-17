package aop.creator.impl;

import aop.advisor.Advisor;
import aop.advisor.AdvisorRegister;
import aop.advisor.PointCutAdvisor;
import aop.creator.AopFactory;
import bean.aware.BeanFactoryAware;
import bean.factory.BeanFactory;
import bean.postProcess.AopPostProcessor;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName : AopProxyCreator
 * @Author : TCW
 * @Date: 2020-02-16 16:36
 */
public class AopProxyCreator implements AdvisorRegister, BeanFactoryAware, AopPostProcessor {

    private BeanFactory beanFactory;
    private List<Advisor> advisors;

    public AopProxyCreator(){
        advisors=new ArrayList<>();
    }

    @Override
    public void register(Advisor advisor) {

        advisors.add(advisor);
    }

    @Override
    public List<Advisor> getAdvisor() {
        return this.advisors;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory=beanFactory;
    }

    @Override
    public Object postProcessWeaving(Object bean, String beanName) throws Exception {
        //获取和当前bean匹配的advisor
        List<Advisor> matchAdvisor ;
        matchAdvisor = getMatchAdvisor(bean);
        //对bean进行增强
        if(!CollectionUtils.isEmpty(matchAdvisor)){
            bean = AopFactory.createProxyInstance().createAopProxyInstance(bean, matchAdvisor, beanFactory, beanName).getProxy();
        }
        return bean;
    }


    /**
     * 获取于当前bean匹配的所有advisor
     * @param bean
     * @return
     * @throws Exception
     */
    private List<Advisor> getMatchAdvisor(Object bean) throws Exception {
        if(CollectionUtils.isEmpty(advisors) || bean ==null){
            return null;
        }
        List<Advisor> matchAdvisor = new ArrayList<>();
        Class<?> aClass = bean.getClass();
        for(Advisor advisor:advisors){
            if(advisor instanceof PointCutAdvisor){
                if(((PointCutAdvisor) advisor).getPointCutResolver().matchsClass(aClass, advisor.getExpression())){
                    matchAdvisor.add(advisor);
                }
            }
        }

        return matchAdvisor;
    }
}
