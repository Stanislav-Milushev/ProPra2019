package propra.grpproj.quiz.services;

import java.util.Optional;

import propra.grpproj.quiz.dataholders.Evening;
import propra.grpproj.quiz.dataholders.Pub;
import propra.grpproj.quiz.repositories.sqlite.EveningRepository;
import propra.grpproj.quiz.repositories.sqlite.PubRepository;

public class EveningService
{

    private EveningRepository eveningRepository;
    private PubRepository pubRepository;

    public EveningService(EveningRepository eveningRepository, PubRepository pubRepository)
    {
        super();
        this.eveningRepository = eveningRepository;
        this.pubRepository = pubRepository;
    }

    /**
     * Creates a new setup for an evening in a pub at a given date and time and
     * configures the max-duration to answer questions.
     * 
     * TODO and what about the rounds and questions per round? When will this be
     * added to the evening-event?
     * 
     * @param uniquePubName a unique name entered on GUI, better it would be better
     * to use the pub-id!
     * @param beginTimeAndDate a UTC date and time in ISO format
     * @throws RuntimeException if no Pub was found by given name
     */
    public void createNewEvening(
            String uniquePubName, String beginTimeAndDate, long maxDurationToAnswerQuestionsInSeconds
    )
    {
        Long id = nextFreeId();
        Long pubRefId = findPubByNameOrThrow(uniquePubName).getId();
        
        // FIXME pass maxDurationToAnswerQuestionsInSeconds to constructor of Evening
        eveningRepository.save(new Evening(id, pubRefId, beginTimeAndDate));
    }

    private Pub findPubByNameOrThrow(String uniquePubName)
    {
        Optional<Pub> optionalOfPub = pubRepository.findByName(uniquePubName);
        if (optionalOfPub.isEmpty())
            throw new RuntimeException(
                    "Cannot create a evening in an non existing Pub. Failed to find Pub by name " + uniquePubName);
        return optionalOfPub.get();
    }

    // ========================================================================
    // helper methods
    // ========================================================================

    /**
     * Returns the next incremental free ID.
     * 
     * @return the next incremental free ID.
     */
    private Long nextFreeId()
    {
        Long probe = 1L;

        while (eveningRepository.existsById(probe))
        {
            probe = probe + 1;
        }

        return probe;
    }

}
