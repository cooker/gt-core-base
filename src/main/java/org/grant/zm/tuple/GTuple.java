package org.grant.zm.tuple;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ZoomGrant 2020/2/28
 */
@Data
@AllArgsConstructor
public class GTuple<K, V> {
    private K key;
    private V val;

    public static <K, V> GTuple of(K key, V val){
        return new GTuple(key, val);
    }
}
