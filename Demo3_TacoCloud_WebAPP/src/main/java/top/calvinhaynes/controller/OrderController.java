package top.calvinhaynes.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.calvinhaynes.entity.Order;

import javax.validation.Valid;

/**
 * @ProjectName: OrderController
 * @Author: CalvinHaynes
 * @Date: 2021/7/29 20:05
 * @Description: Taco订单的Controller
 */
@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {


    /**
     * get请求显示订单表单视图页面
     *
     * @param model 模型
     * @return {@link String}
     */
    @GetMapping("/current")
    public String orderForm(Model model) {
        model.addAttribute("order", new Order());
        return "orderForm";
    }


    /**
     * 填完订单之后的操作
     *
     * @param order  订单
     * @param errors 错误
     * @return {@link String}
     */
    @PostMapping
    public String processOrder(@Valid Order order, Errors errors) { //@Valid注解是检验用的
        if (errors.hasErrors()) {
            return "orderForm";
        }

        log.info("Order submitted: " + order);
        return "redirect:/";
    }
}
