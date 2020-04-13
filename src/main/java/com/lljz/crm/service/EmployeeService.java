package com.lljz.crm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lljz.crm.entity.Employee;

public interface EmployeeService extends IService<Employee> {

    IPage<Employee> selectList(Page<Employee> page);

    Employee selectByName(String eName);

    Integer add(Employee employee,Integer roleId);

    Integer del(Integer empId);

    Integer update(Employee employee,Integer roleId);

}
