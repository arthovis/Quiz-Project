package quiz.dao.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@javax.persistence.Entity
@Table(name = "test")
public class Tests extends TemplateEntity {

    @ManyToMany(mappedBy = "tests")
    private final List<Question> questions = new ArrayList<>();
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    public List<Question> getQuestions() {
        return questions;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
