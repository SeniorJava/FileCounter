package com.company;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main  {

    public static void main(String[] args) throws IOException
    {
        File fileR = new File(args[0]);
        File fileW = new File(args[1]);

        BufferedReader reader = new BufferedReader(new FileReader(fileR));

        List<String> strings = new ArrayList<>();

        List<Thread> threads = new ArrayList<>();

        while (reader.ready()) {
            strings.add(reader.readLine());
        }

        for (int i = 0; i < strings.size(); i++) {
            threads.add(new Thread(new Threads(strings.get(i), new FileVisitCounter(), fileW.getName()),String.valueOf(i)));
        }

        for (Thread t : threads) {
            t.start();
        }



//        for (int i = 0; i < threads.size() ; i++) {
//            System.out.println(i + "\t" + threads.get(i).getCount() + "\t" + threads.get(i).getPath());
//        }


    }

}
