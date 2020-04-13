package com.lljz.crm.service.impl;

import com.lljz.crm.entity.Department;
import com.lljz.crm.mapper.DepartmentMapper;
import com.lljz.crm.service.DepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Transactional
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

}
