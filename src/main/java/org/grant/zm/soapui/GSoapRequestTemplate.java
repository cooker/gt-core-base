package org.grant.zm.soapui;

/**
 * grant
 * 3/3/2020 12:42 下午
 * 描述：
 */
public interface GSoapRequestTemplate {
    String HEADER = "";
    String FOOT = "";

    String X_KEY_START = "<%s>";
    String X_KEY_END = "</%s>";

    String X_VALUE = "<![CDATA[%s]]>";
    String X_VALUE_EMPTY = "<![CDATA[]]>";
}
