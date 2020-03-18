package org.grant.zm.config;

import lombok.Data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * grant
 * 2/3/2020 9:56 AM
 * 描述：配置增强
 */
@Data
public abstract class GConfigExt implements GIConfig{
    protected Map<String, String> props = new HashMap<>();
    private String filePath;

    public GConfigExt(String filePath) {
        this.filePath = filePath;
    }

    protected void check() throws FileNotFoundException {
        if (isExists()){
            reload();
        }else {
            throw new FileNotFoundException("Not Found Config " + getFilePath());
        }
    }

    protected abstract boolean isExists();

    @Override
    public String getPropByString(String key) {
        return props.get(key);
    }

    protected void reload(InputStream in) throws IOException {
        Properties p = new Properties();
        p.load(in);
        this.props = new HashMap<String, String>((Map)p);
        in.close();
    }

    public String getFilePath() {
        return filePath;
    }

    @Override
    public Map<String, String> getMap() {
        return new HashMap<>(props);
    }
}
