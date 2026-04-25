package com.narxoz.rpg.state;

import com.narxoz.rpg.combatant.Hero;

public class StunnedState implements HeroState {
    private int turnsLeft = 1;

    @Override
    public String getName() { return "Stunned"; }

    @Override
    public int modifyOutgoingDamage(int basePower) { return 0; }

    @Override
    public int modifyIncomingDamage(int rawDamage) {
        return (int) (rawDamage * 1.5); // + 50 урн
    }

    @Override
    public void onTurnStart(Hero hero) {
        System.out.println("  [Stun] " + hero.getName() + " оглушен и не может двигаться!");
    }

    @Override
    public void onTurnEnd(Hero hero) {
        turnsLeft--;
        if (turnsLeft <= 0) {
            hero.setState(new NormalState());
        }
    }

    @Override
    public boolean canAct() { return false; }
}