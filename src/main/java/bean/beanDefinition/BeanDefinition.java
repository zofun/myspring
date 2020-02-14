package bean.beanDefinition;


import org.apache.commons.lang3.StringUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @author TCW
 * bean定义接口，可以提供创建一个bean的所有信息
 * class能够提供创建对象的所有信息，但是spring由于引入了其它的东西（比如是否懒加载等），
 * class中包含的信息已经不足以描述bean了，因此引入了BeanDefinition
 */
public interface BeanDefinition {
    /**
     * 单例模式
     */
    String SINGLETON="singleton";
    /**
     * 原型模式
     */
    String PROTOTYPE="prototype";

    /**
     * 获取bean所对应的类，
     * 拿到bean所定义的类，使用反射即可实例化
     * @return
     */
    Class<?> getBeanClass();

    /**
     * 获取bean的名称
     * @return
     */
    String getBeanName();

    /**
     * 获取bean工厂的名字，利用工厂方法进行创建。
     *
     * @return
     */
    String getBeanFactory();

    /**
     * 获取工厂类中创建对象的方法名称
     * @return
     */
    String getCreateBeanMethod();

    String getStaticCreateBeanMethod();

    String getBeanInitMethodName();

    String getBeanDestoryMethodName();

    /**
     * 获取bean的类型
     * spring中其实提供了5中类型：
     * singleton:单例模式，bean以单例存在。（默认值）
     * prototype:原型模式，每次从容器中获取bean的时候，都会重新建立一个bean
     * request：每次请求都会创建一个bean，该作用域仅适用于ApplicationContext环节下
     * session:一个会话共享一套bean，该作用域仅适用于ApplicationContext环节下
     * globealSession：一般用于protlet环境下。该作用域仅适用于ApplicationContext环节下
     *
     * @return
     */
    String getScope();

    boolean isSingleton();

    boolean isPrototype();


    /**
     * 判断是否合法
     * @return
     */
    default boolean validate(){

        if(getBeanClass() == null){
            //字节码文件为空 并且创建bean的工厂为空,不合法.
            if(StringUtils.isBlank(getBeanFactory()) && StringUtils.isBlank(getCreateBeanMethod())){
                return false;
            }
        }
        return true;
    }

    /**
     * 获取传入的构造参数
     * @return
     */
    List<?> getConstructorArg();

    //下面的方法时为了缓存相应的调用方法
    Constructor<?> getConstructor();
    void setConstructor(Constructor<?> constructor);

    Method getFactoryMethod();
    void setFactoryMethod(Method factoryMethod);

    //属性依赖
    Map<String,Object> getPropertyKeyValue();
    void setPropertyKeyValue(Map<String,Object> properties);

}
