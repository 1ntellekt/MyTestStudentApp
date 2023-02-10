package cz.intellekt.myteststudent.service;

import cz.intellekt.myteststudent.data.TestDaoI;
import cz.intellekt.myteststudent.domain.Question;
import cz.intellekt.myteststudent.domain.Result;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class TestServiceImpl implements TestService {

    private final TestDaoI testDao;

    public TestServiceImpl(TestDaoI testDao) {
        this.testDao = testDao;
    }

    @Override
    public void startTest(String nameStudent) {
        List<Question> questions = testDao.getQuestions();
        Scanner scanner = new Scanner(System.in);
        Result result = new Result(questions.size());
        for (Question q :questions){
            System.out.println(q.getQuestion());
            for (int i = 0; i < q.getAnswers().size(); i++) {
                System.out.print(q.getAnswers().get(i)+", ");
            }
            System.out.println();
            System.out.print("Enter your answer: ");
            String answer = scanner.nextLine();
            if (answer.equals(q.getCorrectAnswer())){
                result.addScore();
            } else {
                result.addWrong();
            }
        }
        System.out.println("Student:"+nameStudent+" results: "+result.getPercent()+"% wrongs:"+result.getWrongs()+" score:"+result.getScore());
    }

}
