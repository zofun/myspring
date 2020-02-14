package bean.beanDefinition.impl;

import bean.beanDefinition.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @author TCW
 * bean定义的默认实现
 */
public class DefaultBeanDefinition implements BeanDefinition {
    private Class<?> clazz;
    private String beanName;
    private String beanFactoryName;
    private String createBeanMethodName;
    private String staticCreateBeanMethodName;
    private String beanInitMethodName;
    private String beanDestoryMethodName;

    private boolean isSingleton;
    private Constructor constructor;
    private Method method;
    private List<?> constructorArg;
    private Map<String,Object> values;

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public void setBeanFactoryName(String beanFactoryName) {
        this.beanFactoryName = beanFactoryName;
    }

    public void setCreateBeanMethodName(String createBeanMethodName) {
        this.createBeanMethodName = createBeanMethodName;
    }

    public void setBeanInitMethodName(String beanInitMethodName) {
        this.beanInitMethodName = beanInitMethodName;
    }

    public void setBeanDestoryMethodName(String beanDestoryMethodName) {
        this.beanDestoryMethodName = beanDestoryMethodName;
    }

    public void setSingleton(boolean singleton) {
        isSingleton = singleton;
    }

    @Override
    public Class<?> getBeanClass() {
        return clazz;
    }

    @Override
    public String getBeanName() {
        return beanName;
    }

    @Override
    public String getBeanFactory() {
        return beanFactoryName;
    }

    @Override
    public String getCreateBeanMethod() {
        return createBeanMethodName;
    }

    @Override
    public String getStaticCreateBeanMethod() {
        return staticCreateBeanMethodName;
    }

    @Override
    public String getBeanInitMethodName() {
        return beanInitMethodName;
    }

    @Override
    public String getBeanDestoryMethodName() {
        return beanDestoryMethodName;
    }

    @Override
    public String getScope() {
        return this.isSingleton? BeanDefinition.SINGLETON : BeanDefinition.PROTOTYPE;
    }

    @Override
    public boolean isSingleton() {
        return this.isSingleton;
    }

    @Override
    public boolean isPrototype() {
        return !this.isSingleton;
    }

    @Override
    public List<?> getConstructorArg() {
        return this.constructorArg;
    }

    @Override
    public Constructor<?> getConstructor() {
        return this.constructor;
    }

    @Override
    public void setConstructor(Constructor<?> constructor) {
        this.constructor = constructor;
    }

    @Override
    public Method getFactoryMethod() {
        return this.method;
    }

    @Override
    public void setFactoryMethod(Method factoryMethod) {
        this.method = method;
    }

    @Override
    public Map<String, Object> getPropertyKeyValue() {
        return this.values;
    }

    @Override
    public void setPropertyKeyValue(Map<String, Object> properties) {
        this.values = properties;
    }
}
