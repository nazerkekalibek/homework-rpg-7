package com.narxoz.rpg;

import com.narxoz.rpg.combatant.*;
import com.narxoz.rpg.engine.*;
import com.narxoz.rpg.observer.*;
import com.narxoz.rpg.strategy.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        EventPublisher publisher=new EventPublisher();

        publisher.subscribe(new BattleLogger());
        publisher.subscribe(new AchievementTracker());
        publisher.subscribe(new PartySupport());
        publisher.subscribe(new HeroStatusMonitor());
        publisher.subscribe(new LootDropper());

        Hero h1 =new Hero("Knight", 111, 24, 13);
        Hero h2=new Hero("Archer", 85, 14, 7);
        Hero h3=new Hero("Tank", 140, 26, 20);

        h1.setStrategy(new BalancedStrategy());
        h2.setStrategy(new AggressiveStrategy());
        h3.setStrategy(new DefensiveStrategy());

        h1.setPublisher(publisher);
        h2.setPublisher(publisher);
        h3.setPublisher(publisher);

        DungeonBoss boss=new DungeonBoss(200);
        boss.setPublisher(publisher);

        DungeonEngine engine=new DungeonEngine(
                Arrays.asList(h1, h2, h3),
                boss,
                publisher
        );

        EncounterResult result=engine.run();

        System.out.println("\n=== RESULT ===");
        System.out.println("Heroes won: "+result.isHeroesWon());
        System.out.println("Rounds: "+result.getRoundsPlayed());
        System.out.println("Survivors: "+result.getSurvivingHeroes());
    }
}


//====== STRATEGY PATTERN ======
//CombatStrategy (interface)
// ├── AggressiveStrategy
// ├── DefensiveStrategy
// └── BalancedStrategy
//Hero → uses → CombatStrategy


//====== OBSERVER PATTERN ======
//GameObserver (interface)
// ├── BattleLogger
// ├── AchievementTracker
// ├── PartySupport
// ├── HeroStatusMonitor
// └── LootDropper
//EventPublisher → notifies → GameObserver
//Hero & Boss → send events → EventPublisher
