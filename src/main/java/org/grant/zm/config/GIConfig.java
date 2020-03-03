package org.grant.zm.config;

import java.util.Map;

/**
 * ZoomGrant 2020/3/1
 */
public interface GIConfig {

    Integer getPropByInt(String key);
    Long getPropByLong(String key);
    Double getPropByDouble(String key);
    Boolean getPropByBool(String key);
    String getPropByString(String key);

    Integer getPropByInt(String key, Integer def);
    Long getPropByLong(String key, Long def);
    Double getPropByDouble(String key, Double def);
    String getPropByString(String key, String def);

    Map<String, String> getMap();

    void reload();
}
