package top.calvinhaynes.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ProjectName: WebConfig
 * @Author: CalvinHaynes
 * @Date: 2021/7/29 20:12
 * @Description: 声明视图控制器（只将请求转发到视图上而不做其他事情的控制器）
 * 替代HomeController,实现get请求并转发视图
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //注册视图控制器，addviewController方法会对其中的参数urlPathorPattern："/"执行Get请求，
        //并返回一个ViewControllerRegistry对象
        //setViewName方法指明当请求"/"时转发到home视图上
        registry.addViewController("/").setViewName("home");
    }
}
