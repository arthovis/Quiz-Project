package quiz.dao.entity;

import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "answer")
public class Answer extends TemplateEntity {

    private String text;
    private int value;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
