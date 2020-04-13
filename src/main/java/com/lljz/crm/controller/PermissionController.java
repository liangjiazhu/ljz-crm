package com.lljz.crm.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lljz.crm.entity.Permission;

import com.lljz.crm.service.PermissionService;
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
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/perm")
    public List<Permission> roles(){

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("is_del",0);
        List<Permission> list = permissionService.list();
        for (Permission perm : list) {
            System.out.println(list);
        }
        return list;
    }

    /**
     * 全查询员工信息
     * 设置mybatisPlus分页
     * @return
     */
    @GetMapping("/select/perm")
    public Map getList(Integer page, Integer limit){
        Map<String,Object> map = new HashMap<String,Object>();
        Page<Permission> p = new Page<Permission>();
        //设置每页记录数
        p.setSize(limit);
        //设置当前页码
        p.setCurrent(page);
        IPage<Permission> iPage = permissionService.selectList(p);
        map.put("msg","查询情况");
        map.put("count",iPage.getTotal());
        map.put("data",iPage.getRecords());
        map.put("code",0);
        return map;
    }



    /**
     * 添加
     * @param perm
     * @param
     * @return
     */
    @PostMapping("/select/perm")
    public Map add(Permission perm){
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("state",permissionService.add(perm));
        return result;
    }


    /**
     * 删除
     * @param perId
     * @return
     */
    @DeleteMapping("/select/perm/del/{perId}")
    public Map del(@PathVariable Integer perId){

        System.out.println("进入删除方法，id是："+perId);
        Integer del = permissionService.del(perId);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("state",del);
        return result;
    }


    /**
     * 修改
     * @param permission
     * @param
     * @return
     */
    @PutMapping("/select/perm")
    public Map edit(Permission permission){

        Integer update = permissionService.update(permission);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("state",update);
        return result;
    }

}
