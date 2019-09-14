package teste;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import quiz.dao.daos.CategoryDAO;
import quiz.dao.daos.EMF;
import quiz.dao.entity.Category;

import javax.persistence.EntityManager;

public class TestCategoryDAO {

    @Before
    public void delete() {
        EntityManager entityManager = EMF.createEM();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("delete from question").executeUpdate();
        entityManager.createNativeQuery("delete from category").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    public void testCRUD() {
        CategoryDAO categoryDAO = new CategoryDAO();

        Category category = new Category();
        category.setName("aaa");

        // test this
        categoryDAO.create(category);

        // verify
        Category read = categoryDAO.read(category.getId());
        Assert.assertNotNull(read);

        Category updatedCategory = categoryDAO.read(category.getId());
        updatedCategory.setName("bbb");
        categoryDAO.update(updatedCategory);
        Category categoryToCheck = categoryDAO.read(category.getId());
        Assert.assertEquals("bbb", categoryToCheck.getName());


        Category categoryToRemove = categoryDAO.read(category.getId());
        categoryDAO.delete(categoryToRemove);

        Category categoryToCheck1 = categoryDAO.read(category.getId());
        Assert.assertNull(categoryToCheck1);

    }
}
