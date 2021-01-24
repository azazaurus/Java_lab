package ru.itis.image;

import com.beust.jcommander.JCommander;
public class Main {
    public static void main(String[] argv) {

    	Args args = new Args();

    	JCommander.newBuilder()
    	.addObject(args)
    	.build()
    	.parse(argv);
        ImageDownloader imageDownloader = new ImageDownloader();
        imageDownloader.download(args.mode, args.count, args.files, args.folder);
/*
        java -jar download.jar --mode=one-thread --files=URL;URL --folder=C:/images

        java -jar downloadl.jar --mode=multi-thread --count=10 --files=URL;URL --folder=C:/images*/
    }
}
