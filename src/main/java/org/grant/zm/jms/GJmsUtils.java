package org.grant.zm.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * ZoomGrant 2020/3/1
 */
public class GJmsUtils {

    public static Map<String, String> getProps(Message message) throws JMSException {
        Enumeration en = message.getPropertyNames();
        String key, value;
        Map<String, String> props = new HashMap<>();
        while (en.hasMoreElements()){
            key = Objects.toString(en.nextElement());
            value = message.getStringProperty(key);
            props.put(key, value);
        }
        return props;
    }
}
