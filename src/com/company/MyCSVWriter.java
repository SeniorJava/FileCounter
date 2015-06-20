package com.company;

import javenue.csv.Csv;
import java.io.File;
import java.io.IOException;

/**
 * Created by senior on 19.06.15.
 */
public class MyCSVWriter {

    private String fileForWrite;
    private String path;
    private long files;
    private static Csv.Writer writer;


    public MyCSVWriter(String fileForWrite, String path, long files) throws IOException {
        this.fileForWrite = fileForWrite;
        this.path = path;
        this.files = files;
        if (writer == null) writer = new Csv.Writer(new File(fileForWrite)).delimiter(';');
    }

    public void writerToCSV() {
        writer.value(path +"   " +  String.valueOf(files));
        writer.newLine();
        writer.flush();

    }


}
