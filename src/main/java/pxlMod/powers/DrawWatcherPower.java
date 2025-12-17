package pxlMod.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class DrawWatcherPower extends AbstractPower implements  InvisiblePower {
    public static final String POWER_ID = "modid:DrawWatcher";

    public DrawWatcherPower(AbstractCreature owner) {
        this.name = "Draw Watcher";
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.loadRegion("focus");
        this.priority = -9999;
        this.amount = -1;
        this.updateDescription();
        this.canGoNegative = false;
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (m.hasPower(CurseOfFatePower.POWER_ID)) {
                ((CurseOfFatePower) m.getPower(CurseOfFatePower.POWER_ID)).onPlayerDraw();
            }
        }
    }

    @Override
    public void updateDescription() {
        this.description = "Watches card draws.";
    }
}

