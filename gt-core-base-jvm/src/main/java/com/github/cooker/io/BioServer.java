package com.github.cooker.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * grant
 * 28/4/2020 9:24 上午
 * 描述：
 */
public class BioServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = null;
        try {
            server = new ServerSocket(Contants.port);
            System.out.println("The time server is start in port : " + Contants.port);
            Socket socket = null;
            while (true){
                socket = server.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(server != null){
                System.out.println("The time server close");
                server.close();
                server = null;
            }
        }
    }
}


class TimeServerHandler implements Runnable{
    Socket socket = null;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(), true);
            String currentTime = null;
            String body =  null;
            while (true) {
                body =  in.readLine();
                if (body == null) {
                    break;
                }
                System.out.println("The time server receive order : " + body);
                currentTime = "QUERY TIME ORDER".equals(body) ?
                        new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                out.println(currentTime);
            }
        } catch (IOException e) {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (out != null) {
                out.close();
                out = null;
            }
            if (this.socket != null) {
                try {
                    this.socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                this.socket = null;
            }
        }
    }
}
