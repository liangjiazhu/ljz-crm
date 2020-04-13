package com.lljz.crm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lljz.crm.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

public interface RoleService extends IService<Role> {

    IPage<Role> selectList(Page<Role> page);

    Integer add(Role role,Integer perId);

    Integer del(Integer roleId);

    Integer update(Role role,Integer perId);

}
