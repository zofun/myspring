package bean.aware;

import context.app.ApplicationContext;

/**
 * @ClassName : ApplicationContextAware
 * @Author : TCW
 * @Date: 2020-02-18 15:36
 */
public interface ApplicationContextAware extends Aware {


    void setApplicationContext(ApplicationContext applicationContext);

}
