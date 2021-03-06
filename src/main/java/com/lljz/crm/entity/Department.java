package com.lljz.crm.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 梁家柱
 * @since 2020-03-24
 */
@TableName("department")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Integer depId;

    private String depName;




}
