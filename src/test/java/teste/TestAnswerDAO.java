package teste;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import quiz.dao.daos.AnswerDAO;
import quiz.dao.daos.CategoryDAO;
import quiz.dao.daos.EMF;
import quiz.dao.daos.QuestionDAO;
import quiz.dao.entity.Answer;
import quiz.dao.entity.Category;
import quiz.dao.entity.Question;
import quiz.dao.enums.QuestionDifficulty;
import quiz.dao.enums.QuestionType;

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

        Category category = new Category();
        CategoryDAO categoryDAO = new CategoryDAO();
        category.setName("aaa");
        categoryDAO.create(category);

        Question question = new Question();
        QuestionDAO questionDAO = new QuestionDAO();
        question.setText("fhsjfhjksd");
        question.setQuestionType(QuestionType.OPEN);
        question.setQuestionDifficulty(QuestionDifficulty.HIGH);
        question.setCategory(category);

        questionDAO.create(question);

        answer.setText("Random answer");
        answer.setValue(true);
        answer.setQuestion(question);

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
