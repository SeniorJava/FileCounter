package com.company;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by senior on 18.06.15.
 */
public class FileVisitCounter extends SimpleFileVisitor<Path> {
    private long count = 0;

    public long getCount() {
        return count;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        count++;
        return FileVisitResult.CONTINUE;
    }
}
