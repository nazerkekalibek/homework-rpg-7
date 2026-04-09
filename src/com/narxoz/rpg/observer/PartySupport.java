package com.narxoz.rpg.observer;

public class PartySupport implements GameObserver{
    public void onEvent(GameEvent e){
        if(e.getType()==GameEventType.HERO_LOW_HP) {
            System.out.println("Party support activated!");
        }
    }
}
