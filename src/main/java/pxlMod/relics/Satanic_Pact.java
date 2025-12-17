package pxlMod.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.RingOfTheSerpent;
import pxlMod.cards.BaseCard;
import pxlMod.character.Heretic;

import static pxlMod.BasicMod.makeID;

public class Satanic_Pact extends BaseRelic {
    private static final String NAME = "Satanic_Pact"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.STARTER; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.MAGICAL; //The sound played when the relic is clicked.
    private static final int DRAW = 2;

    public Satanic_Pact() {
        super(ID, NAME, Heretic.Meta.CARD_COLOR, RARITY, SOUND);
    }

    public void onEquip() {
        ++AbstractDungeon.player.masterHandSize;
        ++AbstractDungeon.player.masterHandSize;
    }

    public void onUnequip() {
        --AbstractDungeon.player.masterHandSize;
        --AbstractDungeon.player.masterHandSize;
    }

    public void atTurnStart() {
        this.flash();
    }

    public AbstractRelic makeCopy() {
        return new Satanic_Pact();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + DRAW + DESCRIPTIONS[1];
    }
}
