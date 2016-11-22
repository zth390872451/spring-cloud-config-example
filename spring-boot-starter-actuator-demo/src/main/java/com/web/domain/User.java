package com.web.domain;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/11/23 0023.
 */
@Table(name = "zth_user")
@Entity
public class User {
    private Long id;
    private String userName;
    private String password;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
