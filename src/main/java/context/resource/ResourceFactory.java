package context.resource;

/**
 * @ClassName : ResourceFactory
 * @Author : TCW
 * @Date: 2020-02-17 15:45
 * Resource工厂
 */
public interface ResourceFactory {
    Resource getResource(String localtions) throws Exception;
}
