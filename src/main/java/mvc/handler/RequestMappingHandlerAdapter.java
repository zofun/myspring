package mvc.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName : RequestMappingHandlerAdapter
 * @Author : TCW
 * @Date: 2020-02-18 15:51
 */
public class RequestMappingHandlerAdapter implements HandlerAdapter {
    @Override
    public Object handler(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public boolean isMatch(HttpServletRequest request) {
        return false;
    }
}
