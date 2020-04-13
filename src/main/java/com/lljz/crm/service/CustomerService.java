package com.lljz.crm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lljz.crm.entity.Customer;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface CustomerService extends IService<Customer> {
    IPage<Customer> selectList(Page<Customer> page,  Integer empId);

}
