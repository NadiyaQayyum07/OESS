package com.cdk.oes.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

/**
 * Created by qayyumn on 9/1/2016.
 */
@Entity
@Table(name = "exam_t")
public class Exam {
    @Id
    @GeneratedValue
    @Column(name = "exam_id")
    private int examId;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @Column
    private int duration;

    @OneToMany
    @JoinColumn(name = "exam_id")
    private Set<Question> questionSet;

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Set<Question> getQuestionSet() {
        return questionSet;
    }

    public void setQuestionSet(Set<Question> questionSet) {
        this.questionSet = questionSet;
    }
}
