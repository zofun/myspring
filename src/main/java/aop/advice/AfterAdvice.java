package aop.advice;


import java.lang.reflect.Method;

/**
 * @ClassName : AfterAdvice
 * @Author : TCW
 * @Date: 2020-02-16 11:15
 * 后置通知接口
 */
public interface AfterAdvice  extends Advice {
    void after(Method method, Object[] args, Object target, Object returnVal);
}
