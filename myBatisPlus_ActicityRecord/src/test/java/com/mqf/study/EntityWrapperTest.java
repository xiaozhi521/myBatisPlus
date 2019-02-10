package com.mqf.study;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.mqf.study.beans.Employee;
import com.mqf.study.mapper.EmployeeMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class EntityWrapperTest {
    private ApplicationContext ioc =
            new ClassPathXmlApplicationContext("applicationContext.xml");


    private EmployeeMapper employeeMapper =  ioc.getBean("employeeMapper",EmployeeMapper.class);


}
