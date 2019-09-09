package teste;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import quiz.dao.daos.CategoryDAO;
import quiz.dao.daos.EMF;
import quiz.dao.entity.Category;

import javax.persistence.EntityManager;

public class TestCategoryDao {

    @Before
    public void delete() {
        EntityManager entityManager = EMF.createEM();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("delete from category").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    public void testCreate() {
        CategoryDAO categoryDAO = new CategoryDAO();

        Category category = new Category();
        category.setName("aaa");

        // test this
        categoryDAO.create(category);

        // verify
        Category read = categoryDAO.read(1);
        Assert.assertNotNull(read);

        Category updatedCategory = categoryDAO.read(1);
        updatedCategory.setName("bbb");
        categoryDAO.update(updatedCategory);
        Category categoryToCheck = categoryDAO.read(1);
        Assert.assertEquals("bbb", categoryToCheck.getName());


        Category categoryToRemove = categoryDAO.read(1);
        categoryDAO.delete(categoryToRemove);

        Category categoryToCheck1 = categoryDAO.read(1);
        Assert.assertNull(categoryToCheck1);

    }
}
