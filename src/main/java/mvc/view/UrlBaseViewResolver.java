package mvc.view;

/**
 * @ClassName : UrlBaseViewResolver
 * @Author : TCW
 * @Date: 2020-02-18 15:58
 */
public abstract class UrlBaseViewResolver implements ViewResolver {
    private final String REDIRECT_URL_PREFIX = "redirect:";
    private final String FOWARD_URL_PREFIX = "forward:";

    @Override
    public abstract View resolverView(String viewName);
}
