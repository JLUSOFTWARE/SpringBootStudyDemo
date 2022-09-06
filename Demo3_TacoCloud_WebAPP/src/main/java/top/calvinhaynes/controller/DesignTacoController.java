package top.calvinhaynes.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.calvinhaynes.entity.Ingredient;
import top.calvinhaynes.entity.Taco;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ProjectName: DesignTacoController
 * @Author: CalvinHaynes
 * @Date: 2021/7/29 20:05
 * @Description: 设计Taco的Controller
 */
@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {


    /**
     * 添加成分模型
     * <p>
     * 在所有的请求处理方法执行之前，往Model里面加数据
     *
     * @param model 模型
     */
    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        //配料表
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
        );

        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            //以key-value（Type，List<Ingredient>）填充Model
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }


    /**
     * 展示设计形式
     * Method Description: 显示设计Taco的表单页面
     * <p>
     * Spring Web MVC 控制器中的所有处理方法必须解析为一个逻辑视图名，
     * 显式地（比如返回一个字符串或者 View）或者隐式地（比如基于约定）。
     * Spring 中的视图通过一个逻辑视图名来定位，被一个视图解析器解析。
     * 视图模板会通过get请求获取到的Model的key值（逻辑视图名）定位
     *
     * @param model 模型
     * @return {@link String}
     */
    @GetMapping
    public String showDesignForm(Model model) {
        model.addAttribute("design", new Taco());
        return "design";
    }


    /**
     * 设计完Taco后的处理
     *
     * @param design 设计
     * @param errors 错误
     * @return {@link String}
     */
    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors) {

        //错误处理：直接重新返回design视图
        if (errors.hasErrors()) {
            return "design";
        }

        //提交后的操作，涉及到持久化数据，所以会在下一个Demo中细写
        //现在先简单用一个log打印替代
        log.info("processing your design:" + design);

        //重定向到订单页面
        return "redirect:/orders/current";
    }


    /**
     * 通过配料类型type筛选ingredients列表
     *
     * @param ingredients 成分
     * @param type        类型
     * @return {@link List}<{@link Ingredient}>
     */
    private List<Ingredient> filterByType(
            List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }


}
