package com.github.cooker.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;

/**
 * grant
 * 28/4/2020 9:24 上午
 * 描述：
 */
public class AioClient {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
        client.connect(new InetSocketAddress(Contants.port)).get();
        client.write(ByteBuffer.wrap("heeeee".getBytes()));
        System.out.println("消息发送...");
    }
}
