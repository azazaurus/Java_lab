package ru.itis;

import com.beust.jcommander.*;

@Parameters(separators = "=")
public class Args {
    @Parameter(names = {"--server-ip"})
    public String ip;
    @Parameter(names = {"--server-port"})
    public int port;
}

