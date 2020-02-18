package mvc.handler;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName : HandlerMapping
 * @Author : TCW
 * @Date: 2020-02-18 15:32
 */
public interface HandlerMapping {

    Object gethandler(HttpServletRequest  request);
}
