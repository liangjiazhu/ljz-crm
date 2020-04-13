package com.lljz.crm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lljz.crm.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;


public interface PermissionService extends IService<Permission> {

    IPage<Permission> selectList(Page<Permission> page);

    Integer add(Permission perm);

    Integer del(Integer empId);

    Integer update(Permission perm);
}
