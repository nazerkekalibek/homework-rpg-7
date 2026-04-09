package com.narxoz.rpg.combatant;

import com.narxoz.rpg.observer.EventPublisher;
import com.narxoz.rpg.observer.GameEvent;
import com.narxoz.rpg.observer.GameEventType;
import com.narxoz.rpg.strategy.CombatStrategy;

public class Hero {
    private final String name;
    private int hp;
    private final int maxHp;
    private final int attackPower;
    private final int defense;
    private CombatStrategy strategy;
    private EventPublisher publisher;

    public Hero(String name, int hp, int attackPower, int defense) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attackPower = attackPower;
        this.defense = defense;
    }

    public void setStrategy(CombatStrategy strategy){
        this.strategy=strategy;
    }
    public void setPublisher(EventPublisher publisher){
        this.publisher=publisher;
    }
    public int attack(){
        return strategy.calculateDamage(attackPower);
    }

    public int defend(int incoming){
        int reduced=strategy.calculateDefense(defense);
        return Math.max(0, incoming-reduced);
    }
    public void takeDamage(int dmg){
        hp=Math.max(0, hp-dmg);
        if(publisher!=null) {
            if(hp>0 && hp<maxHp*0.3){
                publisher.notifyObservers(
                        new GameEvent(GameEventType.HERO_LOW_HP, name, hp));
            }
            if(hp==0){
                publisher.notifyObservers(
                        new GameEvent(GameEventType.HERO_DIED, name, 0));
            }
        }
    }

    public boolean isAlive() { 
        return hp>0; 
    }
    public String getName() { 
        return name; 
    }
    public int getHp() { 
        return hp; 
    }
}
