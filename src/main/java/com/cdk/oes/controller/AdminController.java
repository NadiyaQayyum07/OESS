package com.cdk.oes.controller;

import com.cdk.oes.dao.AdminDAO;
import com.cdk.oes.dao.ExamDAO;
import com.cdk.oes.dao.QuestionDAO;
import com.cdk.oes.dao.UserDAO;
import com.cdk.oes.dto.Exam;
import com.cdk.oes.dto.Question;
import com.cdk.oes.dto.User;
import com.cdk.oes.service.AdminService;
import com.cdk.oes.util.DateUtility;
import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

import static java.lang.System.out;

/**
 * Created by qayyumn on 9/1/2016.
 */
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService = null;

    @Autowired
    private AdminDAO adminDAO = null;

    @Autowired
    private UserDAO userDAO = null;

    @Autowired
    private QuestionDAO questionDAO = null;

    @Autowired
    private ExamDAO examDAO = null;

    public AdminService getAdminService() {
        return adminService;
    }

    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    public ExamDAO getExamDAO() {
        return examDAO;
    }

    public void setExamDAO(ExamDAO examDAO) {
        this.examDAO = examDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public AdminDAO getAdminDAO() {
        return adminDAO;
    }

    public void setAdminDAO(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    public void setQuestionDAO(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    public QuestionDAO getQuestionDAO() {
        return questionDAO;
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public
    @ResponseBody
    String check(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (userDAO.getUser(username, password) == "admin") {
            return "successAdmin";
        } else if (userDAO.getUser(username, password) == "user") {
            return "successUser";
        } else {
            return "failure";
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public
    @ResponseBody
    String register(HttpServletRequest request, HttpServletResponse response, User user1) {
        int role = Integer.parseInt(request.getParameter("makeAdmin"));
        user1.setRole(role);
        User user = userDAO.register(user1);
        return user.getUserName() + user.getPassword();
    }

    @RequestMapping(value = "/listCandidate", method = RequestMethod.GET)
    public
    @ResponseBody
    String listCandidate(HttpServletRequest request, HttpServletResponse response) {
        List<User> userList = userDAO.listCandidates();
        String jsonString = "[";
        for (User u : userList) {
            jsonString += new Gson().toJson(u) + ",";
        }
        jsonString = jsonString.substring(0, jsonString.length() - 1);
        jsonString += "]";
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        out.println(jsonString);
        return jsonString;
    }


    @RequestMapping(value = "/listQuestion", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Question> listQuestion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return questionDAO.listQuestion();

    }

    @RequestMapping(value = "/insertData", method = RequestMethod.POST)
    public ModelAndView insertData(HttpServletRequest request, HttpServletResponse response) {
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        Exam exam = null;

        try {
            List<FileItem> list = servletFileUpload.parseRequest(request);
            if (list.size() == 4) {
                exam = new Exam();
                for (FileItem f : list) {
                    if (f.isFormField()) {
                        if (f.getFieldName().equals("startTime")) {
                            String start = f.getString();
                            exam.setStartTime(DateUtility.stringToDateTime(start));
                        }
                        if (f.getFieldName().equals("endTime")) {
                            String end = f.getString();
                            exam.setEndTime(DateUtility.stringToDateTime(end));
                        }
                        if (f.getFieldName().equals("duration")) {
                            String duration = f.getString();
                            exam.setDuration(Integer.parseInt(duration));
                        }
                    } else {
                        File file = new File(f.getName());
                        f.write(file);
                        adminService.addQuestions(exam, file.getAbsolutePath());
                    }
                }
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView();
    }
}
