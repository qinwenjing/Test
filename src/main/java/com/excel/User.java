package com.excel;

import java.util.List;

public class User {
    private String userName;

    private String cnName;

    private String userType;

    private List<Role> roleList;

    private Dept dept;

    private String status;

    public User(String userName, String cnName, String userType, List<Role> roleList, Dept dept, String status) {
        this.userName = userName;
        this.cnName = cnName;
        this.userType = userType;
        this.roleList = roleList;
        this.dept = dept;
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
