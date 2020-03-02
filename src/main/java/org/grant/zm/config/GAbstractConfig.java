package org.grant.zm.config;

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
    public void reload() {

    }
}
