package context.reader;

import context.resource.Resource;
import jdk.internal.org.xml.sax.SAXException;
import org.dom4j.DocumentException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * @ClassName : BeanDefinitionReader
 * @Author : TCW
 * @Date: 2020-02-17 16:02
 *
 * 读取传入的字节流
 */
public interface BeanDefinitionReader {

    void loadBeanDefinition(Resource resource);

    void loadBeanDefinition(Resource...  resource)throws IOException, ParserConfigurationException, SAXException, DocumentException, ClassNotFoundException;

}
