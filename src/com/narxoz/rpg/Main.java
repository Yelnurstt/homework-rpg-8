package com.narxoz.rpg;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.floor.CombatFloor;
import com.narxoz.rpg.floor.RestFloor;
import com.narxoz.rpg.floor.TowerFloor;
import com.narxoz.rpg.floor.TrapFloor;
import com.narxoz.rpg.state.StunnedState;
import com.narxoz.rpg.tower.TowerRunResult;
import com.narxoz.rpg.tower.TowerRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Hero warrior = new Hero("Aragorn", 100, 20, 5);
        Hero mage = new Hero("Gandalf", 70, 25, 2);

        mage.setState(new StunnedState());

        List<Hero> party = new ArrayList<>(Arrays.asList(warrior, mage));

        List<TowerFloor> floors = new ArrayList<>();
        floors.add(new CombatFloor());
        floors.add(new TrapFloor());
        floors.add(new CombatFloor());
        floors.add(new RestFloor());

        TowerRunner runner = new TowerRunner(floors);
        TowerRunResult result = runner.run(party);

        System.out.println("\n--- РЕЗУЛЬТАТЫ ПОХОДА ---");
        System.out.println("Пройдено этажей: " + result.getFloorsCleared());
        System.out.println("Выжило героев: " + result.getHeroesSurviving());
        System.out.println("Башня пройдена до конца: " + (result.isReachedTop() ? "Да" : "Нет"));
    }
}