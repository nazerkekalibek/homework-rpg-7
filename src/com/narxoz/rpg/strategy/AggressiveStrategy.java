package com.narxoz.rpg.strategy;

public class AggressiveStrategy implements CombatStrategy {
    public int calculateDamage(int basePower) { 
        return (int)(basePower * 2.5); 
    }
    public int calculateDefense(int baseDefense) { 
        return (int)(baseDefense * 1.5); 
    }
    public String getName() { 
        return "Aggressive"; 
    }

}
