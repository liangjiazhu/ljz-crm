package com.lljz.crm.entity;



import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;



@TableName("emp_role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpRole  extends BaseEntity{



    private Integer empId;

    private Integer roleId;




}
