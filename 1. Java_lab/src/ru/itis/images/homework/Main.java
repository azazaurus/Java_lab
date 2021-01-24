package ru.itis.images.homework;

public class Main {
    public static void main(String[] args) {
        ImageDownloader imageDownloader = new ImageDownloader();
        imageDownloader.download("multy-thread", 3,
                "https://images.chesscomfiles.com/uploads/v1/group/16986.bb6495d7.1200x1200o.086f0ed9d6f4.jpg;https://i.pinimg.com/736x/09/c4/c4/09c4c42611a0bcf7749c688d4d4831ec--fantasy-creatures-mythical-creatures.jpg",
                "C:\\Users\\yourgraydays\\Documents\\GitHub\\Java_lab");
/*
        java -jar download.jar --mode=one-thread --files=URL;URL --folder=C:/images

        java -jar downloadl.jar --mode=multi-thread --count=10 --files=URL;URL --folder=C:/images*/
    }
}
