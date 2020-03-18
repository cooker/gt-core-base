package org.grant.zm.config.impl;

import lombok.extern.slf4j.Slf4j;
import org.grant.zm.config.GAbstractConfig;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * ZoomGrant 2020/3/1
 */
@Slf4j
public class GFileConfig extends GAbstractConfig {

    public GFileConfig(String filePath) throws FileNotFoundException {
        super(filePath);
        super.check();
    }

    @Override
    protected boolean isExists() {
        if (Files.exists(Paths.get(getFilePath()))){
            return true;
        }
        return false;
    }


    @Override
    public void reload() {
        super.reload();
        try {
            reload(new FileInputStream(getFilePath()));
        } catch (IOException e) {
            log.warn("config can't reload ", e);
        }

    }
}
