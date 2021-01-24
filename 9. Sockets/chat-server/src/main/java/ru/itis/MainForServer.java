package ru.itis;

import com.beust.jcommander.*;

public class MainForServer {
    public static void main(String[] argv) {
        Args args = new Args();

        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argv);

        MyServerSocket rocketServer = new MyServerSocket();
        rocketServer.start(args.port);
    }
}
