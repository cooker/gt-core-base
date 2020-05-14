package com.github.cooker.collection;

import org.junit.Test;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

/**
 * grant
 * 11/5/2020 10:34 上午
 * 描述：
 */
public class SetTest {

    @Test
    public void add(){
//        EnumSet a = new EnumSet<>();
    }

    static class A{
        private double i = Math.random();
        @Override
        public int hashCode() {
            return 1;
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

        @Override
        public String toString() {
            return "A{" +
                    "i=" + i +
                    '}';
        }
    }
}
