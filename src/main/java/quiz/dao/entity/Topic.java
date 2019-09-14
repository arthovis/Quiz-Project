package quiz.dao.entity;

import quiz.dao.enums.TopicDifficulty;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "topic")
public class Topic extends TemplateEntity {

    @Column(name = "number_of_questions")
    private int numberOfQuestions;

    @Column(name = "topic_difficulty")
    @Enumerated(EnumType.STRING)
    private TopicDifficulty topicDifficulty;

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public TopicDifficulty getTopicDifficulty() {
        return topicDifficulty;
    }

    public void setTopicDifficulty(TopicDifficulty topicDifficulty) {
        this.topicDifficulty = topicDifficulty;
    }
}
