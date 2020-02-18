package mvc.handler;

import bean.aware.ApplicationContextAware;
import context.app.ApplicationContext;
import mvc.annotation.RequestMapping;
import mvc.controller.RequestMappingInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @ClassName : RequestMappingHandlerMapping
 * @Author : TCW
 * @Date: 2020-02-18 15:35
 */
public class RequestMappingHandlerMapping implements HandlerMapping,InitializingBean, ApplicationContextAware {

    private ApplicationContext context;

    private List<RequestMappingInfo> requestMappingInfos;
    private Map<String,List<RequestMapping>> urlMaps;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {

        this.context=applicationContext;
    }

    @Override
    public Object gethandler(HttpServletRequest request) {
        return null;
    }

    @Override
    public void afterPropertiesSet() {

        String[] beans = context.getBeanNameForType(Object.class);
        for (String beanName:beans){
            Class aClass = context.getType(beanName);
            if(isHandler(aClass)){
                detectHandlerMethod(aClass);
            }
        }

    }

    private void detectHandlerMethod(Class type) {

    }

    public boolean isHandler(Class beanType){

        return false;
    }
}
