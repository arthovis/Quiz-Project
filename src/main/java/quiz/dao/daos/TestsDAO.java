package quiz.dao.daos;

import quiz.dao.entity.Question;
import quiz.dao.entity.Tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.List;

public class TestsDAO {

    public void create(Tests test) {
        EntityManager entityManager = EMF.createEM();
        entityManager.getTransaction().begin();
        entityManager.persist(test);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Tests read(int id) {
        EntityManager entityManager = EMF.createEM();
        Tests test = entityManager.find(Tests.class, id);
        entityManager.close();
        return test;
    }

    public void update(Tests test) {
        EntityManager entityManager = EMF.createEM();
        entityManager.getTransaction().begin();
        Tests existing = entityManager.find(Tests.class, test.getId());

        if (existing == null) {
            throw new EntityNotFoundException("Entity not found");
        }
        existing.setCreationDate(test.getCreationDate());

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void delete(Tests test) {
        EntityManager entityManager = EMF.createEM();
        entityManager.getTransaction().begin();
        Tests existing = entityManager.find(Tests.class, test.getId());
        if (existing != null) {
            entityManager.remove(existing);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void link(List<Question> randomChosenQuestions, Tests tests) {
        // todo
    }
}
