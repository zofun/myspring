package bean.postProcess;

/**
 * @ClassName : AopPostProcessor
 * @Author : TCW
 * @Date: 2020-02-16 11:04
 */
public interface AopPostProcessor extends BeanPostProcessor {

    Object postProcessWeaving(Object bean,String beanName) throws Exception;
}
