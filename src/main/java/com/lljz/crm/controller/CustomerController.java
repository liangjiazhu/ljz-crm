package com.lljz.crm.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lljz.crm.entity.Customer;
import com.lljz.crm.entity.Employee;
import com.lljz.crm.service.CustomerService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * 全查询员工信息
     * 设置mybatisPlus分页
     * @return
     */
    @GetMapping("/customer")
    public Map getList(Integer page, Integer limit){
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
        Map<String,Object> map = new HashMap<String,Object>();
        Page<Customer> p = new Page<Customer>();
        p.setSize(limit);       //设置每页记录数
        p.setCurrent(page);     //设置当前页码
        IPage<Customer> iPage = customerService.selectList(p,employee.getEmpId());
        map.put("msg","查询情况");
        map.put("count",iPage.getTotal());
        map.put("data",iPage.getRecords());
        map.put("code",0);
        return map;
    }
}
