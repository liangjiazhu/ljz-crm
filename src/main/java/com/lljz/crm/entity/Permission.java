package com.lljz.crm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@TableName("permission")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Integer perId;

    private String perName;

    private String url;

    private String permission;



}
