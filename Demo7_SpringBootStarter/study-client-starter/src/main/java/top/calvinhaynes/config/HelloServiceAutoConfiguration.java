package top.calvinhaynes.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 你好,服务汽车配置
 *
 * @author CalvinHaynes
 * @date 2021/09/13
 */
@Configuration
@ComponentScan({"top.calvinhaynes.service"})
public class HelloServiceAutoConfiguration {

}
