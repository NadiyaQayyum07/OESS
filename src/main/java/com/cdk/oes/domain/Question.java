package com.cdk.oes.domain;

import javax.persistence.*;

/**
 * Created by qayyumn on 9/1/2016.
 */
@Entity
@Table(name = "question_t")
public class Question {
    @Id
    @GeneratedValue
    @Column(name = "question_id")
    private int questionId;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private String options;
    @Column
    private int marks;
    @Column
    private String answers;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }
}
