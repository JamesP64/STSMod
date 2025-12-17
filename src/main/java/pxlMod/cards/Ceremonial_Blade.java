package pxlMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase;
import pxlMod.character.Heretic;
import pxlMod.util.CardStats;

public class Ceremonial_Blade extends BaseCard{
    public static final String ID = makeID(Ceremonial_Blade.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Heretic.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );

    private static final int DAMAGE = 1;

    public Ceremonial_Blade() {
        super(ID, info);
        this.baseDamage = DAMAGE;
        this.damage = this.baseDamage;
        setExhaust(true);
        setCustomVar("BaseDamage", damage, damage);
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (AbstractDungeon.player != null) {
            setDamage(AbstractDungeon.player.hand.size() * damage);
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Ceremonial_Blade();
    }

    @Override
    public void applyPowers() {
        if (AbstractDungeon.player != null && AbstractDungeon.getCurrRoom().phase == RoomPhase.COMBAT) {
            int handSize = AbstractDungeon.player.hand.size();

            super.applyPowers();
            setCustomVar("BaseDamage", damage, damage);


            this.rawDescription = cardStrings.DESCRIPTION + " NL (Deals " + (this.damage * handSize) + " damage)";
            this.initializeDescription();

            this.isDamageModified = this.damage != this.baseDamage;
        }
    }

    @Override
    public void onMoveToDiscard() {
        this.baseDamage = DAMAGE;
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    @Override
    public void triggerWhenDrawn() {
        this.baseDamage = DAMAGE;
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }
}