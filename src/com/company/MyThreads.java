package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by senior on 18.06.15.
 */
public class MyThreads implements Runnable {

    private static boolean flag = true;
    private String path;
    private FileVisitCounter fileVisitor;
    private String fileForWrite;
    private long count;

    public static boolean isFlag() {
        return flag;
    }

    public static void setFlag(boolean flag) {
        MyThreads.flag = flag;
    }

    public String getPath() {
        return path;
    }

    public long getCount() {
        return count;
    }

    public MyThreads(String path, FileVisitCounter fileVisitor, String fileForWrite) {
        this.path = path;
        this.fileVisitor = fileVisitor;
        this.fileForWrite = fileForWrite;

    }


    @Override
    public void run() {
        Path p = new File(path).toPath();
        try {
            Files.walkFileTree(p, fileVisitor);
            count = fileVisitor.getCount();

            if (!Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread().getName() + "\t" + this.getCount() + "\t" + this.getPath());
            }
            else {
                flag = false;
                System.out.println("Подсчет прерванн!");
            }
            new  MyCSVWriter(fileForWrite).putResults(path,count);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
