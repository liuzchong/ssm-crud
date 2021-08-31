package com.atguigu.crud.test;

import com.atguigu.crud.bean.Employee;
import com.github.pagehelper.PageInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/**
 * 使用Spring测试模块提供的测试请求功能，测试crud请求的正确性
 * spring4测试的时候，需要servlet3.0的支持
 * @author liuzc
 * @create 2021-08-30 10:16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
//还要引入springMVC配置文件(文件路径)==>file:src/main/resources/springMVC.xml
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:springMVC.xml"})
public class MvcTest {
    //传入springMVC的ioc
    @Autowired//还需要在类上添加注解@WebAppConfiguration
    WebApplicationContext context;
    //虚拟mvc请求，获取到处理结果。
    MockMvc mockMvc;

    @Before//每次使用都需要初始化，所以添加注解@Before(junit)
    //mvc使用需要用到初始化
    public void initMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testPage() throws Exception {
        //模拟请求拿到返回值
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "5"))
                .andReturn();
        //请求成功以后，请求域中会有pageInfo，我们可以取出pageInfo进行验证
        MockHttpServletRequest request = result.getRequest();
        PageInfo pi = (PageInfo) request.getAttribute("pageInfo");
        System.out.println("当前页码：" + pi.getPageNum());
        System.out.println("总页码：" + pi.getPages());
        System.out.println("总记录数：" + pi.getTotal());
        System.out.println("在页面连续显示的页码：" );
        int[] nums = pi.getNavigatepageNums();
        for (int i : nums) {
            System.out.print(" " + i);
        }
        System.out.println();
        //还可以获取员工数据
        List<Employee> list = pi.getList();
        for (Employee employee: list) {
            System.out.println("ID:" + employee.getdId() + "===>Name:" + employee.getEmpName());
        }
    }
}
