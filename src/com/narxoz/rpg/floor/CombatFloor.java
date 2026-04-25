package com.narxoz.rpg.floor;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.combatant.Monster;
import java.util.List;

public class CombatFloor extends TowerFloor {
    private Monster monster;

    @Override
    protected String getFloorName() { return "Monster Den"; }

    @Override
    protected void setup(List<Hero> party) {
        monster = new Monster("Goblin Brawler", 40, 12);
        System.out.println("  [Setup] Появился монстр: " + monster.getName() + " (HP: " + monster.getHp() + ")");
    }

    @Override
    protected FloorResult resolveChallenge(List<Hero> party) {
        System.out.println("  [Challenge] Начинается бой!");
        int totalDamageTaken = 0;

        while (monster.isAlive() && party.stream().anyMatch(Hero::isAlive)) {
            for (Hero hero : party) {
                if (!hero.isAlive()) continue;

                hero.getState().onTurnStart(hero);

                if (hero.getState().canAct() && monster.isAlive()) {
                    int damage = hero.getState().modifyOutgoingDamage(hero.getAttackPower());
                    monster.takeDamage(damage);
                    System.out.println("    " + hero.getName() + " бьет " + monster.getName() + " на " + damage + " урона.");
                }

                hero.getState().onTurnEnd(hero);
            }

            if (monster.isAlive()) {
                Hero target = party.stream().filter(Hero::isAlive).findFirst().orElse(null);
                if (target != null) {
                    int hpBefore = target.getHp();
                    monster.attack(target);
                    int damageDealt = hpBefore - target.getHp();
                    totalDamageTaken += damageDealt;
                    System.out.println("    " + monster.getName() + " атакует " + target.getName() + " на " + damageDealt + " урона. (Осталось HP: " + target.getHp() + ")");
                }
            }
        }

        boolean cleared = !monster.isAlive();
        return new FloorResult(cleared, totalDamageTaken, cleared ? "Монстр побежден" : "Партия разбита");
    }

    @Override
    protected void awardLoot(List<Hero> party, FloorResult result) {
        System.out.println("  [Loot] Найдено 50 золотых монет в логове монстра!");
    }
}