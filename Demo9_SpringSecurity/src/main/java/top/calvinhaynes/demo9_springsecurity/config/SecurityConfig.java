package top.calvinhaynes.demo9_springsecurity.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * AOP:拦截器
 *
 * /@EnableWebSecurity注解 :开启WebSecurity模式
 *
 * @author CalvinHaynes
 * @date 2021/09/17
 */

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 授权的配置：Authorization
     *
     * 链式编程
     *
     * @param http http
     * @throws Exception 异常
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 请求授权的规则：首页所有人都可以访问，功能也只有对应有权限的人才能访问到
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");

        // 没有权限默认会跳转到登录页面，可以设置为自己写的login界面
        http.formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginPage("/toLogin")
                .loginProcessingUrl("/login");

        // 注销,开启了注销功能（前端页面有对应的按钮）,跳到首页
        http.logout().logoutSuccessUrl("/");

        // 防止网站工具：get，post
        http.csrf().disable();//关闭csrf功能，登录失败肯定存在的原因

        //开启记住我功能: cookie,默认保存两周,自定义接收前端的参数，
        //在验证名为“remember-me”的 HTTP 参数是否存在时，
        //即使在javax.servlet.http.HttpSession过期后，用户也会被记住。
        http.rememberMe().rememberMeParameter("remember");


    }

    /**
     * 认证的配置：Certification
     *
     * 链式编程
     *
     * 在spring Secutiry 5.0+ 新增了很多加密方法，所以需要进行加密解密的设置身份验证才会生效，也是为了安全考虑，因为如果不加密的haul反编译可以盗取密码
     *      PasswordEncoder接口：用于编码密码的服务接口。首选实现类是BCryptPasswordEncoder 。
     *
     *
     * @param auth 身份验证
     * @throws Exception 异常
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //这些数据正常应该中数据库中读，现在使用内存替代（速度会更快）
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("calvinhaynes").password(new BCryptPasswordEncoder().encode("123456")).roles("vip2","vip3")
                .and()
                .withUser("root").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1","vip2","vip3")
                .and()
                .withUser("guest").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1");

        //JDBC 读数据库信息进行身份验证的方法
        //auth.jdbcAuthentication();

    }


}
