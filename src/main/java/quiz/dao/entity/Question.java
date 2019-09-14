package quiz.dao.entity;

import quiz.dao.enums.QuestionDifficulty;
import quiz.dao.enums.QuestionType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
@Table(name = "question")
public class Question extends TemplateEntity {

    private String text;

    @Column(name = "question_type")
    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    @Column(name = "question_difficulty")
    @Enumerated(EnumType.STRING)
    private QuestionDifficulty questionDifficulty;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category", referencedColumnName = "id")
    Category category;

    @OneToMany(mappedBy = "question")
    private final List<Answer> answers = new ArrayList<>();


    @ManyToMany
    @JoinTable(name = "question_test",
            joinColumns = {@JoinColumn(name = "question_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "test_id", referencedColumnName = "id")})
    private final List<Tests> tests = new ArrayList<>();

    public List<Tests> getTests() {
        return tests;
    }

    public List<Answer> getAnswers() {
        return answers;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public QuestionDifficulty getQuestionDifficulty() {
        return questionDifficulty;
    }

    public void setQuestionDifficulty(QuestionDifficulty questionDifficulty) {
        this.questionDifficulty = questionDifficulty;
    }
}
