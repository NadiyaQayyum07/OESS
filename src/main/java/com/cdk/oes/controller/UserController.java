package com.cdk.oes.controller;

import com.cdk.oes.dao.AdminDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by qayyumn on 9/1/2016.
 */
@Controller
public class UserController {
    @Autowired

    private AdminDAO adminDAO = null;

    public AdminDAO getAdminDAO() {
        return adminDAO;
    }

    public void setAdminDAO(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    @RequestMapping(value = "/hello")
    public String hello()
    {
        return "hello";
    }
}
