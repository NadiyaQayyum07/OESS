package com.cdk.springmvchibernate.dao;

import com.cdk.springmvchibernate.dto.User;
import com.cdk.springmvchibernate.util.StringUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by qayyumn on 8/25/2016.
 */
@Component
public class UserDAO {
    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public User save(User user){
        com.cdk.springmvchibernate.domain.User domainUser = new com.cdk.springmvchibernate.domain.User();
        domainUser.setName(user.getName());
        domainUser.setGender(user.getGender());
        domainUser.setCountry(user.getCountry());
        domainUser.setDob(user.getDob());
        domainUser.setEmail(user.getEmail());
        domainUser.setPassword(StringUtils.generateRandomPassword());
        domainUser.setUserName(user.getEmail());
        hibernateTemplate.save(domainUser);
        user.setUserId(domainUser.getUserId());
        return user;
    }
}
