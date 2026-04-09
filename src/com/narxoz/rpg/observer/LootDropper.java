package com.narxoz.rpg.observer;

public class LootDropper implements GameObserver{
    public void onEvent(GameEvent e){
        if (e.getType()==GameEventType.BOSS_DEFEATED){
            System.out.println("Boss dropped epic loot!");
        }
    }
}
