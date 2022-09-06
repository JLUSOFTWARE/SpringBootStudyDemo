package top.calvinhaynes.demo7_springbootstarter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.calvinhaynes.service.HelloService;

/**
 * 测试启动控制器
 *
 * @author CalvinHaynes
 * @date 2021/09/13
 */
@RestController
public class TestStarterController {

    private final HelloService helloService;

    @Autowired
    public TestStarterController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    public String starterWithHello(){
        return helloService.sayHello();
    }
}
