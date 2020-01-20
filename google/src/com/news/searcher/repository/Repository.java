package com.news.searcher.repository;

import com.news.searcher.model.LFUCache;
import com.news.searcher.model.Text;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private static final int CAPACITY = 5;
    private LFUCache lfuList;
    private List<String> allEvents;
    private List<String> checkList;

    public Repository() {
        this.lfuList = new LFUCache(CAPACITY);
        this.checkList = new ArrayList<>(List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
        this.allEvents = new ArrayList<>(List.of(
                Text.VIRUS.getInfo(),
                Text.TRUMP.getInfo(),
                Text.BEIRUT.getInfo(),
                Text.COFFEE.getInfo(),
                Text.AUSTRALIA.getInfo(),
                Text.VOLCANO.getInfo(),
                Text.UK.getInfo(),
                Text.RIVER.getInfo(),
                Text.WORLD.getInfo(),
                Text.CLIMAT.getInfo())
        );
    }

    public LFUCache getLfuList() {
        return lfuList;
    }

    public List<String> getAllEvents() {
        return allEvents;
    }

    public List<String> getCheckList() {
        return checkList;
    }

    public static int getCAPACITY() {
        return CAPACITY;
    }
}
