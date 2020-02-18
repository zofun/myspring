package mvc.view;

/**
 * @ClassName : ViewResolver
 * @Author : TCW
 * @Date: 2020-02-18 15:59
 */
public interface ViewResolver {
    View resolverView(String viewName);
}
