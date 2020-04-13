package com.lljz.crm.entity;

import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *
 * @author 梁家柱
 * @since 2020-03-24
 */
@TableName("role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Integer roleId;

    private String roleName;



    @TableField(exist = false)
    private List<Department>  departmentList;

    @TableField(exist = false)
    private List<Permission> permissionList;


}
