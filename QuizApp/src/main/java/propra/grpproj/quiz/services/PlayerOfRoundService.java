package propra.grpproj.quiz.services;

import java.util.Optional;

import propra.grpproj.quiz.dataholders.PlayerOfRound;
import propra.grpproj.quiz.repositories.sqlite.PlayerOfRoundRepository;


/**
 * 
 * @author Daniel
 *
 */
public class PlayerOfRoundService
{
	private PlayerOfRoundRepository playerOfRoundRepository;

	public PlayerOfRoundService(PlayerOfRoundRepository playerOfRoundRepository)
	{
		super();
		this.playerOfRoundRepository = playerOfRoundRepository;
	}

	public void resetScore()
	{
		playerOfRoundRepository.deleteAll();
	}

	public void writeToDBScore(Long userRefId, Long roundOfEveningRefId, int newScore)
	{
		Optional<PlayerOfRound> playerOfRound = playerOfRoundRepository.findByUserRefIdAndRoundsOfEveId(userRefId,
				roundOfEveningRefId);
		if (playerOfRound.isPresent()) {
			playerOfRoundRepository.save(new PlayerOfRound(nextFreeId(), userRefId, roundOfEveningRefId, newScore));
		}
	}
	
    // ========================================================================
    // helper methods
    // ========================================================================


	private Long nextFreeId()
	{
        Long probe = 1L;

        while (playerOfRoundRepository.existsById(probe))
        {
            probe = probe + 1;
        }

        return probe;
	}

}
