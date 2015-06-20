package com.company;

import java.io.File;
import java.util.HashSet;

/**
 * Created by senior on 18.06.15.
 */
public class FileCounter {

    HashSet<File> setFiles = new HashSet<>();

    public long counter(File file)
    {
        File[] listFiles = file.listFiles();

        for (File f : listFiles) {
            if (f.isDirectory()) counter(f);
            else setFiles.add(f);
//            System.out.println(f.getName());
        }
        return setFiles.size();
    }
}
