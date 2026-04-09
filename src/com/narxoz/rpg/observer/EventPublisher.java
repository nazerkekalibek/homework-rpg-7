package com.narxoz.rpg.observer;

import java.util.*;

public class EventPublisher {
    private final List<GameObserver> observers=new ArrayList<>();
    public void subscribe(GameObserver observer){
        observers.add(observer);
    }

    public void notifyObservers(GameEvent event) {
        for(GameObserver o : observers){
            o.onEvent(event);
        }
    }

}
