package pxlMod.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pxlMod.character.Heretic;
import pxlMod.util.CardStats;

public class Greed extends BaseCard{
    public static final String ID = makeID(Greed.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Heretic.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );

    private static final int BLOCK = 3;
    private static final int UPG_BLOCK = 2;

    public Greed() {
        super(ID, info);

        setBlock(BLOCK, UPG_BLOCK);

        tags.add(CardTags.STARTER_DEFEND);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, this.block));
        this.addToBot(new DrawCardAction(p, 2));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Greed();
    }
}
