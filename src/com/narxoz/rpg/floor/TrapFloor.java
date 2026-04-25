package com.narxoz.rpg.floor;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.state.PoisonedState;
import java.util.List;

public class TrapFloor extends TowerFloor {

    @Override
    protected String getFloorName() { return "Poison Gas Chamber"; }

    @Override
    protected void setup(List<Hero> party) {
        System.out.println("  [Setup] Комната заполняется зеленым газом...");
    }

    @Override
    protected FloorResult resolveChallenge(List<Hero> party) {
        int damageTaken = 0;
        for (Hero hero : party) {
            if (hero.isAlive()) {
                System.out.println("  [Challenge] " + hero.getName() + " вдыхает ядовитый газ!");
                hero.setState(new PoisonedState());
            }
        }
        return new FloorResult(true, damageTaken, "Партия прошла через ловушку, но была отравлена.");
    }

    @Override
    protected boolean shouldAwardLoot(FloorResult result) {
        return false;
    }

    @Override
    protected void awardLoot(List<Hero> party, FloorResult result) {
    }

    @Override
    protected void cleanup(List<Hero> party) {
        System.out.println("  [Cleanup] Газ медленно рассеивается из комнаты.");
    }
}