package pxlMod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DrawReductionPower;

import static pxlMod.BasicMod.makeID;

public class BloodContractPower extends BasePower {
    public static final String POWER_ID = makeID("BloodContactPower");
    private static final AbstractPower.PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = true;

    private int reductionApplied = 0;

    public BloodContractPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.updateDescription();
    }

    @Override
    public void onInitialApplication() {
        this.addToBot(new ApplyPowerAction(owner, owner, new DrawReductionPower(owner, 1)));
    }

    @Override
    public void atStartOfTurn() {
        this.addToBot(new ApplyPowerAction(owner, owner, new DrawReductionPower(owner, amount)));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
