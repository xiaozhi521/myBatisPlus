package com.mqf.study;

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
     * 通用 删除操作
     */
    @Test
    public void testCommonDelete() {
        //1 .根据id进行删除
//        Integer result = employeeMapper.deleteById(8);
//        System.out.println("result: " + result );
        //2. 根据 条件进行删除
//		Map<String,Object> columnMap = new HashMap<String,Object>();
//		columnMap.put("last_name", "MP1");
//		columnMap.put("email", "mp1@163.com");
//		Integer result = employeeMapper.deleteByMap(columnMap);
//		System.out.println("result: " + result );

        //3. 批量删除
		List<Integer> idList = new ArrayList<Integer>();
		idList.add(11);
		idList.add(12);
		Integer result = employeeMapper.deleteBatchIds(idList);
		System.out.println("result: " + result );
    }
    /**
     * 通用 查询操作
     */
    @Test
    public void  testCommonSelect() {
        //1. 通过id查询
		Employee employee = employeeMapper.selectById(6);
		System.out.println("1. 通过id查询：" + employee);

        //2. 通过多个列进行查询    id  +  lastName
		Employee  employee2 = new Employee();
		employee2.setId(2);
		employee.setLastName("苍老师");
		employee.setGender(0);

		Employee result = employeeMapper.selectOne(employee);
		System.out.println("2. 通过多个列进行查询 : " +result );


        //3. 通过多个id进行查询    <foreach>
		List<Integer> idList = new ArrayList<Integer>();
		idList.add(4);
		idList.add(5);
		idList.add(6);
		List<Employee> emps = employeeMapper.selectBatchIds(idList);
		System.out.println("3. 通过多个id进行查询：" + emps);

        //4. 通过Map封装条件查询
		Map<String,Object> columnMap = new HashMap<String,Object>();
		columnMap.put("last_name", "Tom");
		columnMap.put("gender", 1);

		List<Employee> emps2 = employeeMapper.selectByMap(columnMap);
		System.out.println("4. 通过Map封装条件查询:" + emps);

        //5. 分页查询
        List<Employee> emps3 = employeeMapper.selectPage(new Page<Employee>(3, 2), null);
        System.out.println("5. 分页查询" + emps);
    }
    /**
     * 通用 更新操作
     */
    @Test
    public void commonUpdate() {
        //初始化修改对象
        Employee employee = new Employee();
        employee.setId(6);
        employee.setLastName("小泽老师");
        employee.setEmail("cang@sina.com");
        employee.setGender(0);
        //employee.setAge(33);

//        Integer result = employeeMapper.updateById(employee);

        //更新所有的字段，如果java bean 与数据库字段不匹配，会抛出异常
        Integer result = employeeMapper.updateAllColumnById(employee);

        System.out.println("result: " + result );
    }
    /**
     * 通用 插入操作
     */
    @Test
    public void commonInsert() {

        //初始化Employee对象
        Employee employee  = new Employee();
        employee.setLastName("MP1");
        employee.setEmail("mp1@163.com");
        employee.setGender(1);
        employee.setAge(22);
//        employee.setSalary(20000.0);
        //插入到数据库
        // insert方法在插入时， 会根据实体类的每个属性进行非空判断，只有非空的属性对应的字段才会出现到SQL语句中
        Integer result = employeeMapper.insert(employee);

        //insertAllColumn方法在插入时， 不管属性是否非空， 属性所对应的字段都会出现到SQL语句中.
//        Integer result = employeeMapper.insertAllColumn(employee);
//
        System.out.println("result: " + result );
//
//        //获取当前数据在数据库中的主键值
        Integer key = employee.getId();
        System.out.println("key:" + key );
    }

    //测试数据库连接
    @Test
    public void testDataSource() throws Exception {
        DataSource ds  = ioc.getBean("dataSource",DataSource.class);
        System.out.println(ds);
        Connection conn = ds.getConnection();
        System.out.println(conn);
    }
}
