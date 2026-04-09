package com.narxoz.rpg.observer;

public class HeroStatusMonitor implements GameObserver{
    public void onEvent(GameEvent e){
        if(e.getType()==GameEventType.HERO_DIED){
            System.out.println("💀 "+e.getSourceName()+" has died!");
        }
    }
}
