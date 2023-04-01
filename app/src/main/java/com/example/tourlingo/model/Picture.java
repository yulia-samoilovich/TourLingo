package com.example.tourlingo.model;

public class Picture {
    private String answer;
    private String option1;
    private String option2;

    public Picture(String answer, String option1, String option2) {
        this.answer = answer;
        this.option1 = option1;
        this.option2 = option2;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    @Override
    public String toString() {
        return "Options:" + answer + option1 + option2;
    }
}
