package com.atguigu.crud.bean;

public class Department {
    private Integer deptId;

    private String deptName;

    //如果生成有参构造器，一定要再生成无参构造器(反射会用到)
    public Department() {
    }

    public Department(Integer deptId, String deptName) {

        this.deptId = deptId;
        this.deptName = deptName;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }
}