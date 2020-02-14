package bean.factory;

import bean.beanDefinition.BeanDefinition;
import bean.beanDefinition.BeanDeginitionRegister;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultBeanFactory implements BeanFactory, BeanDeginitionRegister, Closeable {

    private Log log= LogFactory.getLog(this.getClass());

    private Map<String, BeanDefinition> bdMap=new ConcurrentHashMap<>();
    private Map<String,Object> beanMap=new ConcurrentHashMap<>();

    /**
     * 记录正在创建的bean
     */
    private ThreadLocal<Set<String>> initialedBeans=new ThreadLocal<>();

    @Override
    public Object getBean() throws Exception {
        return null;
    }

    @Override
    public String[] getBeanNameForType(Class<?> tclass) {
        return new String[0];
    }

    @Override
    public Map<String, Object> getBeansForType(Class<?> tclass) {
        return null;
    }

    @Override
    public Class getType(String beanName) {
        BeanDefinition beanDef = bdMap.get(beanName);
        return beanDef.getClass();
    }

    @Override
    public void register(BeanDefinition def, String beanName) {

        if(bdMap.containsKey(beanName)){
            log.info("["+beanName+"]"+"已经存在");
        }
        if(!def.validate()){
            log.error("["+beanName+"]"+"不合法");
        }
        bdMap.put(beanName,def);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return bdMap.containsKey(beanName);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
       if(!bdMap.containsKey(beanName)){
           log.info("["+beanName+"]"+"不存在");
       }
        return bdMap.get(beanName);
    }

    private Object doGetBean(String beanName) throws Exception{
        if(!beanMap.containsKey(beanName)){
            log.info("["+beanName+"]"+"不存在");
        }
        //记录正在创建的bean
        Set<String> beans=this.initialedBeans.get();
        if(beans==null){
            beans=new HashSet<>();
            this.initialedBeans.set(beans);
        }
        //循环依赖检测
        if(beans.contains(beanName)){
            log.error("检测到" + beanName + "存在循环依赖：");
        }
        //添加到正在创建的set中
        beans.add(beanName);

        final Object instance = beanMap.get(beanName);

        if(instance!=null){
            return instance;
        }

        //不存在就创建
        if(!bdMap.containsKey(beanName)){
            log.info("不存在"+beanName+"的定义，正在创建");

        }
        BeanDefinition beanDefinition = this.bdMap.get(beanName);
        Class<?> beanClass = beanDefinition.getBeanClass();
        if(beanClass!=null){
            
        }

    }

    @Override
    public void close() throws IOException {

        Set<Map.Entry<String, BeanDefinition>> entries = bdMap.entrySet();
        //调用所有bean的destory方法
        for(Map.Entry<String, BeanDefinition>  entry: entries){
            BeanDefinition value = entry.getValue();
            String destoryMethodName = value.getBeanDestoryMethodName();
            try {
                Method method = value.getBeanClass().getMethod(destoryMethodName, null);
                method.invoke(value.getBeanClass(), null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
