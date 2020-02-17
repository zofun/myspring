package context.app.impl;

import bean.beanDefinition.BeanDeginitionRegister;
import context.reader.impl.XmlBeanDefinitionReader;
import context.resource.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @ClassName : XmlApplicationContext
 * @Author : TCW
 * @Date: 2020-02-17 17:39
 */
public class XmlApplicationContext extends AbstractApplicationContext {
    private List<Resource> resources;

    public XmlApplicationContext(String loc) throws Exception {
        //初始化
        resources = new ArrayList<>();
        resources.add(getResource(loc));
        //生成reader对象
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader((BeanDeginitionRegister) this.beanFactory);;
        //生成resource数组对象
        Resource[] resource = new Resource[resources.size()];
        int i=0;
        for(Resource res:resources){
            resource[i++] = res;
        }
        xmlBeanDefinitionReader.loadBeanDefinition(resource);
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
        return null;
    }
}
