package com.cdk.springmvchibernate.controller;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import com.cdk.springmvchibernate.dao.UserDAO;
import com.cdk.springmvchibernate.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController extends MultiActionController {
    @Autowired
    private UserDAO userDAO = null;

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public
    @ResponseBody
    User add(HttpServletRequest request, HttpServletResponse response, User userD) {
        User user = userDAO.save(userD);
        return user;
    }



}
