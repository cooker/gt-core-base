package com.github.cooker.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketOption;
import java.net.SocketOptions;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.Set;

/**
 * grant
 * 28/4/2020 9:24 上午
 * 描述：
 */
public class NioServer {
    static boolean isStop = false;
    public static void main(String[] args) throws InterruptedException, IOException {
        ServerSocketChannel serverSocketChannel = null;
        Selector selector = Selector.open();////创建选择器  获得一个多路复用器
        try {
            serverSocketChannel = ServerSocketChannel.open();//获得一个serverChannel

            serverSocketChannel.configureBlocking(false);//设置为非阻塞模式 如果为 true，则此通道将被置于阻塞模式；如果为 false，则此通道将被置于非阻塞模式
            serverSocketChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
            serverSocketChannel.socket().bind(new InetSocketAddress(Contants.port), 1024);//绑定一个端口和等待队列长度
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);//把selector注册到channel，关注链接事件
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("绑定端口...");
        Thread thread = new Thread(()->{
            try {
                connect(selector);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();

        Thread.sleep(1200000L);
        isStop = true;
        serverSocketChannel.close();
        selector.close();
    }


    private static void connect(Selector selector) throws IOException {
        while (!isStop){
            if (selector.select() == 0) continue;
            Iterator<SelectionKey> selectionKeys = selector.selectedKeys().iterator();

            while (selectionKeys.hasNext()){
                SelectionKey key = selectionKeys.next();
                if (key.isValid()){
                    //有效的key
                    if (key.isAcceptable()){
                        selectionKeys.remove();
                        System.out.println("有新的链接...");
                        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                        // 通过ServerSocketChannel的accept创建SocketChannel实例
                        // 完成该操作意味着完成TCP三次握手，TCP物理链路正式建立
                        SocketChannel sc = ssc.accept();//3次握手
                        sc.configureBlocking(false);
                        sc.setOption(StandardSocketOptions.SO_REUSEADDR, true);
                        sc.register(selector, SelectionKey.OP_READ);//连接建立后关注读事件
                    }else if(key.isReadable()){
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer readbuffer = ByteBuffer.allocate(1024);//写 0 1024  1024
                        //  ByteBuffer readbuffer = ByteBuffer.allocateDirect(1024); //申请直接内存，也就是堆外内存
                        // 读取请求码流，返回读取到的字节数
                        int readBytes = channel.read(readbuffer);
                        // 读取到字节，对字节进行编解码
                        if (readBytes > 0) {
                            // 将缓冲区当前的limit设置为position=0，用于后续对缓冲区的读取操作
                            readbuffer.flip();//读写模式反转
                            // 将缓冲区可读字节数组复制到新建的数组中
                            byte[] bytes = new byte[readbuffer.remaining()];
                            readbuffer.get(bytes);
                            String body = new String(bytes, "UTF-8");
                            System.out.println("input is:" + body);
//                            res(socketChannel, body);
                        }else if(readBytes < 0){
                            // 链路已经关闭 释放资源
                            System.out.println("链路已经关闭 释放资源 >>" + channel.getRemoteAddress());
                            channel.close();
                            key.cancel();
                            selectionKeys.remove();
                        }else{
                            // 没有读到字节忽略
                            System.out.println("没有读到字节忽略");
                        }
                    }
                }else {
                    System.out.println("wuxiao");
                }
            }
        }
    }
}
