package pxlMod.cards;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import pxlMod.character.Heretic;
import pxlMod.powers.AttritionPower;
import pxlMod.powers.Scourge_FormPower;
import pxlMod.util.CardStats;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.getCard;

public class Scourge_Form extends BaseCard {
    public static final String ID = makeID(Scourge_Form.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Heretic.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            3
    );

    public Scourge_Form() {
        super(ID, info);

        setMagic(1);

        this.magicNumber = 1;

        this.isEthereal = true;

        tags.add(BaseModCardTags.FORM);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new Scourge_FormPower(p, this.magicNumber), this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.isEthereal = false;
            this.upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }

    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Scourge_Form();
    }

    public static AbstractCard getRandomCardAnyRarity() {
        List<AbstractCard.CardRarity> rarities = Arrays.asList(
                AbstractCard.CardRarity.COMMON,
                AbstractCard.CardRarity.UNCOMMON,
                AbstractCard.CardRarity.RARE
        );

        Random rng = new Random();
        AbstractCard.CardRarity selectedRarity = rarities.get(rng.nextInt(rarities.size()));

        return getCard(selectedRarity);
    }
}