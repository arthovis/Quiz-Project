package teste;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import quiz.dao.daos.CandidateDAO;
import quiz.dao.daos.EMF;
import quiz.dao.entity.Candidate;

import javax.persistence.EntityManager;

public class TestCandidateDAO {
    @Before
    public void delete() {
        EntityManager entityManager = EMF.createEM();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("delete from candidate").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    public void testCRUD() {
        CandidateDAO candidateDAO = new CandidateDAO();
        Candidate candidate = new Candidate();

        candidate.setEmail("andrei@florin.com");
        candidate.setName("Andrei");
        candidate.setSurname("Baicu");

        candidateDAO.create(candidate);

        Candidate read = candidateDAO.read(candidate.getId());
        Assert.assertNotNull(read);

        Candidate updatedCandidate = candidateDAO.read(candidate.getId());
        updatedCandidate.setSurname("Baciu");
        candidateDAO.update(updatedCandidate);
        Candidate candidateToCheck = candidateDAO.read(candidate.getId());
        Assert.assertEquals("Baciu", candidateToCheck.getSurname());

        Candidate candidateToRemove = candidateDAO.read(candidate.getId());
        candidateDAO.delete(candidateToRemove);

        Candidate candidateToCheckRemove = candidateDAO.read(candidate.getId());
        Assert.assertNull(candidateToCheckRemove);

    }
}
