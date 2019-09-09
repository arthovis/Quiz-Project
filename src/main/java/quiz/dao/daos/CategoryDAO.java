package quiz.dao.daos;

import quiz.dao.entity.Category;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

public class CategoryDAO {

    public void create(Category category) {
        EntityManager entityManager = EMF.createEM();
        entityManager.getTransaction().begin();
        entityManager.persist(category);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Category read(int id) {
        EntityManager entityManager = EMF.createEM();
        Category category = entityManager.find(Category.class, id);
        entityManager.close();
        return category;
    }

    public void update(Category category) {
        EntityManager entityManager = EMF.createEM();
        entityManager.getTransaction().begin();
        Category existing = entityManager.find(Category.class, category.getId());

        if (existing == null) {
            throw new EntityNotFoundException("Entity not found");
        }
        existing.setName(category.getName());

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void delete(Category category) {
        EntityManager entityManager = EMF.createEM();
        entityManager.getTransaction().begin();
        Category existing = entityManager.find(Category.class, category.getId());
        if (existing != null) {
            entityManager.remove(existing);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }


}
