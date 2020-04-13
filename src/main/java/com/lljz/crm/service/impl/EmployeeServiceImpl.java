package com.lljz.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lljz.crm.entity.EmpRole;
import com.lljz.crm.entity.Employee;
import com.lljz.crm.mapper.EmpRoleMapper;
import com.lljz.crm.mapper.EmployeeMapper;
import com.lljz.crm.service.EmployeeService;
import com.lljz.crm.shiro.EmpRealm;
import com.lljz.crm.utils.ShiroUtils;
import com.lljz.crm.utils.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {



    @Autowired
    private EmpRoleMapper empRoleMapper;


    @Autowired
    private EmployeeMapper mapper;


    @Override
    public IPage<Employee> selectList(Page<Employee> page) {
        return mapper.selectList(page);
    }

    @Override
    public Employee selectByName(String eName) {
        return mapper.selectByName(eName);
    }



    /**
     * 添加
     * @param employee
     * @param roleId
     * @return
     */
    @Override
    public Integer add(Employee employee, Integer roleId) {
        //随机生成盐
        employee.setSalt(ShiroUtils.randomSalt());
        //加密密码
        employee.setPass(new SimpleHash("md5",employee.getPass(),employee.getSalt(),2).toHex());

        //设置时间，idDel //获取创建时间，可以提高一点性能
        employee.setCreateTime(StringUtils.getDote());
        //获取创建时间，可以提高一点性能
        employee.setUpdateTime(employee.getCreateTime());
        employee.setIsDel(0);

        int result =  mapper.insert(employee);

        //获取插入自增的id
        int empId = employee.getEmpId();
        EmpRole empRole = new EmpRole(empId,roleId);
        empRole.setCreateTime(StringUtils.getDote());
        empRole.setUpdateTime(empRole.getCreateTime());
        empRole.setIsDel(0);

        empRoleMapper.insert(empRole);

        return result;
    }

    /**
     * 删除
     * @param empId
     * @return
     */
    @Override
    public Integer del(Integer empId) {
        Employee employee = new Employee();
        employee.setEmpId(empId);
        employee.setIsDel(1);
        int i = mapper.updateById(employee);
        return i;
    }


    /**
     * 修改
     * @param employee
     * @param roleId
     * @return
     */
    @Override
    public Integer update(Employee employee, Integer roleId) {

       if(!"".equals(employee.getPass())){
           //随机生成盐
           employee.setSalt(ShiroUtils.randomSalt());
           //加密密码
           employee.setPass(new SimpleHash("md5",employee.getPass(),employee.getSalt(),2).toHex());
       }else {
           System.out.println("密码为“”，修改为null");
           employee.setPass(null);
       }

        //设置更新时间
        employee.setUpdateTime(StringUtils.getDote());

       //将信息更新到数据库中（空的属性不修改
        int result =  mapper.updateById(employee);

        EmpRole empRole = new EmpRole(employee.getEmpId(),roleId);
        empRole.setUpdateTime(StringUtils.getDote());
        AbstractWrapper wrapper = new QueryWrapper();
        wrapper.eq("emp_id",employee.getEmpId());


        int update = empRoleMapper.update(empRole, wrapper);

        //如果员工没有角色，就添加角色
        if (update == 0){
            empRole.setCreateTime(empRole.getUpdateTime());
            empRole.setIsDel(0);
            empRoleMapper.insert(empRole);
        }
        return result;
    }
}
