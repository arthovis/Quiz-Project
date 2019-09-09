package quiz.dao.daos;

import quiz.dao.entity.Question;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

public class QuestionDAO {

    public void create(Question question){
        EntityManager entityManager = EMF.createEM();
        entityManager.getTransaction().begin();
        entityManager.persist(question);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Question read(int id){
        EntityManager entityManager = EMF.createEM();
        Question question = entityManager.find(Question.class,id);
        entityManager.close();
        return question;
    }

    public void update(Question question){
        EntityManager entityManager = EMF.createEM();
        entityManager.getTransaction().begin();
        Question existing = entityManager.find(Question.class, question.getId());

        if (existing == null) {
            throw new EntityNotFoundException("Entity not found");
        }
        existing.setText(question.getText());
        existing.setQuestionType(question.getQuestionType());
        existing.setQuestionDifficulty(question.getQuestionDifficulty());

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void delete(Question question) {
        EntityManager entityManager = EMF.createEM();
        entityManager.getTransaction().begin();
        Question existing = entityManager.find(Question.class, question.getId());
        if (existing != null) {
            entityManager.remove(existing);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
