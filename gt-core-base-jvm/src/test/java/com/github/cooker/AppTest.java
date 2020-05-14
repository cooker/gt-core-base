package com.github.cooker;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
//        Pair<String, String>[] pairs = new Pair[10];
//        pairs[0] = new Pair(1,2);
//        System.out.println(pairs[0].getKey());
//        List<?> list = new ArrayList<>();
//        list.get(0);
        Pair pair = new Pair(1, "111");
        System.out.println(String.class.cast("222"));
//        System.out.println(ss.getKey());

    }

    static class Pair<K, V extends CharSequence>{
        K key;
        V val;

        public Pair(K key, V val) {
            this.key = key;
            this.val = val;
        }

        public K getKey() {
            return key;
        }

        public V getVal() {
            return val;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public void setVal(V val) {
            this.val = val;
        }
    }
}
