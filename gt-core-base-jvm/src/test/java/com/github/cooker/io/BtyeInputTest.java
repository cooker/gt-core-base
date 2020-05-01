package com.github.cooker.io;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

/**
 * grant
 * 23/4/2020 11:18 AM
 * 描述：
 */
public class BtyeInputTest {
    /** 输入
     * 字节流
     * ByteArrayInputStream
     * BufferedInputStream
     * FileInputStream
     * ObjectInputStream
     * SequenceInputStream
     * PipedInputStream
     *
     * FilterInputStream
     * PushbackInputStream
     * DataInputStream
     * LineNumberInputStream
     * StringBufferInputStream
     */
    byte[] bytes = null;

    @Before
    public void init(){
        bytes = new byte[]{-21,-2,'\r','\n',4,5,6,7,'\n',9,0};
    }


    @Test
    public void ByteArrayInputStream() throws IOException {
        ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        int len = bin.available();
        byte[] bytes1 = new byte[len];
        byte[] bytes2 = new byte[len];
        long now, end;
        now = System.currentTimeMillis();
        bin.read(bytes1);
        bin.read(bytes2);
        end = System.currentTimeMillis() - now;
        System.out.println(new String(bytes1));
        System.out.println(new String(bytes2));
        System.out.println(end);
        System.out.println(bin.available());
        bin.skip(10);
        bin.reset();
        System.out.println(bin.available());

        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        bout.reset();
//        bin.close(); // 不需要调用close
    }

    @Test
    public void ByteArrayInputStream2() throws IOException {
        ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(bin, 2);
        int b = -1;
        bufferedInputStream.mark(3);
        // read 是如果pos > 缓存内的长度，会执行fill 调用in 的read([],,)方法进行数据拷贝
        System.out.println(bufferedInputStream.read());
        System.out.println(bufferedInputStream.read());
        System.out.println(bufferedInputStream.read());

//        System.out.println(bufferedInputStream.read());

        /**
         * 不能超过 mark + readlimit ，不然reset会报错。处理原理如果有mark标记，
         *
         */
         bufferedInputStream.reset();

        System.out.println(bufferedInputStream.read());
        System.out.println(bufferedInputStream.read());
        System.out.println(bufferedInputStream.read());

        int len = bufferedInputStream.available();
        System.out.println(len);
        byte[] bs = new byte[6];
        len = bufferedInputStream.read(bs);
        System.out.println(len);
        for (int i=0;i<6;i++){
            System.out.println(bs[i]);
        }

        len = bufferedInputStream.read(bs);
        System.out.println(len);
        for (int i=0;i<6;i++){
            System.out.println(bs[i]);
        }

    }

    @Test
    public void FileInputStream() throws IOException {
        FileInputStream fin = new FileInputStream(System.getProperty("user.dir") + "/pom.xml");
//        fin.read()
//        System.out.println(fd);

    }


    @Test
    public void FilterInputStream(){
        //
        //        FilterInputStream;
    }

    @Test public void PushbackInputStream() throws IOException {
        //默认缓冲区 1，用于存放 unread 退回的数据
        PushbackInputStream pushbackInputStream = new PushbackInputStream(new ByteArrayInputStream(bytes));
        pushbackInputStream.read();
        pushbackInputStream.read();


        pushbackInputStream.unread(2);

    }

    @Test
    public void DataInputStream() throws IOException {
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bytes));
        System.out.println(dataInputStream.readInt());
    }

    @Test public void LineNumberInputStream() throws IOException {
        LineNumberInputStream lineNumberInputStream = new LineNumberInputStream(new ByteArrayInputStream(bytes));
        int num = lineNumberInputStream.read();
        System.out.println(num);
        num = lineNumberInputStream.read();
        System.out.println(num);
        num = lineNumberInputStream.read();
        System.out.println(num);
        num = lineNumberInputStream.read();
        System.out.println(num);

    }

    @Test
    public void ObjectInputStream() throws IOException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
    }

    @Test
    public void PipedInputStream() throws IOException {
        PipedOutputStream pipedOutputStream = new PipedOutputStream();
        PipedInputStream pipedInputStream = new PipedInputStream(pipedOutputStream);
        pipedOutputStream.write(12);
        int num = pipedInputStream.read();
        System.out.println(num);

        num = pipedInputStream.read();
        System.out.println(num);
    }

    @Test
    public void SequenceInputStream() throws IOException {

        SequenceInputStream sequenceInputStream = new SequenceInputStream(new ByteArrayInputStream(bytes),new ByteArrayInputStream(bytes));
        int num = -1;
        while ((num = sequenceInputStream.read()) != -1){
            System.out.println(num);
        }
    }

    @Test
    public void StringBufferInputStream(){
        StringBufferInputStream stringBufferInputStream = new StringBufferInputStream("sa");
        //\u000d System.out.println("sasa");
    }

}
