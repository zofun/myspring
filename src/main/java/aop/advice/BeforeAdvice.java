package aop.advice;

import java.lang.reflect.Method;

/**
 * @ClassName : BeforeAdvice
 * @Author : TCW
 * @Date: 2020-02-16 11:16
 * 前置通知
 */
public interface BeforeAdvice extends Advice {

    void before(Method method, Object[] args, Object target);
}
