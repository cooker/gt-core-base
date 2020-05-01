package com.github.cooker.io;

import org.junit.Test;

import java.io.*;

/**
 * grant
 * 23/4/2020 2:07 PM
 * 描述：
 */
public class CharWriterTest {

    @Test
    public void sa() throws IOException {
        PrintWriter writer = new PrintWriter(System.out);
        writer.println("sasas");
        writer.flush();
        System.out.println("sasa");
    }
}
