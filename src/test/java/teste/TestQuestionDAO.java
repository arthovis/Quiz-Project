package teste;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import quiz.dao.daos.CategoryDAO;
import quiz.dao.daos.EMF;
import quiz.dao.daos.QuestionDAO;
import quiz.dao.entity.Category;
import quiz.dao.entity.Question;
import quiz.dao.enums.QuestionDifficulty;
import quiz.dao.enums.QuestionType;

import javax.persistence.EntityManager;

public class TestQuestionDAO {
    @Before
    public void delete() {
        EntityManager entityManager = EMF.createEM();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("delete from question").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    public void testCreate() {
        CategoryDAO categoryDAO = new CategoryDAO();
        QuestionDAO questionDAO = new QuestionDAO();

        Category category = new Category();
        category.setName("aaa");
        categoryDAO.create(category);

        Question question = new Question();

        question.setText("fhsjfhjksd");
        question.setQuestionType(QuestionType.OPEN);
        question.setQuestionDifficulty(QuestionDifficulty.HIGH);
        question.setCategory(category);

        questionDAO.create(question);

        Question read = questionDAO.read(question.getId());
        Assert.assertNotNull(read);

        //update
        Question updatedQuestion = questionDAO.read(question.getId());
        updatedQuestion.setText("sfsdfdsfsd");
        questionDAO.update(updatedQuestion);
        Question questionToCheck = questionDAO.read(question.getId());
        Assert.assertEquals("sfsdfdsfsd", questionToCheck.getText());

        //remove
        Question questionToRemove = questionDAO.read(question.getId());
        questionDAO.delete(questionToRemove);

        Question questionToCheck1 = questionDAO.read(question.getId());
        Assert.assertNull(questionToCheck1);
    }
}
