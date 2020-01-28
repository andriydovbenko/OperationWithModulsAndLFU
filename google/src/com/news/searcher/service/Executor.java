package com.news.searcher.service;

import com.news.searcher.model.LFUCache;
import com.news.searcher.repository.Repository;

import java.util.*;

public class Executor {
    private static final String EXIT_KEY = "exit";
    private Scanner scanner;
    private LFUCache lfuCacheList;
    private List<String> allEvents;
    private List<String> checkList;

    public Executor() {
        Repository repository = new Repository();
        this.lfuCacheList = repository.getLfuList();
        this.allEvents = repository.getAllEvents();
        this.checkList = repository.getCheckList();
        this.scanner = new Scanner(System.in);
    }

    public void startApp() {
        printAllEventsList();
        startSelectingEventsFromConsole();
        printFLUCacheListIntoConsole();
    }

    private void printAllEventsList() {
        for (int i = 0; i < allEvents.size(); i++) {
            System.out.println("event key= " + (i + 1) + " " + allEvents.get(i));
        }
    }

    private void startSelectingEventsFromConsole() {
        System.out.println("\nTo stop selecting events you need to enter: 'exit'\n");
        boolean inputStatus = true;
        while (inputStatus) {
            String input = scanner.next();
            inputStatus = putTheEventIntoMap(input);
        }
    }

    private boolean putTheEventIntoMap(String input) {
        boolean inputStatus = true;

        if (input.equals(EXIT_KEY)) {
            inputStatus = false;
            scanner.close();
        } else if (checkList.contains(input)) {
            System.out.println("News is added to search list");
            int item = Integer.parseInt(input) - 1;
            lfuCacheList.put(input, allEvents.get(item));
        } else {
            System.out.println("Incorrect input. Try again");
        }
        return inputStatus;
    }

    private void printFLUCacheListIntoConsole() {
        System.out.println("\n" + Repository.getCAPACITY() + " the most popular news :\n" +
                lfuCacheList.toString());
    }
}