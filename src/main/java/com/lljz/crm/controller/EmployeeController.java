package com.lljz.crm.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lljz.crm.entity.Employee;
import com.lljz.crm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService service;


    /**
     * 全查询员工信息
     * 设置mybatisPlus分页
     * @return
     */
    @GetMapping("/emp")
    public Map getList(Integer page,Integer limit){
        Map<String,Object> map = new HashMap<String,Object>();
        Page<Employee> p = new Page<Employee>();
        p.setSize(limit);       //设置每页记录数
        p.setCurrent(page);     //设置当前页码
        IPage<Employee> iPage = service.selectList(p);
        map.put("msg","查询情况");
        map.put("count",iPage.getTotal());
        map.put("data",iPage.getRecords());
        map.put("code",0);
        return map;
    }


    /**
     * 添加
     * @param employee
     * @param roleId
     * @return
     */
    @PostMapping("/emp")
    public Map add(Employee employee,Integer roleId){
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("state",service.add(employee,roleId));
        return result;
    }

    /**
     * 删除
     * @param empId
     * @return
     */
    @DeleteMapping("/emp/del/{empId}")
    public Map del(@PathVariable Integer empId){
        Integer del = service.del(empId);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("state",del);
        return result;
    }


    /**
     * 修改
     * @param employee
     * @param
     * @return
     */
    @PutMapping("/emp")
    public Map edit(Employee employee, Integer roleId){

        Integer update = service.update(employee, roleId);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("state",update);
        return result;
    }




}
