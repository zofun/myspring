package aop;

import aop.advice.Advice;
import aop.advisor.Advisor;
import aop.advisor.PointCutAdvisor;
import aop.chain.AopAdviceChain;
import aop.pointcut.PointCut;
import bean.factory.BeanFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName : AopUtils
 * @Author : TCW
 * @Date: 2020-02-16 11:41
 */
public class AopUtils {


    /**
     *应用通知
     * @param target
     * @param proxy
     * @param advisors
     * @param args
     * @param method
     * @param beanFactory
     * @return
     * @throws Exception
     */
    public static Object applyAdvice(Object target, Object proxy,List<Advisor> advisors, Object[] args, Method method, BeanFactory beanFactory) throws Exception {
        //获取与该方法匹配的增强
        List<Advice> advices = getMatchMethodAdvice(method,target.getClass(), advisors, beanFactory);
        if(advisors==null||advisors.size()==0){
            //无增强，直接调用
            return method.invoke(target,args);
        }else {
            //存在该方法的增强器
            return new AopAdviceChain(method, target, args, proxy, advices).invoke();
        }
    }

    /**
     * 获取与方法匹配的通知
     * @param method
     * @param aClass
     * @param advisors
     * @param beanFactory
     * @return
     * @throws Exception
     */
    public static List<Advice> getMatchMethodAdvice(Method method, Class<?> aClass, List<Advisor> advisors, BeanFactory beanFactory) throws Exception {

        if(advisors==null||advisors.size()==0){
            return null;
        }
        List<Advice> advices=new ArrayList<>();
        for (Advisor advisor : advisors) {
            if(advisor instanceof PointCutAdvisor){
                PointCut resolver = ((PointCutAdvisor) advisor).getPointCutResolver();
                boolean flag = resolver.matchsMethod(aClass, method, advisor.getExpression());
                if(flag){
                    advices.add((Advice) beanFactory.getBean(advisor.getAdviceBeanName()));
                }

            }
        }
        return advices;
    }
}
