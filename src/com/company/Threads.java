package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by senior on 18.06.15.
 */
public class Threads implements Runnable {

    private String path;
    private FileVisitCounter fileVisitor;
    private String fileForWrite;
    private long count;
    private MyCSVWriter myCSVWriter;

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
        this.run();
    }

    private static int var = 0;
    @Override
    public void run() {
        Path p = new File(path).toPath();
        try {
            Files.walkFileTree(p, fileVisitor);
            count = fileVisitor.getCount();
            new MyCSVWriter(fileForWrite,path,count).writerToCSV();
            System.out.println(var++ + "\t" + this.getCount() + "\t" + this.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
