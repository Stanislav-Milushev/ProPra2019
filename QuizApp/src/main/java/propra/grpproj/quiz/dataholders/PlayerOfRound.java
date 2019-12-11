package propra.grpproj.quiz.dataholders;

/**
 * This class holds the reference-association between a actual user and the
 * {@link RoundsOfEvening} where the player play a game und hat uebel gesoffen jung!
 */
public class PlayerOfRound
{
    private final Long id;

    private final Long roundOfEveningRefId;
    private final Long userRefId;
    private final int score;

    public PlayerOfRound(Long id, Long roundOfEveningRefId, Long userRefId, int score)
    {
        super();
        this.id = id;
        this.roundOfEveningRefId = roundOfEveningRefId;
        this.userRefId = userRefId;
        this.score = score;
    }

    @Override
    public String toString()
    {
        return "PlayerOfRound [id=" + id + ", roundOfEveningRefId=" + roundOfEveningRefId + ", userRefId=" + userRefId
                + ", score=" + score + "]";
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PlayerOfRound other = (PlayerOfRound) obj;
        if (id == null)
        {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public Long getId()
    {
        return id;
    }

    public Long getRoundOfEveningRefId()
    {
        return roundOfEveningRefId;
    }

    public Long getUserRefId()
    {
        return userRefId;
    }

    public int getScore()
    {
        return score;
    }

}
