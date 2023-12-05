package com.akayush1108.quizApp.service;

import com.akayush1108.quizApp.dao.QuestionDao;
import com.akayush1108.quizApp.dao.QuizDao;
import com.akayush1108.quizApp.model.Question;
import com.akayush1108.quizApp.model.QuestionWrapper;
import com.akayush1108.quizApp.model.Quiz;
import com.akayush1108.quizApp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {   // method call from QuizController class

        List<Question> questions = questionDao.findRandomQuestionsByCategory(category,numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {   // method call from QuizController class
        Optional<Quiz> quiz = quizDao.findById(id);  // we will get the quiz with the id
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();  // converting each question into wrapper
        for(Question q : questionsFromDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionsForUser.add(qw);
        }

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {   // method call from QuizController class
        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestions();

        int right = 0;
        int i = 0;
        for(Response response : responses){   // iterate all list and check each response(answer) if right then increase right
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))  // taking response from Response class
                right++;

            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
