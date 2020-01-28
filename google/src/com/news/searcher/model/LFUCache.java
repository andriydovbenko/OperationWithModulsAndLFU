package com.news.searcher.model;

import java.util.LinkedHashMap;
import java.util.Map;

import java.util.Optional;

public class LFUCache {
    private static final int SMALLEST_FREQUENCY = 1;
    private static final int PERMISSIBLE_FREQUENCY = 2;
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
            if (minFrequency < PERMISSIBLE_FREQUENCY) {
                String key = findKeyOfFirstLessPopularEvent(minFrequency);
                repository.remove(key);
                repository.put(stringKey, new Event(eventInformation));
            }
        }
    }

    private int findMinFrequencyInRepository() {
        Optional<Integer> minFrequency = repository.values()
                .stream()
                .map(Event::getFrequency)
                .min(Integer::compareTo);

        return minFrequency.orElse(SMALLEST_FREQUENCY);
    }

    private String findKeyOfFirstLessPopularEvent(int minFrequency) {
        Optional<String> key = repository.entrySet()
                .stream()
                .filter(value -> value.getValue().getFrequency() == minFrequency)
                .findFirst().map(Map.Entry::getKey);

        return key.orElse(null);
    }

    @Override
    public String toString() {
        return repository + ", capacity=" + capacity;
    }
}