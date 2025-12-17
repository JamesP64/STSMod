package pxlMod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase.COMBAT;
import static pxlMod.BasicMod.makeID;

public class DrawDownPower extends BasePower {
    public static final String POWER_ID = makeID("DrawDownPower");
    private static final AbstractPower.PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    private int reductionApplied = 0;

    public DrawDownPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.updateDescription();
    }

    @Override
    public void onInitialApplication() {
        if (shouldApplyReduction()) {
            reductionApplied = Math.min(amount, AbstractDungeon.player.masterHandSize);
            AbstractDungeon.player.masterHandSize -= reductionApplied;
        }
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);

        if (shouldApplyReduction()) {
            int additionalReduction = Math.min(stackAmount, AbstractDungeon.player.masterHandSize);
            reductionApplied += additionalReduction;
            AbstractDungeon.player.masterHandSize -= additionalReduction;
        }
    }

    @Override
    public void onRemove() {
        if (shouldApplyReduction() && reductionApplied > 0) {
            AbstractDungeon.player.masterHandSize += reductionApplied;
        }
    }

    @Override
    public void onVictory() {
        if (reductionApplied > 0) {
            AbstractDungeon.player.masterHandSize += reductionApplied;
            reductionApplied = 0;
        }
    }

    private boolean shouldApplyReduction() {
        return AbstractDungeon.player != null &&
                AbstractDungeon.getCurrRoom().phase == COMBAT;
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            this.description = "At the start of your turn, draw #b1 less card.";
        } else {
            this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        }
    }
}