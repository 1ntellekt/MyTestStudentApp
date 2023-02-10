package cz.intellekt.myteststudent.data;

import cz.intellekt.myteststudent.domain.Question;
import cz.intellekt.myteststudent.util.CsvManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.*;

@PropertySource("classpath:application.properties")
@Component
public class TestDaoImpl implements TestDaoI {
    @Value("${test.filename}")
    private String filename;

    @Override
    public List<Question> getQuestions() {
        String path = Objects.requireNonNull(getClass().getClassLoader().getResource(filename)).getPath();
        List<List<String>> records = CsvManager.getStringsFromCsvFile(path);
        List<Question> questions = new ArrayList<>();
        for (List<String> row : records) {
            Question q = new Question();
            List<String> answers = new ArrayList<>();
            for (String j : row) {
                if (j.contains("?")) q.setQuestion(j);
                else {
                    if (j.contains("*")){
                        String correctAnswer = j.replace("*", "");
                        q.setCorrectAnswer(correctAnswer);
                        answers.add(correctAnswer);
                    }
                    else answers.add(j);
                }
            }
            q.setAnswers(answers);
            questions.add(q);
        }
        return questions;
    }
}
