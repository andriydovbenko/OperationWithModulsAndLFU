package com.news.searcher.model;

import java.util.Objects;

public class Event {
    private final String eventInformation;
    private int frequency = 1;

    public Event(String eventInformation) {
        this.eventInformation = eventInformation;
    }

    public String getEventInformation() {
        return eventInformation;
    }

    public int getFrequency() {
        return frequency;
    }

    public Event incrementFrequency() {
        ++frequency;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return eventInformation.equals(event.eventInformation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventInformation);
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventInformation='" + eventInformation + '\'' +
                ", frequency=" + frequency +
                '}' + "\n";
    }
}