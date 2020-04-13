package com.lljz.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lljz.crm.entity.PermRole;
import com.lljz.crm.entity.Role;
import com.lljz.crm.mapper.PermRoleMapper;
import com.lljz.crm.mapper.RoleMapper;
import com.lljz.crm.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lljz.crm.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {


    @Autowired
    private PermRoleMapper permRoleMapper;

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public IPage<Role> selectList(Page<Role> page) {
        return roleMapper.selectList(page);
    }

    /**
     * 添加方法
     * @param role
     * @param perId
     * @return
     */
    @Override
    public Integer add(Role role, Integer perId) {
        role.setCreateTime(StringUtils.getDote());
        role.setUpdateTime(role.getCreateTime());
        role.setIsDel(0);

        int insert = roleMapper.insert(role);

        Integer roleId = role.getRoleId();

        PermRole permRole = new PermRole();
        permRole.setRoleId(roleId);
        permRole.setPerId(perId);
        permRole.setCreateTime(StringUtils.getDote());
        permRole.setUpdateTime(permRole.getCreateTime());
        permRole.setIsDel(0);
        permRoleMapper.insert(permRole);

        return insert;
    }


    /**
     * 删除
     * @param roleId
     * @return
     */
    @Override
    public Integer del(Integer roleId) {
        Role employee = new Role();
        employee.setRoleId(roleId);
        employee.setIsDel(1);
        int i = roleMapper.updateById(employee);
        return i;
    }

    /**
     * 修改
     * @param role
     * @param perId
     * @return
     */
    @Override
    public Integer update(Role role, Integer perId) {

        role.setUpdateTime(StringUtils.getDote());


        int insert = roleMapper.updateById(role);


        PermRole permRole = new PermRole(role.getRoleId(),perId);
        permRole.setUpdateTime(StringUtils.getDote());

        AbstractWrapper wrapper = new QueryWrapper();
        wrapper.eq("role_id",role.getRoleId());
        int update = permRoleMapper.update(permRole, wrapper);

        return insert;
    }


}
