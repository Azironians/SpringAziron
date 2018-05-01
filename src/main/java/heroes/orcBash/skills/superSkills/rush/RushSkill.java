package heroes.orcBash.skills.superSkills.rush;

import heroes.abstractHero.hero.Hero;
import heroes.abstractHero.skills.abstractSkill.AbstractSkill;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import management.actionManagement.actions.ActionEventFactory;
import management.battleManagement.BattleManager;
import management.playerManagement.Player;
import management.playerManagement.PlayerManager;
import java.util.List;
import java.util.logging.Logger;

import static heroes.orcBash.skills.superSkills.rush.RushPropertySkill.*;

public final class RushSkill extends AbstractSkill {

    private static final Logger log = Logger.getLogger(RushSkill.class.getName());

    public RushSkill(final ImageView sprite, final ImageView description, final List<Media> voiceList) {
        super(NAME, RELOAD, REQUIRED_LEVEL, getSkillCoefficients()
                , sprite, description, voiceList);
    }

    @Override
    public final void use(final BattleManager battleManager, final PlayerManager playerManager) {
        final double damage = getParent().getHealthSupply() * coefficients.get(0);
        final Player currentPlayer = playerManager.getCurrentTeam().getCurrentPlayer();
        final Player opponentPlayer = playerManager.getOpponentTeam().getCurrentPlayer();
        final Hero opponentHero = opponentPlayer.getCurrentHero();
        if (opponentHero.getDamage(damage)) {
            actionEvents.add(ActionEventFactory.getAfterDealDamage(currentPlayer, opponentHero, damage));
        }
    }

    @Override
    public final void showAnimation() {

    }
}
