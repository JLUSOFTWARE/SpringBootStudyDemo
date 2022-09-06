package top.calvinhaynes.demo9_springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 路由控制器
 *
 * @author CalvinHaynes
 * @date 2021/09/17
 */
@Controller
public class RouterController {

    @RequestMapping({"/","/index"})
    public String index() {
        return "index";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "views/login";
    }

    @RequestMapping("/level1/{id}")
    public String level1(@PathVariable("id") int id) {
        return String.format("views/level1/%d", id);
    }

    @RequestMapping("/level2/{id}")
    public String level2(@PathVariable("id") int id) {
        return String.format("views/level2/%d", id);
    }

    @RequestMapping("/level3/{id}")
    public String level3(@PathVariable("id") int id) {
        return String.format("views/level3/%d", id);
    }
}
