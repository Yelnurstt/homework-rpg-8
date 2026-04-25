package com.narxoz.rpg.state;

import com.narxoz.rpg.combatant.Hero;

public class PoisonedState implements HeroState {
    private int turnsLeft = 3;

    @Override
    public String getName() { return "Poisoned"; }

    @Override
    public int modifyOutgoingDamage(int basePower) {
        return (int) (basePower * 0.7); // - 30 урн
    }

    @Override
    public int modifyIncomingDamage(int rawDamage) { return rawDamage; }

    @Override
    public void onTurnStart(Hero hero) {
        System.out.println("  [Poison] " + hero.getName() + " получает 5 урона от яда!");
        hero.takeDamage(5);
    }

    @Override
    public void onTurnEnd(Hero hero) {
        turnsLeft--;
        if (turnsLeft <= 0) {
            System.out.println("  [Poison] Действие яда на " + hero.getName() + " закончилось.");
            hero.setState(new NormalState());
        }
    }

    @Override
    public boolean canAct() { return true; }
}