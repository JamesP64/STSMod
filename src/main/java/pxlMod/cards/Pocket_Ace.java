package pxlMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawReductionPower;
import pxlMod.character.Heretic;
import pxlMod.util.CardStats;

public class Pocket_Ace extends BaseCard{
    public static final String ID = makeID(Pocket_Ace.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Heretic.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );

    private static final int MAGIC = 5;
    private static final int UPG_MAGIC = -2;

    public Pocket_Ace() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new LoseHPAction(p, p, magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new DrawReductionPower(p,1)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Pocket_Ace();
    }

}
