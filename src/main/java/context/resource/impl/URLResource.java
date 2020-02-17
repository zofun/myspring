package context.resource.impl;

import context.resource.Resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @ClassName : URLResource
 * @Author : TCW
 * @Date: 2020-02-17 15:51
 */
public class URLResource implements Resource {
    private URL url;


    public URLResource(URL url) {
        this.url = url;
    }

    @Override
    public boolean isExist() {
        return url!=null;
    }

    @Override
    public File getFile() {
        return null;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return url.openStream();
    }
}
