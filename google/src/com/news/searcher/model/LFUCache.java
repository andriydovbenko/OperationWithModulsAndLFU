package com.news.searcher.model;

import java.util.LinkedHashMap;
import java.util.Map;

import java.util.Optional;

public class LFUCache {
    private final Map<String, Event> repository;
    private final int capacity;

    public LFUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity can not be less than 0 ");
        }
        this.capacity = capacity;
        this.repository = new LinkedHashMap<>(capacity);
    }

    public void put(String stringKey, String eventInformation) {
        if (repository.containsKey(stringKey)) {
            repository.get(stringKey).incrementFrequency();
        } else if (repository.size() < capacity) {
            repository.put(stringKey, new Event(eventInformation));
        } else {
            int minFrequency = findMinFrequencyInRepository();
            if (minFrequency < 2) {
                String key = findKeyOfFirstLessPopularEvent(minFrequency);
                repository.remove(key);
                repository.put(stringKey, new Event(eventInformation));
            }
        }
    }

    private int findMinFrequencyInRepository() {
        int minFrequency = 1;
        Optional<Integer> min = repository.values()
                .stream()
                .map(Event::getFrequency)
                .min(Integer::compareTo);
        if (min.isPresent()) {
            minFrequency = min.get();
        }
        return minFrequency;
    }

    private String findKeyOfFirstLessPopularEvent(int minFrequency) {
        String minKey = null;
        Optional<String> key = repository.entrySet()
                .stream()
                .filter(value -> value.getValue().getFrequency() == minFrequency)
                .findFirst().map(Map.Entry::getKey);
        if (key.isPresent()) {
            minKey = key.get();
        }
        return minKey;
    }

    @Override
    public String toString() {
        return repository + ", capacity=" + capacity;
    }
}