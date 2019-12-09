package propra.grpproj.quiz.dataholders;

/**
 * 
 * @author Daniel
 *
 */
public class QuestionsAndAnswers
{
	/**
	 * Primary Key
	 */
	private Long questionAnswersId;

	private String question;
	private String answerTrue;
	private String answerFalse1;
	private String answerFalse2;
	private String answerFalse3;
	private String description;

	public QuestionsAndAnswers(Long questionAnswersId, String question, String answerTrue, String answerFalse1,
			String answerFalse2, String answerFalse3, String description)
	{
		super();
		this.questionAnswersId = questionAnswersId;
		this.question = question;
		this.answerTrue = answerTrue;
		this.answerFalse1 = answerFalse1;
		this.answerFalse2 = answerFalse2;
		this.answerFalse3 = answerFalse3;
		this.description = description;
	}

	@Override
	public String toString()
	{
		return "Questions [questionAnswersId=" + questionAnswersId + ", question=" + question + ", answerTrue="
				+ answerTrue + ", answerFalse1=" + answerFalse1 + ", answerFalse2=" + answerFalse2 + ", answerFalse3="
				+ answerFalse3 + ", description=" + description + "]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answerFalse1 == null) ? 0 : answerFalse1.hashCode());
		result = prime * result + ((answerFalse2 == null) ? 0 : answerFalse2.hashCode());
		result = prime * result + ((answerFalse3 == null) ? 0 : answerFalse3.hashCode());
		result = prime * result + ((answerTrue == null) ? 0 : answerTrue.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((question == null) ? 0 : question.hashCode());
		result = prime * result + ((questionAnswersId == null) ? 0 : questionAnswersId.hashCode());
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
		QuestionsAndAnswers other = (QuestionsAndAnswers) obj;
		if (answerFalse1 == null) {
			if (other.answerFalse1 != null)
				return false;
		} else if (!answerFalse1.equals(other.answerFalse1))
			return false;
		if (answerFalse2 == null) {
			if (other.answerFalse2 != null)
				return false;
		} else if (!answerFalse2.equals(other.answerFalse2))
			return false;
		if (answerFalse3 == null) {
			if (other.answerFalse3 != null)
				return false;
		} else if (!answerFalse3.equals(other.answerFalse3))
			return false;
		if (answerTrue == null) {
			if (other.answerTrue != null)
				return false;
		} else if (!answerTrue.equals(other.answerTrue))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		if (questionAnswersId == null) {
			if (other.questionAnswersId != null)
				return false;
		} else if (!questionAnswersId.equals(other.questionAnswersId))
			return false;
		return true;
	}

	public Long getQuestionAnswersId()
	{
		return questionAnswersId;
	}

	public String getQuestion()
	{
		return question;
	}

	public void setQuestion(String question)
	{
		this.question = question;
	}

	public String getAnswerTrue()
	{
		return answerTrue;
	}

	public void setAnswerTrue(String answerTrue)
	{
		this.answerTrue = answerTrue;
	}

	public String getAnswerFalse1()
	{
		return answerFalse1;
	}

	public void setAnswerFalse1(String answerFalse1)
	{
		this.answerFalse1 = answerFalse1;
	}

	public String getAnswerFalse2()
	{
		return answerFalse2;
	}

	public void setAnswerFalse2(String answerFalse2)
	{
		this.answerFalse2 = answerFalse2;
	}

	public String getAnswerFalse3()
	{
		return answerFalse3;
	}

	public void setAnswerFalse3(String answerFalse3)
	{
		this.answerFalse3 = answerFalse3;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

}
