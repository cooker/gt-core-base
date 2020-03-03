package org.grant.zm.config;

import io.vavr.control.Option;

/**
 * grant
 * 2/3/2020 9:34 AM
 * 描述：
 */
public abstract class GAbstractConfig extends GConfigExt implements GIConfig {

    public GAbstractConfig(String filePath) {
        super(filePath);
    }

    @Override
    public Integer getPropByInt(String key) {
        return Integer.valueOf(getPropByString(key));
    }

    @Override
    public Long getPropByLong(String key) {
        return Long.valueOf(getPropByString(key));
    }

    @Override
    public Double getPropByDouble(String key) {
        return Double.valueOf(getPropByString(key));
    }

    @Override
    public Boolean getPropByBool(String key) {
        String val = getPropByString(key);
        if (val != null) {
            if ("t".equalsIgnoreCase(val) || "true".equalsIgnoreCase(val)){
                return Boolean.TRUE;
            }else {
                return Boolean.FALSE;
            }
        }else {
            return Boolean.FALSE;
        }
    }

    @Override
    public Integer getPropByInt(String key, Integer def) {
        return Option.of(getPropByInt(key)).getOrElse(def);
    }

    @Override
    public Long getPropByLong(String key, Long def) {
        return Option.of(getPropByLong(key)).getOrElse(def);
    }

    @Override
    public Double getPropByDouble(String key, Double def) {
        return Option.of(getPropByDouble(key)).getOrElse(def);
    }

    @Override
    public String getPropByString(String key, String def) {
        return Option.of(getPropByString(key)).getOrElse(def);
    }

    @Override
    public void reload() {

    }
}
