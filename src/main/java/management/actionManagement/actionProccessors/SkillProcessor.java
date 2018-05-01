package management.actionManagement.actionProccessors;

import heroes.abstractHero.skills.Skill;
import management.actionManagement.ActionManager;
import management.battleManagement.BattleManager;
import management.playerManagement.ATeam;
import management.playerManagement.PlayerManager;
import management.processors.Processor;

//Not final!
public class SkillProcessor implements Processor {

    private final ActionManager actionManager;

    private final BattleManager battleManager;

    private final PlayerManager playerManager;

    protected ATeam currentTeam;

    protected Skill skill;

    public SkillProcessor(final ActionManager actionManager, final BattleManager battleManager
            , final  PlayerManager playerManager){
        this.actionManager = actionManager;
        this.battleManager = battleManager;
        this.playerManager = playerManager;
    }

    @Override
    public void process() {
        skill.getActionEvents().clear();
        skill.use(battleManager, playerManager); //FIXME: wrap all skills in processor
        skill.reset();
        skill.getActionEvents().forEach(actionManager.getEventEngine()::handle);
        actionManager.refreshScreen();
        if (battleManager.isEndTurn()) {
            actionManager.endTurn(currentTeam);
        }
    }

    public void setTeamAndSkill(final ATeam currentTeam, final Skill skill){
        this.currentTeam = currentTeam;
        this.skill = skill;
    }
}
