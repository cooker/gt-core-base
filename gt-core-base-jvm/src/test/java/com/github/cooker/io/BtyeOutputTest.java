package com.github.cooker.io;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

/**
 * grant
 * 23/4/2020 2:06 PM
 * 描述：
 */
public class BtyeOutputTest {

    /* 字节流
     * ByteArrayOutputStream
     * BufferedOutputStream
     * FileOutputStream
     * ObjectOutputStream
     * SequenceOutputStream
     * PipedOutputStream
     *
     * FilterOutputStream
     * PushbackOutputStream
     * DataOutputStream
     * LineNumberOutputStream
     * StringBufferOutputStream
     */
    @Test
    public void ByteArrayOutputStream() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(new String("xxx").getBytes());
        System.out.println(byteArrayOutputStream.toString());
    }

    @Test
    public void BufferedOutputStream() throws IOException {
        BufferedOutputStream  bufferedOutputStream = new BufferedOutputStream(new ByteArrayOutputStream(), 2);
        //缓存区满后 回调用flush
        bufferedOutputStream.write(1);
        bufferedOutputStream.write(1);
        bufferedOutputStream.write(1);
    }

    @Test
    public void FileOutputStream() throws FileNotFoundException {
        FileOutputStream fout = new FileOutputStream("~/1.txt");
//        fout.write();
    }

    @Test
    public void ObjectOutputStream() throws IOException {
        File file = new File("1.txt");
        file.createNewFile();
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file, true));
        out.write(1);
//        out.flush();
        System.out.println("111");
    }


    @Test
    public void channel() throws IOException {
        File file = new File("1.txt");
        SeekableByteChannel channel = Files.newByteChannel(file.toPath(), StandardOpenOption.WRITE, StandardOpenOption.READ);
        channel.write(ByteBuffer.wrap("sasa".getBytes()));
        ByteBuffer bye = ByteBuffer.allocate((int)channel.size());
        int num = channel.read(bye);
        System.out.println(num);

        System.out.println(new String(bye.array()));
    }

    @Test
    public void fileBuffer() throws IOException {
        File file = new File("1.txt");
        FileOutputStream fout = new FileOutputStream(file);
        BufferedOutputStream fbout = new BufferedOutputStream(fout, 10);

        fbout.write(new java.lang.String("sasa\n").getBytes());
        fbout.flush();
        fbout.write(new java.lang.String("ssa\n").getBytes());
        fbout.flush();
        fbout.write(new java.lang.String("sasa\n").getBytes());
        fbout.flush();
        fbout.write(new java.lang.String("sasa").getBytes());
        fbout.flush();
    }
}
