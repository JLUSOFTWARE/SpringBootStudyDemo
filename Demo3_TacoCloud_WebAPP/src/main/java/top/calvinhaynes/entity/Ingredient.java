package top.calvinhaynes.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * The type Ingredient.
 *
 * @ProjectName: Ingredient
 * @Author: CalvinHaynes
 * @Date: 2021 /7/30 9:26
 * @Description: 配料实体类
 */
@Data
@RequiredArgsConstructor
public class Ingredient {

    private final String id;
    private final String name;
    private final Type type;

    /**
     * The enum Type.
     */
    public static enum Type {
        /**
         * Wrap type.
         */
        WRAP,
        /**
         * Protein type.
         */
        PROTEIN,
        /**
         * Veggies type.
         */
        VEGGIES,
        /**
         * Cheese type.
         */
        CHEESE,
        /**
         * Sauce type.
         */
        SAUCE
    }
}
