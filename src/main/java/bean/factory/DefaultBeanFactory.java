package bean.factory;

import bean.beanDefinition.BeanDefinition;
import bean.beanDefinition.BeanDeginitionRegister;
import bean.beanReference.BeanReference;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
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
    public Object getBean(String beanName) throws Exception {
        return doGetBean(beanName);
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
            log.info("["+beanName+"]"+"不存在,正在尝试创建");
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

        Object instance = beanMap.get(beanName);

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
            instance=createBeanByConstruct(beanDefinition);
            if(instance==null){
                //使用构造器实例化失败，就使用静态方法创建实例
                instance=createBeanByStaticFactoryMethod(beanDefinition);
            }
        }else if(StringUtils.isBlank(beanDefinition.getStaticCreateBeanMethod())){
           instance= createBeanByFactoryMethod(beanDefinition);
        }


        //调用init方法

        doInit(beanDefinition,instance);
        //添加属性依赖

        parsePropertyValues(beanDefinition,instance);

        //从正在创建set中移除
        beans.remove(beanName);


        return instance;


    }


    /**do
     * 使用beanDefinition的构造器创建对象
     * @param bd
     * @return
     */
    private Object createBeanByConstruct(BeanDefinition bd){
        //获取参数
        List<?> args = bd.getConstructorArg();

        Object instance=null;
        Object[] params = new Object[0];
        try {
            //解析参数
            params = parseConstructArgs(args);
            //匹配构造器
            Constructor<?> constructor = matchConstructor(bd, params);
            instance= constructor.newInstance(params);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return instance;
    }

    /**
     * 解析传入的构造器参数值
     * @param args
     * @return
     * @throws Exception
     */
    private Object[] parseConstructArgs(List args) throws Exception{
        if(args==null||args.size()==0){
            return null;
        }
        Object[] rargs=new Object[args.size()];

        for(int i=0;i<args.size();i++){
            Object arg = args.get(i);
            Object val=null;
            if(arg instanceof BeanReference){
                //处理引用类型
                final String beanName = ((BeanReference) arg).getBeanName();
                val=doGetBean(beanName);
            }else if(arg instanceof List){
                val=parseListArg((List) arg);
            }else if(arg instanceof Map){
                // todo 处理map
            }else if (arg instanceof Properties){
                //todo 处理属性文件
            }else{
                val=arg;
            }
            rargs[i]=val;
        }
        return rargs;

    }

    /**
     * 解析构造器参数中的list类型的参数
     * @param arg
     * @return
     * @throws Exception
     */
    private List parseListArg(List arg) throws Exception {

        List param = new LinkedList();
        for(Object value:arg){
            Object res = new Object();
            if(arg instanceof BeanReference){
                String beanName = ((BeanReference) value).getBeanName();
                res = this.doGetBean(beanName);
            }else if(arg instanceof List){
                //递归
                res = parseListArg(arg);
            }else if(arg instanceof Map){
                //todo 处理map
            }else if(arg instanceof Properties){
                //todo 处理属性文件
            }else {
                res = arg;
            }
            param.add(res);
        }
        return param;
    }

    /**
     * 根据参数匹配对应的构造器
     * @param bd
     * @param args
     * @return
     * @throws Exception
     */
    private Constructor<?> matchConstructor(BeanDefinition bd,Object[] args) throws Exception{
        if(args==null){
            return bd.getBeanClass().getConstructor(null);
        }
        if(bd.getConstructor()!=null){
            //如果已经缓存，则直接返回
            return bd.getConstructor();
        }

        int len = args.length;
        Class[] param = new Class[len];
        //构造参数列表
        for(int i=0;i<len;i++){
            param[i] = args[i].getClass();
        }
        //匹配
        Constructor constructor = null;
        try {
            constructor = bd.getBeanClass().getConstructor(param);
        } catch (Exception e) {
            //这里上面的代码如果没匹配到会抛出空指针异常
            //为了代码继续执行 这里我们来捕获 但是不需要做其他任何操作
        }
        if(constructor != null){
            return constructor;
        }

        //未匹配到 继续匹配
        List<Constructor> firstFilterAfter = new LinkedList<>();
        Constructor[] constructors = bd.getBeanClass().getConstructors();
        //按参数个数匹配
        for(Constructor cons:constructors){
            if(cons.getParameterCount() == len){
                firstFilterAfter.add(cons);
            }
        }

        if(firstFilterAfter.size()==1){
            return firstFilterAfter.get(0);
        }
        if(firstFilterAfter.size()==0){
            log.error("不存在对应的构造函数：" + args);
            throw new Exception("不存在对应的构造函数：" + args);
        }
        //按参数类型匹配
        //获取所有参数类型
        boolean isMatch = true;
        for(int i=0;i<firstFilterAfter.size();i++){
            Class[] types = firstFilterAfter.get(i).getParameterTypes();
            for(int j=0;j<types.length;j++){
                if(types[j].isAssignableFrom(args[j].getClass())){
                    isMatch = false;
                    break;
                }
            }
            if(isMatch){
                //对于原型bean 缓存方法
                if(bd.isPrototype()){
                    bd.setConstructor(firstFilterAfter.get(i));
                }
                return firstFilterAfter.get(i);
            }
        }
        //未能匹配到
        throw new Exception("不存在对应的构造函数：" + args);
    }


    /**
     * 使用静态方法创建实例
     * @param bd
     * @return
     */
    private Object createBeanByStaticFactoryMethod(BeanDefinition bd) {
        final Class<?> beanClass = bd.getBeanClass();
        Object instance=null;
        try {
            Method method = beanClass.getMethod(bd.getStaticCreateBeanMethod());
            instance = method.invoke(beanClass, null);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return instance;
    }

    /**
     * 使用工厂方法创建实例
     * @param bd
     * @return
     */
    private  Object createBeanByFactoryMethod(BeanDefinition bd){
        Object instance=null;
        try {
            //获取工厂对象
            Object factory = doGetBean(bd.getBeanFactory());
            //获取创建实例的方法
            Method method = factory.getClass().getMethod(bd.getCreateBeanMethod());
            //创建实例
            instance = method.invoke(factory, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;

    }


    /**
     * 处理属性依赖
     * @param bd
     * @param instance
     * @throws Exception
     */
    private void parsePropertyValues(BeanDefinition bd, Object instance) throws Exception {
        Map<String, Object> propertyKeyValue = bd.getPropertyKeyValue();
        if(propertyKeyValue==null || propertyKeyValue.size()==0){
            return ;
        }
        Class<?> aClass = instance.getClass();
        Set<Map.Entry<String, Object>> entries = propertyKeyValue.entrySet();
        for(Map.Entry<String, Object> entry:entries){
            //获取指定的字段信息
            Field field = aClass.getDeclaredField(entry.getKey());
            //将访问权限设置为true
            field.setAccessible(true);
            Object arg = entry.getValue();
            Object value = null;
            if(arg instanceof BeanReference){
                String beanName = ((BeanReference) arg).getBeanName();
                value = this.doGetBean(beanName);
            }else if(arg instanceof List){
                List param = parseListArg((List) arg);
                value = param;
            }else if(arg instanceof Map){
                //todo 处理map
            }else if(arg instanceof Properties){
                //todo 处理属性文件
            }else {
                value = arg;
            }
            field.set(instance, value);
        }
    }


    /**
     * 调用bean的初始化方法
     * @param bd
     * @param instance
     */
    private void doInit(BeanDefinition bd, Object instance) {
        Class<?> beanClass = instance.getClass();
        if(StringUtils.isNotBlank(bd.getBeanInitMethodName())){
            try {
                Method method = beanClass.getMethod(bd.getBeanInitMethodName(), null);
                method.invoke(instance, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
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
