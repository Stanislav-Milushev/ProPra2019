package propra.grpproj.quiz.dataholders;

/**
 * A class holding the reference-association between one question and one
 * round of an evening that was asked there.
 */
public class QuestionOfRound
{
    private final Long id;

    /**
     * The reference id to the roundOfEvening where the question specified by refId
     * was asked
     */
    private final Long roundOfEveningRefId;
    private final Long questionRefId;

    public QuestionOfRound(Long id, Long roundOfEveningRefId, Long questionRefId)
    {
        super();
        this.id = id;
        this.roundOfEveningRefId = roundOfEveningRefId;
        this.questionRefId = questionRefId;
    }

    @Override
    public String toString()
    {
        return "QuestionOfRound [id=" + id + ", roundOfEveningRefId=" + roundOfEveningRefId + ", questionRefId="
                + questionRefId + "]";
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
        QuestionOfRound other = (QuestionOfRound) obj;
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

    public Long getQuestionRefId()
    {
        return questionRefId;
    }

}
