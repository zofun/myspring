package mvc.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @ClassName : View
 * @Author : TCW
 * @Date: 2020-02-18 12:14
 */
public interface View {
    void render(HttpServletRequest req, HttpServletResponse response, Map<String,Object> model);
}
