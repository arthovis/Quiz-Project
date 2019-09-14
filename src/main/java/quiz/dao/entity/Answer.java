package quiz.dao.entity;

import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "answer")
public class Answer extends TemplateEntity {

    private String text;

    private boolean value;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }


}
