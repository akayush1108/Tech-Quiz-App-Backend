package com.akayush1108.quizApp.dao;

import com.akayush1108.quizApp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Integer> {    // Quiz is basically type of "table" that we are working with in Quiz class. And Integer is Primary key.
}
