package ru.itis;

import com.beust.jcommander.*;

@Parameters(separators = "=")
public class Args {
    @Parameter(names = {"--port"})
    public int port;
}
