package com.lljz.crm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lljz.crm.entity.Permission;
import com.lljz.crm.mapper.PermissionMapper;
import com.lljz.crm.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lljz.crm.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;


    /**
     * 全查询
     * @param page
     * @return
     */
    @Override
    public IPage<Permission> selectList(Page<Permission> page) {
        return permissionMapper.selectList(page);
    }

    /**
     * 添加
     * @param perm
     * @return
     */
    @Override
    public Integer add(Permission perm) {
        perm.setCreateTime(StringUtils.getDote());
        perm.setUpdateTime(perm.getCreateTime());
        perm.setIsDel(0);
        int insert = permissionMapper.insert(perm);

        return insert;
    }

    /**
     * 删除
     * @param perId
     * @return
     */
    @Override
    public Integer del(Integer perId) {
        Permission permission= new Permission();
        permission.setPerId(perId);
        permission.setUpdateTime(StringUtils.getDote());
        permission.setIsDel(1);
        int i = permissionMapper.updateById(permission);
        return i;
    }

    /**
     * 修改
     * @param perm
     * @return
     */
    @Override
    public Integer update(Permission perm) {

        perm.setUpdateTime(StringUtils.getDote());
        perm.setIsDel(0);
        int i = permissionMapper.updateById(perm);
        return i;
    }
}
