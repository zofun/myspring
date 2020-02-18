package mvc.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName : SimpleControllerHandlerAdpater
 * @Author : TCW
 * @Date: 2020-02-18 15:52
 */
public class SimpleControllerHandlerAdpater implements HandlerAdapter{
    @Override
    public Object handler(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public boolean isMatch(HttpServletRequest request) {
        return false;
    }
}
