package com.example.tourlingo.model;

public class Picture {
    private String answer;
    private String option1;
    private String option2;
    private long startTime, endTime;

    public Picture(long startTime) {
        this.startTime = startTime;
    }

    public Picture(String answer, String option1, String option2) {
        this.answer = answer;
        this.option1 = option1;
        this.option2 = option2;
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
