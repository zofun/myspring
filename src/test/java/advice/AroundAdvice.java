package advice;

import javax.sound.midi.Soundbank;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

/**
 * @ClassName : AroundAdvice
 * @Author : TCW
 * @Date: 2020-02-16 17:00
 */
public class AroundAdvice implements aop.advice.AroundAdvice {
    @Override
    public Object around(Method method, Object[] args, Object target) throws InvocationTargetException, IllegalAccessException {
        System.out.println("开始。。。。");
        Object invoke = method.invoke(target, args);
        System.out.println("结束。。。。");
        return invoke;
    }
}
