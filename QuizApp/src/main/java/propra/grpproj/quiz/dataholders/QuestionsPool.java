package propra.grpproj.quiz.dataholders;

public class QuestionsPool
{
	/**
	 * Primary Key
	 */
	private Long poolId;

	/**
	 * Foreign key for linking the tables
	 */
	private Long questionAnswersId;

	public QuestionsPool(Long poolId, Long questionAnswersId)
	{
		super();
		this.poolId = poolId;
		this.questionAnswersId = questionAnswersId;
	}

	@Override
	public String toString()
	{
		return "QuestionsPool [poolId=" + poolId + ", questionAnswersId=" + questionAnswersId + "]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((poolId == null) ? 0 : poolId.hashCode());
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
		QuestionsPool other = (QuestionsPool) obj;
		if (poolId == null) {
			if (other.poolId != null)
				return false;
		} else if (!poolId.equals(other.poolId))
			return false;
		return true;
	}

	public Long getPoolId()
	{
		return poolId;
	}

	public Long getQuestionAnswersId()
	{
		return questionAnswersId;
	}

}
