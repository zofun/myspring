package context.resource;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName : InputStreamSource
 * @Author : TCW
 * @Date: 2020-02-17 15:40
 */
public interface InputStreamSource {
    InputStream getInputStream() throws IOException;
}
