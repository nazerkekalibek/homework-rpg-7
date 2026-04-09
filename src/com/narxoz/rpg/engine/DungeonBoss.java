package com.narxoz.rpg.engine;

import com.narxoz.rpg.observer.*;
import com.narxoz.rpg.strategy.*;

public class DungeonBoss{
    private int hp;
    private final int maxHp;
    private CombatStrategy strategy;
    private EventPublisher publisher;

    public DungeonBoss(int hp){
        this.hp=hp;
        this.maxHp=hp;
        this.strategy=new BalancedStrategy();
    }
    public void setPublisher(EventPublisher publisher){
        this.publisher=publisher;
    }
    public int attack(){
        return strategy.calculateDamage(20);
    }
    public void takeDamage(int dmg){
        hp=Math.max(0, hp-dmg);
        int percent =hp*100/maxHp;
        if(publisher!=null){
            if(percent<=60 && percent>30){
                strategy =new AggressiveStrategy();
                publisher.notifyObservers(
                        new GameEvent(GameEventType.BOSS_PHASE_CHANGED, "Boss", 2));
            } 
            else if(percent<=30){
                strategy=new DefensiveStrategy();
                publisher.notifyObservers(
                        new GameEvent(GameEventType.BOSS_PHASE_CHANGED, "Boss", 3));
            }
            if(hp==0){
                publisher.notifyObservers(
                        new GameEvent(GameEventType.BOSS_DEFEATED, "Boss", 0));
            }
        }
    }
    public boolean isAlive() { 
        return hp>0; 
    }
}
