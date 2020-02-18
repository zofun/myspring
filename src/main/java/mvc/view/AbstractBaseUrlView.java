package mvc.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @ClassName : AbstractBaseUrlView
 * @Author : TCW
 * @Date: 2020-02-18 15:54
 */
public class AbstractBaseUrlView implements View {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void render(HttpServletRequest req, HttpServletResponse response, Map<String, Object> model) {

    }
}
