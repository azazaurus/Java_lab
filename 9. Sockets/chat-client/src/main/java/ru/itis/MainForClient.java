package ru.itis;

import com.beust.jcommander.*;

import java.io.*;

public class MainForClient {
    public static void main(String[] argv) throws IOException {
        Args args = new Args();

        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argv);


        final ClientSocket clientSocket = new ClientSocket(args.ip, args.port);
        clientSocket.connect();

        startMessageGetting(clientSocket);
        startMessageSending(clientSocket);
    }

    public static void startMessageGetting(ClientSocket clientSocket) {
        Runnable runnable = () -> {
            while (true) {
            clientSocket.getMessage();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    public static void startMessageSending(ClientSocket clientSocket) {
        Runnable runnable = () -> {
            final BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                try {
                    clientSocket.sendMessage(consoleReader.readLine());
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }
}
