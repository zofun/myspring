package aop.advice;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName : AroundAdvice
 * @Author : TCW
 * @Date: 2020-02-16 11:23
 * 环绕通知
 */
public interface AroundAdvice extends Advice{
    Object around(Method method, Object[] args, Object target) throws InvocationTargetException, IllegalAccessException;
}
