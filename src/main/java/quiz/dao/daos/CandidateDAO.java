package quiz.dao.daos;

import quiz.dao.entity.Candidate;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

public class CandidateDAO {

    public void create(Candidate candidate) {
        EntityManager entityManager = EMF.createEM();
        entityManager.getTransaction().begin();
        entityManager.persist(candidate);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Candidate read(int id) {
        EntityManager entityManager = EMF.createEM();
        Candidate candidate = entityManager.find(Candidate.class, id);
        entityManager.close();
        return candidate;
    }

    public void update(Candidate candidate) {
        EntityManager entityManager = EMF.createEM();
        entityManager.getTransaction().begin();
        Candidate existing = entityManager.find(Candidate.class, candidate.getId());

        if (existing == null) {
            throw new EntityNotFoundException("Entity not found");
        }
        existing.setEmail(candidate.getEmail());
        existing.setName(candidate.getName());
        existing.setSurname(candidate.getSurname());
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void delete(Candidate candidate) {
        EntityManager entityManager = EMF.createEM();
        entityManager.getTransaction().begin();
        Candidate existing = entityManager.find(Candidate.class, candidate.getId());
        if (existing != null) {
            entityManager.remove(existing);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
