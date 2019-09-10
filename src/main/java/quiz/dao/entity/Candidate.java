package quiz.dao.entity;

import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "candidate")
public class Candidate extends TemplateEntity {

    private String email;
    private String name;
    private String surname;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
