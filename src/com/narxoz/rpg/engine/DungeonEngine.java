package com.narxoz.rpg.engine;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.observer.*;
import java.util.*;

public class DungeonEngine {
    private final List<Hero> heroes;
    private final DungeonBoss boss;
    private final EventPublisher publisher;

    public DungeonEngine(List<Hero> heroes, DungeonBoss boss, EventPublisher publisher) {
        this.heroes=heroes;
        this.boss= boss;
        this.publisher=publisher;
    }
    public EncounterResult run(){
        int round=0;
        while(boss.isAlive() && heroes.stream().anyMatch(Hero::isAlive) && round<20) {
            round++;
            for(Hero h : heroes){
                if (!h.isAlive()) continue;
                int dmg=h.attack();
                boss.takeDamage(dmg);
                publisher.notifyObservers(
                        new GameEvent(GameEventType.ATTACK_LANDED, h.getName(), dmg));
            }
            for (Hero h : heroes){
                if (!h.isAlive()) continue;
                int dmg=boss.attack();
                int finalDmg=h.defend(dmg);
                h.takeDamage(finalDmg);
            }
        }
        return new EncounterResult(
                !boss.isAlive(),
                round,
                (int) heroes.stream().filter(Hero::isAlive).count()
        );
    }
}
