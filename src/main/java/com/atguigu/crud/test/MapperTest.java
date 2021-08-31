package com.atguigu.crud.test;


import com.atguigu.crud.bean.Department;
import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.dao.DepartmentMapper;
import com.atguigu.crud.dao.EmployeeMapper;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.apache.ibatis.javassist.ClassPath;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * 测试dao层的工作
 * @author liuzc
 * @create 2021-08-29 20:27
 * 推荐spring的项目可以使用Spring的单元测试，可以自动注入我们需要的组件
 * 1、导入spring test测试模块
 * 2、@ContextConfiguration指定spring配置文件的位置
 * 3、直接autowired要使用的组件即可
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    SqlSession sqlSession;//批量操作的sqlSession

    @Test
    public void testCRUD(){
        //以下两步是原生的方法
        //1、创建SpringIOC容器
        //ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        //2、从容器中获取mapper
        //DepartmentMapper mapper = ioc.getBean(DepartmentMapper.class);
        System.out.println(departmentMapper);

        //1、插入几个部门
        //departmentMapper.insertSelective(new Department(null,"开发部"));
        //departmentMapper.insertSelective(new Department(null,"测试部"));

        //2、生成员工数据，测试插入
        //employeeMapper.insertSelective(new Employee(null,"jerry","M","jerry@qq.com",1));

        /*for(){
            employeeMapper.insertSelective(new Employee(null,"jerry","M","jerry@qq.com",1));
        }*/
        //3、批量插入多个员工：批量，使用可以执行批量操作的sqlSession
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for (int i = 0; i < 1000; i++) {
            String uid = UUID.randomUUID().toString().substring(0, 5) + i;
            mapper.insertSelective(new Employee(null,uid,"M",uid+"@atguigu.com",1));
        }
        System.out.println("批量操作完成！");

    }
}

