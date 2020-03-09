package org.grant.zm.steam;

import org.junit.Test;

import java.util.stream.Stream;

/**
 * grant
 * 5/3/2020 3:26 下午
 * 描述：
 */
public class Stream8Test {

    /**
     *
     */
    @Test
    public void list(){
        Stream.of(1,2).flatMap(a->Stream.of(a+1)).forEach(System.out::println);
    }
}
