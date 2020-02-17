package context.resource;

import java.io.File;

/**
 * @ClassName : Resource
 * @Author : TCW
 * @Date: 2020-02-17 15:40
 * 读取配置文件的接口
 */
public interface Resource extends InputStreamSource{
    boolean isExist();

    File getFile();
}
