package teste;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import quiz.dao.daos.AnswerDAO;
import quiz.dao.daos.EMF;
import quiz.dao.entity.Answer;

import javax.persistence.EntityManager;

public class TestAnswerDAO {
    @Before
    public void delete() {
        EntityManager entityManager = EMF.createEM();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("delete from answer").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    public void testCRUD() {
        Answer answer = new Answer();
        AnswerDAO answerDAO = new AnswerDAO();

        answer.setText("Random answer");
        answer.setValue(15);

        answerDAO.create(answer);

        Answer read = answerDAO.read(answer.getId());
        Assert.assertNotNull(read);

        Answer updatedAnswer = answerDAO.read(answer.getId());
        updatedAnswer.setText("Updated random answer");
        answerDAO.update(updatedAnswer);
        Answer answerToCheck = answerDAO.read(answer.getId());
        Assert.assertEquals("Updated random answer", answerToCheck.getText());

        Answer answerToRemove = answerDAO.read(answer.getId());
        answerDAO.delete(answerToRemove);

        Answer answerToCheckRemove = answerDAO.read(answer.getId());
        Assert.assertNull(answerToCheckRemove);
    }
}
