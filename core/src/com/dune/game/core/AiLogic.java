package com.dune.game.core;

import com.dune.game.core.units.AbstractUnit;
import com.dune.game.core.units.UnitType;

import java.util.List;

public class AiLogic {
    private GameController gc;

    public AiLogic(GameController gc) {
        this.gc = gc;
    }

    public void update(float dt) {
        List<AbstractUnit> aiUnit = gc.getUnitsController().getAiUnits();

        for (int i = 0; i < aiUnit.size(); i++) {
            AbstractUnit u = aiUnit.get(i);
            unitProcessing(u);
        }
    }

    public void unitProcessing(AbstractUnit unit) {
        if (unit.getUnitType() == UnitType.HARVESTER) {
            if (unit.attacked) {
                // не смог придумать как просить о помощи от боевых танков.
            }
        }
        if (unit.getUnitType() == UnitType.BATTLE_TANK) {

            unit.commandAttack(getTargetForAttack(unit));

            if (checkHelp() != null) {
                unit.commandAttack(checkHelp().getTarget());
            }
        }
    }

    private AbstractUnit checkHelp() {
        for (int i = 0; i < gc.getUnitsController().getAiUnits().size(); i++) {
            AbstractUnit u = gc.getUnitsController().getAiUnits().get(i);
            if (u.getHp() <= u.getHpMax() / 2) {
                return u;
            }
        }
        return null;
    }

    public AbstractUnit getTargetForAttack(AbstractUnit unit) {
            for (int j = 0; j < gc.getUnitsController().getPlayerUnits().size(); j++) {
                AbstractUnit playerUnit = gc.getUnitsController().getPlayerUnits().get(j);
                //проверка на атакующего
                if (playerUnit.getTarget() == unit) {
                    return playerUnit;
                }
                // проверка что рядом нет врагов
                if (unit.position.dst(playerUnit.position) < 250){
                    return playerUnit;
                }
            }
        return null;
    }

}
