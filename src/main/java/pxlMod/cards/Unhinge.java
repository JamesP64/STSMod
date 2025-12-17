package pxlMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pxlMod.character.Heretic;
import pxlMod.powers.ApostasyPower;
import pxlMod.powers.ApostasySelfPower;
import pxlMod.util.CardStats;

public class Unhinge extends BaseCard {
    public static final String ID = makeID(Unhinge.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Heretic.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1
    );

    private static final int MAGIC = 6;
    private static final int UPG_MAGIC = 2;

    public Unhinge() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //this.addToBot(new ApplyPowerAction(p, p, new CurseOfMadnessSelfPower());
        this.addToBot(new ApplyPowerAction(p, p, new ApostasySelfPower(p, p, this.magicNumber), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Unhinge();
    }
}
