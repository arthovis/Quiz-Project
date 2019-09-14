package quiz.dao.services;

import quiz.dao.daos.AnswerDAO;
import quiz.dao.daos.CategoryDAO;
import quiz.dao.daos.QuestionDAO;
import quiz.dao.daos.TestsDAO;
import quiz.dao.entity.Question;
import quiz.dao.entity.Tests;

import java.util.List;

public class Services {
    CategoryDAO categoryDAO = new CategoryDAO();
    QuestionDAO questionDAO = new QuestionDAO();
    AnswerDAO answerDAO = new AnswerDAO();
    TestsDAO testsDAO = new TestsDAO();

    //populate from details {} or in script
    //select random from each dao
    public Tests createTest() {

        Tests tests = new Tests();

        {
            // add details to test
        }

        testsDAO.create(tests);

        List<Question> questions = questionDAO.findAll();

        List<Question> randomChosenQuestions = chooseRandom(questions);

        testsDAO.link(randomChosenQuestions, tests);
        return tests;
    }

    private List<Question> chooseRandom(List<Question> questions) {
        return null;// TODO
    }
}
