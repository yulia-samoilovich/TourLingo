package com.example.tourlingo.model;

public class Sentence {
    private String sentence, answer, option1, option2;
    private long startTime, endTime;

    public Sentence(String sentence, String answer, String option1, String option2) {
        this.sentence = sentence;
        this.answer = answer;
        this.option1 = option1;
        this.option2 = option2;
    }
    public Sentence(String answer, String option1, String option2) {
        this.answer = answer;
        this.option1 = option1;
        this.option2 = option2;
    }
    public Sentence(long startTime) {
        this.startTime = startTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
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
        return "Options: " + answer + option1 + option2;
    }
}