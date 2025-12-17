package pxlMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ReaperEffect;
import pxlMod.character.Heretic;
import pxlMod.powers.ApostasyPower;
import pxlMod.powers.CurseOfRecoilPower;
import pxlMod.util.CardStats;

public class Preachers_Assault extends BaseCard{
    public static final String ID = makeID(Preachers_Assault.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Heretic.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            2
    );

    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 3;
    private static final int MAGIC = 1;

    public Preachers_Assault() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(MAGIC);

        setExhaust(true);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int targetApo = m.getPower(ApostasyPower.POWER_ID).amount;
        if(targetApo < 1) { targetApo = 0;}

        this.addToBot(new VFXAction(new ReaperEffect()));
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        addToBot(new HealAction(p, p, targetApo));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Preachers_Assault();
    }

}
