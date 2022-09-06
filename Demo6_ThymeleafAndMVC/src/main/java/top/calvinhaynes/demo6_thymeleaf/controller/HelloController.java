package top.calvinhaynes.demo6_thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Arrays;

/**
 * 你好控制器
 *
 * @author CalvinHaynes
 * @date 2021/09/13
 */
@Controller
public class HelloController {

    @GetMapping("/hello")
    public String test(Model model) {
        model.addAttribute("users", Arrays.asList("Jack","Mary","Linus"));
        model.addAttribute("msg","<h2>HelloSpringBoot！</h2>");
        return "hello";
    }
}
