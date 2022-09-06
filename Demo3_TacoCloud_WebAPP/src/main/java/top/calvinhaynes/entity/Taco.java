package top.calvinhaynes.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 墨西哥煎玉米卷
 * Taco实体类
 *
 * @author CalvinHaynes
 * @date 2021/09/16
 */
@Data
public class Taco {

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;


    @Size(min = 1, message = "You must choose at least 1 ingredient")
    private List<String> ingredients;
}
