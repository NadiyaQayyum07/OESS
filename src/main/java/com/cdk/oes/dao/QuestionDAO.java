package com.cdk.oes.dao;

import com.cdk.oes.dto.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qayyumn on 9/1/2016.
 */
@Component
public class QuestionDAO {


    @Autowired
    private HibernateTemplate hibernateTemplate = null;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }




    public Question addQuestion(Question question){
        com.cdk.oes.domain.Question domainQuestion = new com.cdk.oes.domain.Question();
        //domainQuestion.setTitle(question.getTitle());
        domainQuestion.setDescription(question.getDescription());
        domainQuestion.setMarks(question.getMarks());
        domainQuestion.setOptions(question.getOptions());
        domainQuestion.setAnswers(question.getAnswers());
        hibernateTemplate.saveOrUpdate(domainQuestion);
        question.setQuestionId(domainQuestion.getQuestionId());
        return question;
    }

    public List<Question> listQuestion(){
        List<com.cdk.oes.domain.Question> domainQuestionList = hibernateTemplate.loadAll(com.cdk.oes.domain.Question.class);
        List<Question> dtoQuestionList = null;
        if(domainQuestionList !=null && domainQuestionList.size()!=0){
            dtoQuestionList = new ArrayList<>();
            for(com.cdk.oes.domain.Question q: domainQuestionList){
                Question question = new Question();
                question.setQuestionId(q.getQuestionId());
                question.setTitle(q.getTitle());
                question.setDescription(q.getDescription());
                question.setOptions(q.getOptions());
                /*question.setTopic_id(q.getTopic_id());
                question.setSubtopic_id(q.getSubtopic_id());*/
                question.setAnswers(q.getAnswers());
                dtoQuestionList.add(question);
            }

        }
        return dtoQuestionList;
    }


        /*public List<Question> fetchQuestions(int examId) {
        List<com.cdk.oes.domain.Question> domainQuestionList = hibernateTemplate.get(com.cdk.oes.domain.Question.class,examId);
        List<Question> dtoQuestionList = null;
        if(domainQuestionList !=null && domainQuestionList.size()!=0){
            dtoQuestionList = new ArrayList<>();
            for(com.cdk.oes.domain.Question q: domainQuestionList){
                Question questions = new Question();
                questions.setQuestionId(q.getQuestionId());
                //questions.setTitle(q.getTitle());
                questions.setDescription(q.getDescription());
                questions.setOptions(q.getOptions());
                dtoQuestionList.add(questions);
            }

        }
        return dtoQuestionList;
    }*/
}
