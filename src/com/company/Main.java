package com.company;


import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Main  {

    public static void main(String[] args) throws IOException, InterruptedException {
        File fileR = new File(args[0]);
        File fileW = new File(args[1]);
        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();
        boolean isDone  = false;

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

        ExecutorService pool = Executors.newFixedThreadPool(threads.size());
        List<Future> futures = new ArrayList<>();

        for (Thread t : threads) {
            if (MyThreads.isRun()){
                futures.add(pool.submit(t));
            }
            else{
                pool.shutdownNow();
                break;
            }
        }

        while (!pool.isTerminated()) {
            if (keyboardObserver.hasKeyEvents()) {
                KeyEvent event = keyboardObserver.getEventFromTop();
                if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    pool.shutdownNow();
                    new MyCSVWriter(fileW.getName()).writerToCSV();
                    System.out.println("Запись завершена");
                    break;
                }
            }
            if (isDone) {
                new MyCSVWriter(fileW.getName()).writerToCSV();
                System.out.println("Запись завершена");
                break;
            }
            for (Future f : futures) {
                if (!f.isDone()) {
                    isDone = false;
                    break;
                }
                isDone = true;
            }

        }

    }

}
