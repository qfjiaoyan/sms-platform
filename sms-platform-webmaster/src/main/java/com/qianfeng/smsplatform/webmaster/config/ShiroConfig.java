package com.qianfeng.smsplatform.webmaster.config;

import com.qianfeng.smsplatform.webmaster.shiro.UserRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //1,创建 SessionManager 管理会话
    @Bean(name = "sessionManager")//<bean class="">
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //设置过期时间
        sessionManager.setGlobalSessionTimeout(1000 * 60 * 30);
        //设置后台线程  清理过期的会话
        sessionManager.setSessionValidationSchedulerEnabled(true);
        //设置地址比拼接sessionid
        sessionManager.setSessionIdUrlRewritingEnabled(false);

        return sessionManager;
    }

    //2,创建SecurityManager
    @Bean(name = "securityManager")
    public SecurityManager securityManager(SessionManager sessionManager, UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager);
        //cookie管理
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        Cookie cookie = cookieRememberMeManager.getCookie();
        cookie.setMaxAge(60 * 60 * 24 * 3);
        cookie.setPath("/");
        securityManager.setRememberMeManager(cookieRememberMeManager);
        //设置自定义realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //3,创建ShiroFilter
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //设置登录页面
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        shiroFilterFactoryBean.setSuccessUrl("/index.html");
        shiroFilterFactoryBean.setUnauthorizedUrl("unauthorized.html");
        //拦截的路径的详细设置
        //什么Map是存取有序的？
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/sys/login", "anon");//匿名访问
        map.put("/captcha.jpg", "anon");//验证码放行
        map.put("/public/**", "anon");
        map.put("/json/**", "anon");
        map.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    //4,BeanLifeCycle  生命周期
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        LifecycleBeanPostProcessor lifecycleBeanPostProcessor = new LifecycleBeanPostProcessor();
        return lifecycleBeanPostProcessor;
    }

    //5,开启shiro的注解
    @Bean(name = "defaultAdvisorAutoProxyCreator")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);//cglib方式
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean(name = "authorizationAttributeSourceAdvisor")
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

}
