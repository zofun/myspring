package mvc.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName : HandlerAdapter
 * @Author : TCW
 * @Date: 2020-02-18 15:49
 */
public interface HandlerAdapter {

    Object handler(HttpServletRequest request, HttpServletResponse response);

    boolean isMatch(HttpServletRequest request);
}
