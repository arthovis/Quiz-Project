package teste;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import quiz.dao.daos.EMF;
import quiz.dao.daos.TopicDAO;
import quiz.dao.entity.Topic;
import quiz.dao.enums.TopicDifficulty;

import javax.persistence.EntityManager;

public class TestTopicDAO {
    @Before
    public void delete() {
        EntityManager entityManager = EMF.createEM();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("delete from topic").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    public void testCRUD() {
        Topic topic = new Topic();
        TopicDAO topicDAO = new TopicDAO();

        topic.setNumberOfQuestions(10);
        topic.setTopicDifficulty(TopicDifficulty.HIGH);

        //create
        topicDAO.create(topic);
        Topic read = topicDAO.read(topic.getId());
        Assert.assertNotNull(read);

        //update
        Topic updatedTopic = topicDAO.read(topic.getId());
        updatedTopic.setTopicDifficulty(TopicDifficulty.MEDIUM);
        topicDAO.update(updatedTopic);
        Topic topicToCheck = topicDAO.read(topic.getId());
        Assert.assertEquals(TopicDifficulty.MEDIUM, topicToCheck.getTopicDifficulty());

        //remove
        Topic topicToRemove = topicDAO.read(topic.getId());
        topicDAO.delete(topicToRemove);

        Topic topicToCheckRemove = topicDAO.read(topic.getId());
        Assert.assertNull(topicToCheckRemove);
    }
}
