package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by senior on 18.06.15.
 */
public class Threads implements Runnable {

    private String path;
    private FileVisitCounter fileVisitor;
    private String fileForWrite;
    private long count;
    private static AtomicInteger var = new AtomicInteger(0);
    private Thread th;

    public void setCount(long count) {
        this.count = count;
    }

    public String getPath() {
        return path;
    }

    public FileVisitCounter getFileVisitor() {
        return fileVisitor;
    }

    public long getCount() {
        return count;
    }

    public Threads(String path, FileVisitCounter fileVisitor, String fileForWrite) {
        this.path = path;
        this.fileVisitor = fileVisitor;
        this.fileForWrite = fileForWrite;
//        th = new Thread(this,String.valueOf(var));
//        th.start();
    }


    @Override
    public void run() {
        Path p = new File(path).toPath();
        try {
            var.incrementAndGet();
            Files.walkFileTree(p, fileVisitor);
            count = fileVisitor.getCount();

            System.out.println(Thread.currentThread().getName() + "\t" + this.getCount() + "\t" + this.getPath());
            new MyCSVWriter(fileForWrite,path,count).writerToCSV();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
