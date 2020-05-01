package com.github.cooker.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * grant
 * 28/4/2020 9:24 上午
 * 描述：
 */
public class BioClient {
    public static void main(String[] args) {

        Socket socket = null;
        BufferedReader reader = null;
        PrintWriter writer = null;

        try {
            socket = new Socket("127.0.0.1", Contants.port);
        } catch (IOException e) {
            System.out.println("客户端初始化失败");
        }
        try {
            writer = new PrintWriter(socket.getOutputStream(),true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println("输入输出流获取失败");
        }
        writer.println("QUERY TIME ORDER");
        System.out.println("查询语句发出");

        try {
            String readLine = reader.readLine();
            System.out.println("Now time is " + readLine);
        } catch (IOException e) {
            System.out.println("读取失败");
        }finally {
            if(writer != null){
                writer.close();
                writer = null;
            }
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                reader = null;
            }
        }
    }

}
