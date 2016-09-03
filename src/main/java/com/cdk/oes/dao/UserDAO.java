package com.cdk.oes.dao;

import com.cdk.oes.dto.User;
import com.cdk.oes.util.PasswordUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qayyumn on 9/1/2016.
 */
@Component
public class UserDAO {

    @Autowired
    private HibernateTemplate hibernateTemplate = null;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public User register(User user) {
        com.cdk.oes.domain.User domainUser = new com.cdk.oes.domain.User();
        domainUser.setName(user.getName());
        domainUser.setGender(user.getGender());
        domainUser.setCountry(user.getCountry());
        domainUser.setEmail(user.getEmail());
        domainUser.setPassword(PasswordUtility.generateRandomPassword());
        domainUser.setUserName(user.getEmail());
        domainUser.setRole(user.getRole());
        System.out.println("userRole    " + user.getRole());
        hibernateTemplate.saveOrUpdate(domainUser);
        user.setUserId(domainUser.getUserId());
        user.setUserName(domainUser.getUserName());
        user.setPassword(domainUser.getPassword());
        return user;
    }

    public List<User> listCandidates() {
        List<com.cdk.oes.domain.User> domainUserList = hibernateTemplate.loadAll(com.cdk.oes.domain.User.class);
        List<User> dtoUserList = null;
        if (null != domainUserList && domainUserList.size() != 0) {
            dtoUserList = new ArrayList<>();

            for (com.cdk.oes.domain.User u : domainUserList) {
                if (u.getRole() == 0) {
                    User user = new User();
                    user.setUserId(u.getUserId());
                    user.setName(u.getName());
                    user.setGender(u.getGender());
                    user.setCountry(u.getCountry());
                    user.setEmail(u.getEmail());
                    user.setUserName(u.getUserName());
                    user.setPassword(u.getPassword());
                    user.setRole(u.getRole());
                    dtoUserList.add(user);
                }
            }
        }
        return dtoUserList;
    }

    public String getUser(String username, String password) {
        try {
            List<com.cdk.oes.domain.User> userList = hibernateTemplate.find("from com.cdk.oes.domain.User u where u.userName=? and u.password=?", username, password);
            System.out.println(userList.size());
            if (userList.size() == 1) {
                if (userList.get(0).getRole() == 1) {
                    return "admin";
                } else
                    return "user";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
