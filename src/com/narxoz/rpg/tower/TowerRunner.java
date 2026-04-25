package com.narxoz.rpg.tower;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.floor.FloorResult;
import com.narxoz.rpg.floor.TowerFloor;

import java.util.List;

public class TowerRunner {
    private final List<TowerFloor> floors;

    public TowerRunner(List<TowerFloor> floors) {
        this.floors = floors;
    }

    public TowerRunResult run(List<Hero> party) {
        int floorsCleared = 0;

        System.out.println("=== НАЧАЛО ПОКОРЕНИЯ БАШНИ ===");

        for (TowerFloor floor : floors) {
            FloorResult result = floor.explore(party);

            long aliveCount = party.stream().filter(Hero::isAlive).count();
            if (aliveCount == 0 || !result.isCleared()) {
                System.out.println("\n[X] Партия погибла или не смогла пройти этаж.");
                return new TowerRunResult(floorsCleared, 0, false);
            }

            floorsCleared++;
        }

        long finalAlive = party.stream().filter(Hero::isAlive).count();
        System.out.println("\n=== БАШНЯ ПОКОРЕНА ===");
        return new TowerRunResult(floorsCleared, (int) finalAlive, true);
    }
}