package com.uok.backend.announcement.observer;

import com.uok.backend.announcement.Announcement;

import java.util.ArrayList;
import java.util.List;

public class Subject {

    List<Observer> observers = new ArrayList<>();

    public void subscribe(Observer o) {
        observers.add(o);
    }

    public void unSubscribe(Observer o) {
        observers.remove(o);
    }

    public void notifyAllObservers(Announcement announcement) {

        for (Observer o : observers) {
            o.notifyObserver(announcement);
        }

    }

}
