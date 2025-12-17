package pxlMod.util;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import pxlMod.cards.Scourge_Form;

public class SelectCardToTransformAction extends AbstractGameAction {
    private boolean openedScreen = false;

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;

        if (!openedScreen) {
            if (p.hand.isEmpty()) {
                isDone = true;
                return;
            }

            AbstractDungeon.handCardSelectScreen.open("Transform", 1, false, false);
            openedScreen = true;
            return;
        }

        if (!AbstractDungeon.handCardSelectScreen.selectedCards.group.isEmpty()) {
            AbstractCard selected = AbstractDungeon.handCardSelectScreen.selectedCards.group.get(0);

            AbstractCard toRemove = null;
            for (AbstractCard c : p.masterDeck.group) {
                if (c.uuid.equals(selected.uuid)) {
                    toRemove = c;
                    break;
                }
            }

            if (toRemove != null) {
                p.masterDeck.removeCard(toRemove);

                AbstractCard newCard = Scourge_Form.getRandomCardAnyRarity();
                if (selected.upgraded) {
                    newCard.upgrade();
                }
                p.masterDeck.addToTop(newCard);

                AbstractCard copyForHand = newCard.makeStatEquivalentCopy();
                if (selected.upgraded) {
                    copyForHand.upgrade();
                }

                if (p.hand.size() < BaseMod.MAX_HAND_SIZE) {
                    p.hand.addToHand(copyForHand);
                    copyForHand.lighten(false);
                    copyForHand.applyPowers();
                } else {
                    p.discardPile.addToTop(copyForHand);
                }
            }

            AbstractDungeon.handCardSelectScreen.selectedCards.clear();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            isDone = true;
        }

        tickDuration();
    }
}
