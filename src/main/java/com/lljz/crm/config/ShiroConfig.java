package com.lljz.crm.config;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.lljz.crm.shiro.EmpRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;


@Configuration
public class ShiroConfig {

    @Bean
    public DefaultWebSecurityManager SecurityManager(EmpRealm empRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(empRealm);

        return securityManager;
    }

    @Bean
    public EmpRealm empRealm(HashedCredentialsMatcher hashedCredentialsMatcher){
        EmpRealm myRealm = new EmpRealm();
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return myRealm;
    }


    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(2);
        return matcher;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        //实例化一个过滤器bean，设置他的安全管理器
        ShiroFilterFactoryBean filterBean = new ShiroFilterFactoryBean();
        filterBean.setSecurityManager(securityManager);

        //**过滤链定义 从上向下顺序执行，一般将/**放在最为下边
        Map<String,String> map = new LinkedHashMap<String ,String>();
        map.put("/doLogin","anon");

        map.put("/static/**","anon");
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        map.put("/logout" , "logout");

        //给url设置权限
        map.put("/emp","perms[emp:list]");


        //所有url都必须认证通过才可以访问
        map.put("/**","authc");
        //**过滤链定义结束

        //设置登录的url，成功之后的url，没权限url
        filterBean.setLoginUrl("/login.html");  //如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        filterBean.setSuccessUrl("/");
        filterBean.setUnauthorizedUrl("/403");  //401、403 没权限的状态码

        //将定义过滤器mapper放到过滤器对象中，由安全管理器来管理
        filterBean.setFilterChainDefinitionMap(map);

        return filterBean;
    }


    /**
     * Shiro JSP标签库
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }

}
