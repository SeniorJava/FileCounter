package com.company;

import javax.swing.*;
import java.awt.event.ActionListener;
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

        List<Threads> threads = new ArrayList<>();

        while (reader.ready()) {
            strings.add(reader.readLine());
        }

        for (String s : strings) {
            threads.add(new Threads(s, new FileVisitCounter(), fileW.getName()));
        }




//        for (int i = 0; i < threads.size() ; i++) {
//            System.out.println(i + "\t" + threads.get(i).getCount() + "\t" + threads.get(i).getPath());
//        }


    }

}
