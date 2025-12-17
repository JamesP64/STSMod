package pxlMod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pxlMod.character.Heretic;
import pxlMod.util.CardStats;

public class Sloth extends BaseCard {
    public static final String ID = makeID(Sloth.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Heretic.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.ENEMY,
            3
    );

    public Sloth() {
        super(ID, info);

        setMagic(1);
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(2);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        m.setMove((byte) -1, com.megacrit.cardcrawl.monsters.AbstractMonster.Intent.STUN);
        m.createIntent();
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Sloth();
    }
}