package ru.itis.images.homework;

import ru.itis.threads.homework.ThreadPool;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class ImageDownloader {

    private URL[] delimiter(String UnDelimitedURLs) {
        URL[] URLs = new URL[2];
        String[] delimitedURLs = UnDelimitedURLs.split(";", 2);
        for (int i = 0; i < 2; i++) {
            try {
                URLs[i] = new URL(delimitedURLs[i]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return URLs;
    }

    private void innerDownload(URL url, String fullFileName) {
        try{
            InputStream inputStream = url.openStream();
            OutputStream outputStream = new FileOutputStream(fullFileName);
            byte[] buffer = new byte[2048];

            int length = 0;

            while ((length = inputStream.read(buffer)) != -1) {
                // System.out.println("Buffer Read of length: " + length);
                outputStream.write(buffer, 0, length);
            }

            inputStream.close();
            outputStream.close();

        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    // Функция возвращает массив имён файлов, которые можно безопасно создать
    private List<String> getNewFullFileNames(String filePath, int filesCount) {
        int currentCount = 0;
        int counter = 1;
        List<String> fullFileNames = new ArrayList<>();

        String fullFileName = filePath + File.separator + "img.jpg";
        File file = new File(fullFileName);
        while (currentCount < filesCount) {
            boolean fileExists = file.exists();
            String previousFullFileName = fullFileName;

            counter++;
            fullFileName = filePath + File.separator + "img (" + counter + ")" + ".jpg";
            file = new File(fullFileName);

            if (!fileExists) {
                fullFileNames.add(previousFullFileName);
                ++currentCount;
            }
        }
        return fullFileNames;
    }

    private void threadPoolDownload(int threadsCount ,URL url1, URL url2, String path) {
        List<String> fullFilesNames = getNewFullFileNames(path, 2);

        ThreadPool threadPool = ThreadPool.newPool(threadsCount);
        Runnable task1 = () -> {innerDownload(url1, fullFilesNames.get(0));};
        Runnable task2 = () -> {innerDownload(url2, fullFilesNames.get(1));};

        threadPool.submit(task1);
        threadPool.submit(task2);
    }

    public void download(String mode, String files, String path) {
        URL[] URLs = delimiter(files);
        threadPoolDownload(1, URLs[0], URLs[1], path);
    }

    public void download(String mode, int count, String files, String path) {
        URL[] URLs = delimiter(files);
        threadPoolDownload(count, URLs[0], URLs[1], path);
    }


}
