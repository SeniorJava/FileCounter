package com.company;

import javenue.csv.Csv;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by senior on 19.06.15.
 */
public class MyCSVWriter {

    private static Csv.Writer writer;
    private static HashMap<String, Long> results = new HashMap<>();

    public  void putResults(String path, long files) {
        results.put(path, files);
    }

    public MyCSVWriter(String fileForWrite) throws IOException {
        if (writer == null) writer = new Csv.Writer(new File(fileForWrite)).delimiter(';');
    }

    public void writerToCSV() {
        for (Map.Entry<String, Long> m : results.entrySet()) {
            writer.value(m.getKey());
            writer.value(String.valueOf(m.getValue()));
            writer.newLine();

        }
        writer.flush();

    }


}
