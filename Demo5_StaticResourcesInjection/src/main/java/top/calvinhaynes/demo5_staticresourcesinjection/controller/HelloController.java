package top.calvinhaynes.demo5_staticresourcesinjection.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 你好控制器
 *
 * @author CalvinHaynes
 * @date 2021/09/12
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello SpringBoot!";
    }
}
