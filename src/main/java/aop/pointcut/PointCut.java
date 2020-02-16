package aop.pointcut;

import java.lang.reflect.Method;

/**
 * @ClassName : PointCut
 * @Author : TCW
 * @Date: 2020-02-16 11:10
 */
public interface PointCut {
    boolean matchsClass(Class<?> targetClass, String expresseion) throws Exception;
    boolean matchsMethod(Class<?> targetClass, Method method, String expresseion) throws Exception;
}
