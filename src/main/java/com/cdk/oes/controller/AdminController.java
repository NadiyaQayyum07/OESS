package com.cdk.oes.controller;

import com.cdk.oes.dao.AdminDAO;
import com.cdk.oes.dao.UserDAO;
import com.cdk.oes.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by qayyumn on 9/1/2016.
 */
@Controller
public class AdminController {
    @Autowired
    private AdminDAO adminDAO = null;

    @Autowired
    private UserDAO userDAO = null;

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

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public
    @ResponseBody
    String check(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username + " " + password);
        if (username.equals("admin") && password.equals("admin")) {
            return "success";
        } else
            return "failure";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public
    @ResponseBody
    String register(HttpServletRequest request, HttpServletResponse response, User user1) {
        User user = userDAO.register(user1);
        return user.getUserName()+user.getPassword();
    }
}
