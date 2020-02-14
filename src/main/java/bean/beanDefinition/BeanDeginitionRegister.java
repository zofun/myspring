package bean.beanDefinition;

/**
 * @author TCW
 * 定义了注册bean到Bean工厂的方法和获取bean对应的BeanDefintion的方法
 */
public interface BeanDeginitionRegister {

    /**
     *注册bean到bean工厂
     * @param def
     * @param beanName
     */
    void register(BeanDefinition def, String beanName);

    /**
     * 判断是否存在bean
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * 获取对应bean的Definition
     * @param beanName
     * @return
     */
    BeanDefinition getBeanDefinition(String beanName);
}
