package org.grant.zm.config.impl;

import lombok.extern.slf4j.Slf4j;
import org.grant.zm.config.GAbstractConfig;
import org.grant.zm.utils.GThreadUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

/**
 * ZoomGrant 2020/3/1
 */
@Slf4j
public class GClassConfig extends GAbstractConfig {

    public GClassConfig(String filePath) throws FileNotFoundException {
        super(filePath);
        super.check();
    }

    @Override
    protected boolean isExists() {
        URL url = GThreadUtils.getCurrResource(getFilePath());
        if (url != null) {
            return true;
        }
        return false;
    }

    @Override
    public void reload() {
        super.reload();
        try {
            reload(GThreadUtils.getCurrResourceInput(getFilePath()));
        } catch (IOException e) {
            log.warn("config can't reload ", e);
        }

    }
}
