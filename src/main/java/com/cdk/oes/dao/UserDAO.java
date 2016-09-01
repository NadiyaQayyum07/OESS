package com.cdk.oes.dao;

import com.cdk.oes.dto.User;
import com.cdk.oes.util.PasswordUtility;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Created by qayyumn on 9/1/2016.
 */
public class UserDAO {
    private HibernateTemplate hibernateTemplate = null;

    public UserDAO() {
    }

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public User register(User user){
        com.cdk.oes.domain.User domainUser = new com.cdk.oes.domain.User();
        domainUser.setName(user.getName());
        domainUser.setGender(user.getGender());
        domainUser.setCountry(user.getCountry());
        domainUser.setEmail(user.getEmail());
        domainUser.setPassword(PasswordUtility.generateRandomPassword());
        domainUser.setUserName(user.getEmail());
        hibernateTemplate.save(domainUser);
        user.setUserId(domainUser.getUserId());
        user.setUserName(domainUser.getUserName());
        user.setPassword(domainUser.getPassword());
        return user;
    }
}
