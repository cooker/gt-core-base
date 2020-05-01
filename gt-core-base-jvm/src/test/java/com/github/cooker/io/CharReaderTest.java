package com.github.cooker.io;

import org.junit.Test;

import java.io.*;
import java.sql.ClientInfoStatus;

/**
 * grant
 * 23/4/2020 1:11 PM
 * 描述：
 */
public class CharReaderTest {
    /**
     * 字符流
     * BufferedReader
     * LineNumberReader
     * CharArrayReader
     * FilterReader
     * PushbackReader
     * InputStreamReader
     * FileReader
     * PipedReader
     * StringReader
     */

    @Test
    public void BufferedReader() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("1.txt"));
//        String sa = bufferedReader.readLine();
        System.out.println((char)bufferedReader.read());
//        System.out.println(sa);

    }

    @Test
    public void LineNumberReader() throws FileNotFoundException {
        LineNumberReader lineNumberReader = new LineNumberReader(new FileReader("1.txt"));
    }

}
