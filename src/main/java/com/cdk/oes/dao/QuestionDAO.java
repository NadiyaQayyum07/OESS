package com.cdk.oes.dao;

import com.cdk.oes.dto.Question;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Created by qayyumn on 9/1/2016.
 */
public class QuestionDAO {
    private HibernateTemplate hibernateTemplate = null;

    public QuestionDAO(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public Question addQuestion(Question question){
        com.cdk.oes.domain.Question domainQuestion = new com.cdk.oes.domain.Question();
        domainQuestion.setTitle(question.getTitle());
        domainQuestion.setDescription(question.getDescription());
        domainQuestion.setMarks(question.getMarks());
        domainQuestion.setOptions(question.getOptions());
        domainQuestion.setAnswers(question.getAnswers());
        hibernateTemplate.save(domainQuestion);
        question.setQuestionId(domainQuestion.getQuestionId());
        return question;
    }
}
