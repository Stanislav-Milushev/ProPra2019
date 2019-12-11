package propra.grpproj.quiz.services;

import java.util.List;
import java.util.Optional;

import propra.grpproj.quiz.dataholders.Pub;
import propra.grpproj.quiz.dataholders.User;
import propra.grpproj.quiz.repositories.CrudRepository;
import propra.grpproj.quiz.repositories.sqlite.PubRepository;
import propra.grpproj.quiz.repositories.sqlite.UserRepository;

/**
 * <p>
 * A service (logic) class serving the high-level API methods to be used by the
 * server-socket manager on incoming requests from users (client).
 * 
 * <p>
 * This class serves methods for saving new data in the database as well as
 * reading data from the database.
 * 
 * <p>
 * NOTICE: the Service layer is the only layer that must use the XxxRepository
 * classes. If a feature is missing here, please added a new function, but do
 * never use the Repositories directly!
 * 
 * @author Daniel
 * 
 */
public class PubService
{

    private PubRepository pubRepository;
    private UserRepository userRepository;

    public PubService(PubRepository pubRepository, UserRepository userRepository)
    {
        super();

        this.pubRepository = pubRepository;
    }

    /**
     * <p>
     * Creates a new Pub, that is not yet officially registered.
     * 
     * <p>
     * NOTICE: this method is not yet fully implemented: it would override an
     * already bay same name existing Pub.
     * 
     * @param pubName
     * @param fullyQualifiedAddress
     * @param barkeeperUserRefId
     */
    public void createNewPub(String pubName, String fullyQualifiedAddress, long barkeeperUserRefId)
    {
        // Open Task: validate that the given user id is a barkeeper and not admin or
        // player...
        // Open Task: check if that pub already exists by the unique
        // fullyQualifiedAddress

        // FIXME add fullyQualifiedAddress to constructor of Pub
        pubRepository.save(new Pub(nextFreeId(), pubName, barkeeperUserRefId, false));
        
    }

    /**
     * This method officially registers a pub!
     */
    public void acceptPub(String pubName, String ownerName)
    {
        final boolean acceptPub = true;

        Optional<Pub> optional = findByPubNameAndBarkeeperName(pubName, ownerName);
        if (optional.isEmpty())
            throw new RuntimeException("No Pub found by name " + pubName + " to be accepted.");

        Pub oldPubFromDB = optional.get();
        Pub newPubToBeSaved = new Pub(oldPubFromDB.getId(), oldPubFromDB.getName(),
                oldPubFromDB.getBarkeeperUserRefId(), acceptPub);
        pubRepository.save(newPubToBeSaved);
    }

    // ========================================================================
    // helper methods
    // ========================================================================

    private Optional<Pub> findByPubNameAndBarkeeperName(String pubName, String barkeeperName)
    {
        List<Pub> allPubs = CrudRepository.convertToList(pubRepository.findAll());

        // @formatter:off
        return allPubs
                .stream()
                .filter(pub -> { return matchesPubNameAndBarkeeperName(pub, pubName, barkeeperName); })
                .findFirst();
        // @formatter:on
    }

    private boolean matchesPubNameAndBarkeeperName(Pub pub, String pubName, String barkeeperName)
    {
        if (pub.getName().equalsIgnoreCase(pubName))
        {
            Optional<User> barkeeper = userRepository.findById(pub.getBarkeeperUserRefId());
            if (barkeeper.isPresent())
            { return barkeeper.get().getUsername().equalsIgnoreCase(barkeeperName); }
        }

        return false;
    }

    /**
     * Returns the next incremental free ID.
     * 
     * @return the next incremental free ID.
     */
    private Long nextFreeId()
    {
        Long probe = 1L;

        while (pubRepository.existsById(probe))
        {
            probe = probe + 1;
        }

        return probe;
    }

}
