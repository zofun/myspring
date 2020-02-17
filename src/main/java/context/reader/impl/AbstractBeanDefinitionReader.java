package context.reader.impl;

import bean.beanDefinition.BeanDeginitionRegister;
import context.reader.BeanDefinitionReader;
import context.resource.Resource;
import jdk.internal.org.xml.sax.SAXException;
import org.dom4j.DocumentException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * @ClassName : AbstractBeanDefinitionReader
 * @Author : TCW
 * @Date: 2020-02-17 17:31
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    protected BeanDeginitionRegister register;

    public AbstractBeanDefinitionReader(BeanDeginitionRegister register) {
        this.register = register;
    }
}
