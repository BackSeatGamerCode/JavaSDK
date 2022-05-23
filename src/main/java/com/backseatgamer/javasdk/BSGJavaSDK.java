package com.backseatgamer.javasdk;

import com.backseatgamer.javasdk.events.BaseEvent;
import com.backseatgamer.javasdk.models.Redemption;
import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public abstract class BSGJavaSDK extends Thread {
    protected final Queue<Redemption> eventQueue = new LinkedList<>();
    private final String HOST = "127.0.0.1";
    private final int PORT = 29175;
    private final Gson gson = new Gson();
    private final long INTERVAL = 1;

    private void startThread(){
        setDaemon(true);
        setName("BackSeatGamer Server Poll Thread");
        start();
    }

    public BSGJavaSDK() {
        startThread();
    }

    public void run() {
        try(
                ServerSocket socketServer = new ServerSocket(PORT);
                Socket socket = socketServer.accept()
        ) {
            while (true){
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String line = reader.readLine();

                System.out.println(line);

                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
                writer.println("done");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract void onRedemptionReceived(Redemption redemption, Object... args);

    protected abstract BaseEvent getEvent(Redemption redemption);
}
