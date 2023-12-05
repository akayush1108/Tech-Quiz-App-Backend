package com.akayush1108.quizApp.controller;

import com.akayush1108.quizApp.model.Question;
import com.akayush1108.quizApp.model.QuestionWrapper;
import com.akayush1108.quizApp.model.Response;
import com.akayush1108.quizApp.service.QuizService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
        //  delete method
        return quizService.createQuiz(category,numQ,title);   // method calling (QuizService class)
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);   // method calling (QuizService class)
    }

    @PostMapping("submit/{id}")   // user will submit the quiz answer(response)
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);  // method call (QuizService class) and return result
    }

}
