package com.akayush1108.quizApp.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Response {  // this class will return the response of the quiz
    private Integer id;   // question id
    private String response;  // question response

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = (String) response;
    }
}
