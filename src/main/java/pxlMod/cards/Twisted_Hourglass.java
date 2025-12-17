package pxlMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pxlMod.character.Heretic;
import pxlMod.powers.ApostasyPower;
import pxlMod.powers.CurseOfFatePower;
import pxlMod.powers.CurseOfRecoilPower;
import pxlMod.powers.DrawWatcherPower;
import pxlMod.util.CardStats;

import static pxlMod.BasicMod.makeID;

public class Twisted_Hourglass extends BaseCard{
    public static final String ID = makeID(Twisted_Hourglass.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Heretic.Meta.CARD_COLOR,
            AbstractCard.CardType.SKILL,
            AbstractCard.CardRarity.UNCOMMON,
            AbstractCard.CardTarget.ENEMY,
            2
    );

    private static final int MAGIC = 1;

    public Twisted_Hourglass() {
        super(ID, info);

        setMagic(MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new CurseOfFatePower(p, m, magicNumber)));
        if (!p.hasPower(DrawWatcherPower.POWER_ID)) {
            addToBot(new ApplyPowerAction(p, p, new DrawWatcherPower(p)));
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Twisted_Hourglass();
    }
}
