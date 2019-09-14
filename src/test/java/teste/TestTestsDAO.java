package teste;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import quiz.dao.daos.EMF;
import quiz.dao.daos.TestsDAO;
import quiz.dao.entity.Tests;

import javax.persistence.EntityManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestTestsDAO {
    @Before
    public void delete() {
        EntityManager entityManager = EMF.createEM();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("delete from test").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    public void testCRUD() throws ParseException {
        Tests test = new Tests();
        TestsDAO testsDAO = new TestsDAO();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date date = format.parse("2009-12-31");


        test.setCreationDate(date);

        testsDAO.create(test);

        Tests read = testsDAO.read(test.getId());
        Assert.assertNotNull(read);

        Tests updatedTest = testsDAO.read(test.getId());
        Date updatedDate = format.parse("2012-12-01");

        updatedTest.setCreationDate(updatedDate);
        testsDAO.update(updatedTest);
        Tests testToCheck = testsDAO.read(test.getId());
        Date updatedDate1 = format.parse("2012-12-01");
        Assert.assertEquals(updatedDate1, testToCheck.getCreationDate());

        Tests testToRemove = testsDAO.read(test.getId());
        testsDAO.delete(testToRemove);

        Tests testToCheckRemove = testsDAO.read(test.getId());
        Assert.assertNull(testToCheckRemove);
    }
}
