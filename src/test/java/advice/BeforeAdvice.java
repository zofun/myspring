package advice;

import javax.sound.midi.Soundbank;
import java.lang.reflect.Method;

/**
 * @ClassName : BeforeAdvice
 * @Author : TCW
 * @Date: 2020-02-16 18:39
 */
public class BeforeAdvice implements aop.advice.BeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) {
        System.out.println("before...");
    }
}
