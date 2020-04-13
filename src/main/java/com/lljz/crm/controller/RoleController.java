package com.lljz.crm.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lljz.crm.entity.Role;
import com.lljz.crm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 梁家柱
 * @since 2020-03-24
 */
@RestController
public class RoleController {

    @Autowired
     private RoleService roleService;


    @GetMapping("/role")
    public List<Role> roles(){
        System.out.println("进入角色查询方法");
        List<Role> list = roleService.list();
        for (Role role : list) {
            System.out.println(list);
        }
        return list;
    }



    /**
     * 全查询员工信息
     * 设置mybatisPlus分页
     * @return
     */
    @GetMapping("/select/role")
    public Map getList(Integer page, Integer limit){
        Map<String,Object> map = new HashMap<String,Object>();
        Page<Role> p = new Page<Role>();
        //设置每页记录数
        p.setSize(limit);
        //设置当前页码
        p.setCurrent(page);
        IPage<Role> iPage = roleService.selectList(p);
        map.put("msg","查询情况");
        map.put("count",iPage.getTotal());
        map.put("data",iPage.getRecords());
        map.put("code",0);
        return map;
    }


    /**
     * 添加
     * @param role
     * @param perId
     * @return
     */
    @PostMapping("/select/role")
    public Map add(Role role,Integer perId){
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("state",roleService.add(role,perId));
        return result;
    }


    /**
     * 删除
     * @param roleId
     * @return
     */
    @DeleteMapping("/select/role/del/{roleId}")
    public Map del(@PathVariable Integer roleId){
        Integer del = roleService.del(roleId);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("state",del);
        return result;
    }


    /**
     * 修改
     * @param role
     * @param
     * @return
     */
    @PutMapping("/select/role")
    public Map edit(Role role, Integer perId){

        Integer update = roleService.update(role, perId);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("state",update);
        return result;
    }
}
