package com.atguigu.crud.service;

import com.atguigu.crud.bean.Department;
import com.atguigu.crud.dao.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liuzc
 * @create 2021-08-30 21:11
 */
@Service
public class DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;
    
    public List<Department> getDepts() {
        return departmentMapper.selectByExample(null);
    }
}
