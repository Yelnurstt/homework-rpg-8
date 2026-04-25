package com.narxoz.rpg.floor;

import com.narxoz.rpg.combatant.Hero;
import java.util.List;

public class RestFloor extends TowerFloor {

    @Override
    protected String getFloorName() { return "Safe Haven"; }

    @Override
    protected void announce() {
        System.out.println("\n=== Вы чувствуете тепло. Это " + getFloorName() + " ===");
    }

    @Override
    protected void setup(List<Hero> party) {
        System.out.println("  [Setup] Партия разжигает костер.");
    }

    @Override
    protected FloorResult resolveChallenge(List<Hero> party) {
        System.out.println("  [Challenge] Герои отдыхают у костра.");
        for (Hero hero : party) {
            if (hero.isAlive()) {
                hero.heal(20);
                System.out.println("    " + hero.getName() + " восстанавливает 20 HP. (Текущее HP: " + hero.getHp() + ")");
            }
        }
        return new FloorResult(true, 0, "Партия успешно отдохнула.");
    }

    @Override
    protected boolean shouldAwardLoot(FloorResult result) {
        return false;
    }

    @Override
    protected void awardLoot(List<Hero> party, FloorResult result) { }
}