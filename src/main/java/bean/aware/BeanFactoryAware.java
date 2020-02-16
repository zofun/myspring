package bean.aware;

import bean.factory.BeanFactory;

/**
 * @author TCW
 * 注入BeanFactory
 */
public interface BeanFactoryAware  extends Aware {

    void setBeanFactory(BeanFactory beanFactory);
}
