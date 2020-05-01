package com.github.cooker.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * grant
 * 28/4/2020 9:24 上午
 * 描述：
 */
public class NioClient {
    static boolean isStop = false;

    public static void main(String[] args) throws IOException, InterruptedException {
        SocketChannel client = SocketChannel.open();
        client.configureBlocking(false);
        boolean isCon =  client.connect(new InetSocketAddress("127.0.0.1",Contants.port));
        System.out.println(isCon);
        Selector selector = Selector.open();
        client.register(selector, SelectionKey.OP_CONNECT);

        Thread thread = new Thread(()->{
            try {
                connect(selector);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();

        Thread.sleep(5000L);
        isStop = true;
        client.close();
        selector.close();
    }


    private static void connect(Selector selector) throws IOException {
        while (!isStop){
            if (selector.select() == 0) continue;
            for (SelectionKey key : selector.selectedKeys()){

                if (key.isConnectable()){
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                    //完成连接的建立
                    if(socketChannel.finishConnect()) {
                        System.out.println("消息发送...");
                        ByteBuffer buffer = ByteBuffer.allocate(2);
                        buffer.put("sa".getBytes());
                        buffer.flip();
                        socketChannel.write(buffer);
                    }
                }else if (key.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    int readBytes = socketChannel.read(readBuffer);
                    if (readBytes > 0) {
                        readBuffer.flip();
                        byte[] bytes=new byte[readBuffer.remaining()];
                        readBuffer.get(bytes);
                        String body=new String(bytes,"UTF-8");
                        System.out.println("res"+body);
                    }else if(readBytes<0){
                        key.cancel();
                        socketChannel.close();
                    }
                }else if (key.isWritable()){
                    System.out.println("sasas");
                }
            }
        }
    }
}

