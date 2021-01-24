package ru.itis;

import java.io.*;
import java.net.*;
import java.util.*;


public class MyServerSocket {
    private ArrayList<Socket> clientList = new ArrayList<>();

    public void start(int port) {
        ServerSocket serverSocket;

        try {
            Socket client;
            serverSocket = new ServerSocket(port);

            boolean isServerListening = true;
            while (isServerListening) {
                // сервер ждет подключения клиентов
                client = serverSocket.accept();
                startThreadForClient(client);
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private void startThreadForClient(Socket client) throws IOException {
        Runnable runnable = () -> {
            BufferedReader fromClient = null;
            try {
                fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
            PrintWriter toClient = null;
            try {
                toClient = new PrintWriter(client.getOutputStream(), true);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }

            toClient.println("Ваш ник: ");
            String clientName = null;
            try {
                clientName = fromClient.readLine();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }

            clientList.add(client);

            boolean isClientListening = true;
            while (isClientListening) {

                String messageFromClient = null;

                try {
                    // прочитать сообщение от клиента
                    messageFromClient = clientName + ": " + fromClient.readLine();
                } catch (IOException e) {
                    isClientListening = false;
                }

                System.out.println(messageFromClient);

                // разослать сообщение всем остальным клиентам
                for (Socket clientSocket: clientList) {
                    if (clientSocket == client) {
                        continue;
                    }
                    else {
                        try {
                            PrintWriter toOtherClient = new PrintWriter(clientSocket.getOutputStream(), true);
                            toOtherClient.println(messageFromClient);
                        } catch (IOException e) {
                            throw new IllegalStateException(e);
                        }
                    }
                }
            }
        };
        // создать поток
        Thread thread = new Thread(runnable);
        // запустить поток
        thread.start();
    }
}
