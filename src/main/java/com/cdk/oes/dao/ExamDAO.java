package com.cdk.oes.dao;

import com.cdk.oes.dto.Exam;
import com.cdk.oes.dto.Question;
import com.cdk.oes.util.DateUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by qayyumn on 9/1/2016.
 */
@Component
public class ExamDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public String addExam(Exam exam) {
        com.cdk.oes.domain.Exam domainExam = new com.cdk.oes.domain.Exam();
        System.out.println(exam.getStartTime());
        System.out.println(exam.getEndTime());
        System.out.println(exam.getDuration());
        domainExam.setStartTime(new java.sql.Date(exam.getStartTime().getTime()));
        domainExam.setEndTime(new java.sql.Date(exam.getEndTime().getTime()));
        domainExam.setDuration(exam.getDuration());
        if (null != exam.getQuestionSet() && exam.getQuestionSet().size() > 0) {
            Set<com.cdk.oes.domain.Question> domainQuestionSet = new HashSet<>();
            for (com.cdk.oes.dto.Question dtoQuestion : exam.getQuestionSet()) {
                com.cdk.oes.domain.Question domainQuestion = new com.cdk.oes.domain.Question();
                domainQuestion.setTitle(dtoQuestion.getTitle());
                domainQuestion.setDescription(dtoQuestion.getDescription());
                domainQuestion.setOptions(dtoQuestion.getOptions());
                domainQuestion.setMarks(dtoQuestion.getMarks());
                domainQuestion.setAnswers(dtoQuestion.getAnswers());
                domainQuestionSet.add(domainQuestion);
            }
            domainExam.setQuestionSet(domainQuestionSet);
        }

        hibernateTemplate.saveOrUpdate(domainExam);

        return "success";
    }

    public List<Exam> listExam() {
        List<com.cdk.oes.domain.Exam> domainExamList = hibernateTemplate.loadAll(com.cdk.oes.domain.Exam.class);
        List<Exam> dtoExamList = null;
        if (null != domainExamList && domainExamList.size() != 0) {
            dtoExamList = new ArrayList<>();
            for (com.cdk.oes.domain.Exam u : domainExamList) {
                if (u.getEndTime().compareTo(DateUtility.getCurrentDateTime()) >= 0) {
                    Exam exam = new Exam();
                    exam.setExamId(u.getExamId());
                    exam.setStartTime(u.getStartTime());
                    exam.setEndTime(u.getEndTime());
                    exam.setDuration(u.getDuration());
                    dtoExamList.add(exam);
                }
            }

        }
        return dtoExamList;
    }

    public Exam getExam(int examId) {
        com.cdk.oes.domain.Exam domainExam = hibernateTemplate.get(com.cdk.oes.domain.Exam.class, examId);
        Exam exam = new Exam();
        exam.setExamId(domainExam.getExamId());
        exam.setStartTime(domainExam.getStartTime());
        exam.setEndTime(domainExam.getEndTime());
        exam.setDuration(domainExam.getDuration());
        Set<Question> questionSet = new HashSet<>();
        for (com.cdk.oes.domain.Question q : domainExam.getQuestionSet()) {
            Question ques = new Question();
            ques.setQuestionId(q.getQuestionId());
            ques.setTitle(q.getTitle());
            ques.setDescription(q.getDescription());
            ques.setOptions(q.getOptions());
            ques.setMarks(q.getMarks());
            questionSet.add(ques);
        }
        exam.setQuestionSet(questionSet);


        return exam;
    }
}
