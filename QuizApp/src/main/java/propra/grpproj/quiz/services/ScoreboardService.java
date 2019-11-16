package propra.grpproj.quiz.services;

import java.util.List;

import propra.grpproj.quiz.dataholders.ScoreboardEntity;
import propra.grpproj.quiz.dataholders.User;
import propra.grpproj.quiz.repositories.CrudRepository;

/**
 * Class containing the low level game logic
 */
public class ScoreboardService
{

    private static final int INITIAL_SCORE = 0;
    /* implement all needed services here */

    private CrudRepository<ScoreboardEntity, Long> scoreboardRepository;

    public ScoreboardService(CrudRepository<ScoreboardEntity, Long> scoreboardRepository)
    {
        super();
        this.scoreboardRepository = scoreboardRepository;
    }

    public List<ScoreboardEntity> getScoreBoard()
    {
        return CrudRepository.convertToList(scoreboardRepository.findAll());
    }

    public ScoreboardEntity addUserToActualGame(User user)
    {
        ScoreboardEntity scoreboardEntity = new ScoreboardEntity(user.getId(), user.getUsername(), INITIAL_SCORE);
        return scoreboardRepository.save(scoreboardEntity);
    }

}
