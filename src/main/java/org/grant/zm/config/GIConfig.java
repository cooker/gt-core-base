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

    Map<String, String> getMap();

    void reload();
}
