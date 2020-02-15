package bean.factory;

import java.util.Map;

/**
 * @author TCW
 * 定义了存取bean相关的方法
 */
public interface BeanFactory {
    Object getBean(String beanName) throws Exception;
    String[] getBeanNameForType(Class<?> tclass);
    Map<String,Object> getBeansForType(Class<?> tclass);
    Class getType(String beanName);

}
