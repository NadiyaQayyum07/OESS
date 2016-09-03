package com.cdk.oes.controller;

import com.cdk.oes.dao.ExamDAO;
import com.cdk.oes.dao.QuestionDAO;
import com.cdk.oes.dto.Exam;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static java.lang.System.out;

/**
 * Created by qayyumn on 9/1/2016.
 */
@Controller
public class CandidateController {

    @Autowired
    private ExamDAO examDAO = null;

    @Autowired
    private QuestionDAO questionDAO = null;

    public QuestionDAO getQuestionDAO() {
        return questionDAO;
    }

    public void setQuestionDAO(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    public ExamDAO getExamDAO() {
        return examDAO;
    }

    public void setExamDAO(ExamDAO examDAO) {
        this.examDAO = examDAO;
    }


    @RequestMapping(value = "/launchTest")
    public
    @ResponseBody
    String
    launchTest(HttpServletRequest request, HttpServletResponse response) {
        Exam exam = examDAO.getExam(Integer.parseInt(request.getParameter("examId")));
        String jsonString = "[";
        jsonString += new Gson().toJson(exam) + ",";
        jsonString = jsonString.substring(0, jsonString.length() - 1);
        jsonString += "]";
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        out.println(jsonString);
        return jsonString;
    }


    @RequestMapping(value = "/listExams")
    public
    @ResponseBody
    String listExams(HttpServletRequest request, HttpServletResponse response) {
        List<Exam> examList = examDAO.listExam();
        String jsonString = "[";
        for (Exam e : examList) {
            jsonString += new Gson().toJson(e) + ",";
        }
        jsonString = jsonString.substring(0, jsonString.length() - 1);
        jsonString += "]";
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        out.println(jsonString);
        return jsonString;
    }
}
