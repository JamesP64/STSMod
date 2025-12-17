package pxlMod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static pxlMod.BasicMod.makeID;

public class ApostasySelfPower extends BasePower {
    public static final String POWER_ID = makeID("ApostasySelfPower");
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public ApostasySelfPower(AbstractCreature source, AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);

        if (this.amount >= 10) {
            addToBot(new ApplyPowerAction(owner, source, new StrengthPower(owner, 5), -5));
            amount -= 10;
            updateDescription();

            if (amount <= 0) {
                addToTop(new RemoveSpecificPowerAction(owner, owner, ID));
            }
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}