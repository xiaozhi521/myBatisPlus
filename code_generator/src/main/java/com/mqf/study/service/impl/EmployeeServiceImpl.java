package com.mqf.study.service.impl;

import com.mqf.study.beans.Employee;
import com.mqf.study.mapper.EmployeeMapper;
import com.mqf.study.service.EmployeeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mqf
 * @since 2019-02-10
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
