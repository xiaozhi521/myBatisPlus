package com.mqf.study;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.mqf.study.beans.Employee;
import com.mqf.study.mapper.EmployeeMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MPTest {
    private ApplicationContext ioc =
            new ClassPathXmlApplicationContext("applicationContext.xml");


    private EmployeeMapper employeeMapper =  ioc.getBean("employeeMapper",EmployeeMapper.class);

    /**
     *  AR 分页操作
     */
    @Test
    public void ARSelectPageTest(){
        Employee employee = new Employee();

//        Page<Employee> employeePage = employee.selectPage(
//                new Page<Employee>(1, 1),
//                new EntityWrapper<Employee>().like("last_name", "老")
//        );
//        List<Employee> records = employeePage.getRecords();
//        System.out.println(records);
//        Map<String, Object> condition = employeePage.getCondition();
//        System.out.println("========== condition : " + condition);

        Page<Employee> page = new Page<Employee>(1,2);
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("last_name","MP1");
        page.setCondition(condition);
//        page.setCurrent(1);
//        page.setSize(1);
        Page<Employee> page1 = employee.selectPage(page, new EntityWrapper<Employee>());

        Map<String, Object> condition1 = page1.getCondition();
        System.out.println(condition1);
        List<Employee> records = page1.getRecords();
        System.out.println(records);


    }

    /**
     *  AR delete 操作
     *
     *  删除不存在的逻辑上属于成功
     *
     *  public static boolean delBool(Integer result) {
     *   return null != result && result >= 0;
     *  }
     */
    @Test
    public void ARDeleteTest(){
        Employee employee = new Employee();

//        employee.setId(15);
//        boolean result = employee.deleteById();

//        boolean result = employee.deleteById(15);

        boolean result = employee.delete(new EntityWrapper<Employee>().eq("id", 15));

        System.out.println("result : " + result);
    }
    /**
     *  AR Select* 操作
     */
    @Test
    public void ARSelectTest(){
        Employee employee = new Employee();

//        employee.setId(17);
//        Employee result = employee.selectById();
//        System.out.println("result: " + result);

//        Employee employee1 = employee.selectById(14);
//        System.out.println("empoyeel1: "  + employee1);

//        List<Employee> employeeList = employee.selectAll();
//        System.out.println("size: " + employeeList.size());

//        int selectCount = employee.selectCount(new EntityWrapper().like("last_name","MP"));
//        System.out.println("selectCount : " + selectCount);

        List<Employee> employeeList = employee.selectList(new EntityWrapper().like("last_name", "MP"));
        System.out.println("selectList: " + employeeList);


    }


    /**
     *  AR upddate 操作
     *  UPDATE tbl_employee SET last_name=?, email=?, gender=?, age=? WHERE id=?
     */
    @Test
    public void ARUpdateTest(){
        Employee employee = new Employee();
        employee.setId(17);
        employee.setGender(1);
        employee.setAge(17);
        employee.setLastName("Alen");
        employee.setEmail("Alen@mqf.com");
        boolean update = employee.updateById();
        System.out.println("result : " + update);
    }


    /**
     *  AR insert 操作
     */
    @Test
    public void ARInsertTest(){
        Employee employee = new Employee();
        employee.setGender(1);
        employee.setAge(64);
        employee.setLastName("Tim");
        employee.setEmail("tim64@mqf.com");
        boolean insert = employee.insert();
        System.out.println("resulet: " + insert);
    }
}
