package quiz.dao.daos;

import quiz.dao.entity.Topic;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

public class TopicDAO {

    public void create(Topic topic) {
        EntityManager entityManager = EMF.createEM();
        entityManager.getTransaction().begin();
        entityManager.persist(topic);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Topic read(int id) {
        EntityManager entityManager = EMF.createEM();
        Topic topic = entityManager.find(Topic.class, id);
        entityManager.close();
        return topic;
    }

    public void update(Topic topic) {
        EntityManager entityManager = EMF.createEM();
        entityManager.getTransaction().begin();
        Topic existing = entityManager.find(Topic.class, topic.getId());

        if (existing == null) {
            throw new EntityNotFoundException("Entity not found");
        }
        existing.setNumberOfQuestions(topic.getNumberOfQuestions());
        existing.setTopicDifficulty(topic.getTopicDifficulty());

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void delete(Topic topic) {
        EntityManager entityManager = EMF.createEM();
        entityManager.getTransaction().begin();
        Topic existing = entityManager.find(Topic.class, topic.getId());
        if (existing != null) {
            entityManager.remove(existing);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
