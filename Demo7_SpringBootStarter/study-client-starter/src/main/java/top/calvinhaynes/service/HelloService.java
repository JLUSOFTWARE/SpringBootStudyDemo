package top.calvinhaynes.service;

import org.springframework.stereotype.Component;

/**
 * 你好,服务
 *
 * @author CalvinHaynes
 * @date 2021/09/13
 */
@Component
public interface HelloService {
    /**
     * 说“你好”
     *
     * @return {@link String}
     */
    public String sayHello();
}
