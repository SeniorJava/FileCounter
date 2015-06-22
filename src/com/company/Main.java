package com.company;


import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main  {

    public static void main(String[] args) throws IOException, InterruptedException {
        File fileR = new File(args[0]);
        File fileW = new File(args[1]);
        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();

        BufferedReader reader = new BufferedReader(new FileReader(fileR));

        List<String> strings = new ArrayList<>();

        List<Thread> threads = new ArrayList<>();

        while (reader.ready()) {
            strings.add(reader.readLine());
        }

        ThreadGroup threadGroup = new ThreadGroup("myGroup");

        for (int i = 0; i < strings.size(); i++) {
            threads.add(new Thread(threadGroup,new MyThreads(strings.get(i), new FileVisitCounter(), fileW.getName()),String.valueOf(i+1)));
        }

        for (Thread t : threads) {

//            if (!MyThreads.isFlag()) break;
////           t.start();
//            if (keyboardObserver.hasKeyEvents()) {
//                KeyEvent event = keyboardObserver.getEventFromTop();
//                if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
//                    threadGroup.interrupt();
//                    MyThreads.setFlag(false);
//                    break;
//                }
//            }
            t.start();

        }

        while (threadGroup.activeCount() > 0) {
            if (keyboardObserver.hasKeyEvents()) {
                KeyEvent event = keyboardObserver.getEventFromTop();
                if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    threadGroup.interrupt();
                   break;
                }
            }
        }

//        while (threadGroup.activeCount() > 0) {
//            Thread.sleep(1);
//        }
        new MyCSVWriter(fileW.getName()).writerToCSV();


    }

}
