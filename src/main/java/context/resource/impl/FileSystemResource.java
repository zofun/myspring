package context.resource.impl;

import context.resource.Resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName : FileSystemResource
 * @Author : TCW
 * @Date: 2020-02-17 15:49
 */
public class FileSystemResource implements Resource {

    private File file;


    public FileSystemResource(File file) {
        this.file = file;
    }

    @Override
    public boolean isExist() {
        return file.exists();
    }

    @Override
    public File getFile() {
        return file;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(file);
    }
}
