package pxlMod.powers;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.lang.reflect.Field;

import static pxlMod.BasicMod.makeID;

public class CurseOfRecoilPower extends BasePower {
    public static final String POWER_ID = makeID("CurseOfRecoilPower");
    private static final AbstractPower.PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = true;
    private int reflectedDamageForThisTurn;

    public CurseOfRecoilPower(AbstractCreature source, AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        updateDescription();
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        if (owner instanceof AbstractMonster) {
            AbstractMonster m = (AbstractMonster) owner;
            int base = m.getIntentBaseDmg();
            if (base < 0) base = 0;
            int mult = isMultiDmg(m) ? getIntentMultiAmt(m) : 1;

            int total = base * mult;
            this.reflectedDamageForThisTurn = (int)(total * (this.amount * 0.1f));
        } else {
            this.reflectedDamageForThisTurn = 0;
        }

        updateDescription();
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (reflectedDamageForThisTurn > 0 && info.owner == this.owner && target != this.owner) {
            this.flash();
            this.addToBot(new DamageAction(this.owner,
                    new DamageInfo(this.owner, reflectedDamageForThisTurn, DamageInfo.DamageType.THORNS)));
            reflectedDamageForThisTurn = 0;
            updateDescription();
        }
    }

    @Override
    public void updateDescription() {
        int recoilPercentage = this.amount * 10;
        this.description = DESCRIPTIONS[0] + recoilPercentage + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurnPostDraw() {
        updateDescription();
    }


    public static boolean isMultiDmg(AbstractMonster m) {
        try {
            Field f = AbstractMonster.class.getDeclaredField("isMultiDmg");
            f.setAccessible(true);
            return f.getBoolean(m);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int getIntentMultiAmt(AbstractMonster m) {
        try {
            Field f = AbstractMonster.class.getDeclaredField("intentMultiAmt");
            f.setAccessible(true);
            return f.getInt(m);
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

}
