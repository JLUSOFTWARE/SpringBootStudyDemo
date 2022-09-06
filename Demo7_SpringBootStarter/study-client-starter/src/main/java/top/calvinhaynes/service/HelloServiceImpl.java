package top.calvinhaynes.service;

import org.springframework.stereotype.Component;

/**
 * 你好服务impl
 *
 * @author CalvinHaynes
 * @date 2021/09/13
 */
@Component
public class HelloServiceImpl implements HelloService{
    /**
     * 说“你好”
     *
     * @return {@link String}
     */
    @Override
    public String sayHello() {
        return "Hello My First SpringBoot Starter!";
    }
}
