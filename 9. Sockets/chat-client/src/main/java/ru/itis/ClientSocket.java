package ru.itis;

import java.io.*;
import java.net.*;

public class ClientSocket {
    private final String host;
    private final int port;
    private Socket client;

    private PrintWriter toServer;
    private BufferedReader fromServer;

    public ClientSocket(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect() {
        try {
            client = new Socket(host, port);
            toServer = new PrintWriter(client.getOutputStream(), true);
            fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void sendMessage(String message) {
        toServer.println(message);
    }

    public void getMessage() {
        try {
            if (fromServer.ready())
            System.out.println(fromServer.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
