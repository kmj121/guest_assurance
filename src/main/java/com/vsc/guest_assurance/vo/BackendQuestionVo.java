package com.vsc.guest_assurance.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Description
 * @Author Roger
 * @Date 2021/1/13
 */
public class BackendQuestionVo {
    private String question;
    private String answer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
