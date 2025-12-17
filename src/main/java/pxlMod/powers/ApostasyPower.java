package pxlMod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import static pxlMod.BasicMod.makeID;

public class ApostasyPower extends BasePower {
    public static final String POWER_ID = makeID("ApostasyPower");
    private static final AbstractPower.PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public ApostasyPower(AbstractCreature source, AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);

        if (this.amount >= 10) {
            addToBot(new ApplyPowerAction(owner, source, new WeakPower(owner, 99, false), 99));
            addToBot(new ApplyPowerAction(owner, source, new StrengthPower(owner, -5), -5));
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