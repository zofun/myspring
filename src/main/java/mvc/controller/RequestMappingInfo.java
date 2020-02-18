package mvc.controller;

import mvc.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @ClassName : RequestMappingInfo
 * @Author : TCW
 * @Date: 2020-02-18 15:41
 */
public class RequestMappingInfo {

    private RequestMapping classRequestMapping;

    private String beanName;

    private RequestMapping methodRequestMapping;

    private Method method;

    private Object handler;

    public boolean isMatch(HttpServletRequest request){

        //todo
        return false;
    }
}
