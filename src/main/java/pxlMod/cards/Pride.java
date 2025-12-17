package pxlMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.unique.FeedAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import pxlMod.character.Heretic;
import pxlMod.util.CardStats;

import static pxlMod.BasicMod.modID;

public class Pride extends BaseCard {
    public static final String ID = makeID(Pride.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Heretic.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            3
    );

    private static final int MAGIC = 0;
    private static final int UPG_MAGIC = 2;

    public Pride() {
        super(ID, info);

        setMagic(MAGIC,UPG_MAGIC);

        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.player.increaseMaxHp((AbstractDungeon.player.hand.size()+magicNumber),true);
    }

//    public void applyPowers() {
//        super.applyPowers();
//        setCustomVar("HPGain", AbstractDungeon.player.hand.size() + magicNumber + 1,
//                AbstractDungeon.player.hand.size() + magicNumber + 1);
//        this.rawDescription = cardStrings.DESCRIPTION;
//        //this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[0];
//        this.rawDescription = this.rawDescription + " NL (Gain [#67D1A1]!" + modID + ":HPGain![] HP.)";
//        this.initializeDescription();
//    }

    @Override
    public AbstractCard makeCopy() {
        return new Pride();
    }

}