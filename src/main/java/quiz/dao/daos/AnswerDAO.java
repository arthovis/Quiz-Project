package quiz.dao.daos;

import quiz.dao.entity.Answer;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

public class AnswerDAO {

    public void create(Answer answer) {
        EntityManager entityManager = EMF.createEM();
        entityManager.getTransaction().begin();
        entityManager.persist(answer);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Answer read(int id) {
        EntityManager entityManager = EMF.createEM();
        Answer answer = entityManager.find(Answer.class, id);
        entityManager.close();
        return answer;
    }

    public void update(Answer answer) {
        EntityManager entityManager = EMF.createEM();
        entityManager.getTransaction().begin();
        Answer existing = entityManager.find(Answer.class, answer.getId());

        if (existing == null) {
            throw new EntityNotFoundException("Entity not found");
        }
        existing.setText(answer.getText());
        existing.setValue(answer.isValue());

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void delete(Answer answer) {
        EntityManager entityManager = EMF.createEM();
        entityManager.getTransaction().begin();
        Answer existing = entityManager.find(Answer.class, answer.getId());
        if (existing != null) {
            entityManager.remove(existing);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
