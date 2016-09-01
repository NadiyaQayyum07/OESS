package com.cdk.oes.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by qayyumn on 9/1/2016.
 */
@Entity
@Table(name = "user_t")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private int userId;
    @Column
    private String userName;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String country;
    @Column
    private String gender;

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<Exam> examSet;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
