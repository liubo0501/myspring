package com.imooc.model;

import java.util.Date;

public class User {
    private Short userid;

    private String username;

    private String password;

    private String name;

    private String tele;

    private String email;

    private String company;

    private String dept;

    private Short managerid;

    private Boolean roleid;

    private Boolean online;

    private Date createt;

    private Date modifyt;

    private Date lastlogint;

    private Integer count;

    private Boolean enable;

    public Short getUserid() {
        return userid;
    }

    public void setUserid(Short userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele == null ? null : tele.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept == null ? null : dept.trim();
    }

    public Short getManagerid() {
        return managerid;
    }

    public void setManagerid(Short managerid) {
        this.managerid = managerid;
    }

    public Boolean getRoleid() {
        return roleid;
    }

    public void setRoleid(Boolean roleid) {
        this.roleid = roleid;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public Date getCreatet() {
        return createt;
    }

    public void setCreatet(Date createt) {
        this.createt = createt;
    }

    public Date getModifyt() {
        return modifyt;
    }

    public void setModifyt(Date modifyt) {
        this.modifyt = modifyt;
    }

    public Date getLastlogint() {
        return lastlogint;
    }

    public void setLastlogint(Date lastlogint) {
        this.lastlogint = lastlogint;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}