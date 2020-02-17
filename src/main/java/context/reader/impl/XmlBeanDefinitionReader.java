package context.reader.impl;

import bean.beanDefinition.BeanDefinition;
import bean.beanDefinition.BeanDeginitionRegister;
import bean.beanDefinition.impl.DefaultBeanDefinition;
import context.resource.Resource;
import jdk.internal.org.xml.sax.SAXException;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @ClassName : XmlBeanDefinitionReader
 * @Author : TCW
 * @Date: 2020-02-17 17:33
 * 解析配置文件并生成BeanDefinition并注册
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDeginitionRegister register) {
        super(register);
    }

    @Override
    public void loadBeanDefinition(Resource resource) {

        loadBeanDefinition(resource);
    }

    @Override
    public void loadBeanDefinition(Resource... resource) throws IOException, ParserConfigurationException, SAXException, DocumentException, ClassNotFoundException {

        for (Resource r:resource){
            parse(r);
        }
    }

    private void parse(Resource res) throws IOException, DocumentException, ClassNotFoundException {
        InputStream inputStream = res.getInputStream();
        //获取document对象
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element rootElement = document.getRootElement();
        //解析
        List<Element> elements = rootElement.elements();
        for(Element element:elements){
            List<Attribute> attributes = element.attributes();
            BeanDefinition beanDefinition = new DefaultBeanDefinition();
            for(Attribute attribute:attributes){
                //class标签
                if("class".equals(attribute.getName())){
                    String data = (String) attribute.getData();
                    Class<?> aClass = Class.forName(data);
                    ((DefaultBeanDefinition) beanDefinition).setClazz(aClass);
                }else if("id".equals(attribute.getName())){
                    //class标签
                    String data = (String) attribute.getData();
                    ((DefaultBeanDefinition) beanDefinition).setBeanName(data);
                }
                //todo 其他标签
            }

            if(beanDefinition.getBeanName() != null){
                this.register.register(beanDefinition, beanDefinition.getBeanName());
            }
        }
    }
}
