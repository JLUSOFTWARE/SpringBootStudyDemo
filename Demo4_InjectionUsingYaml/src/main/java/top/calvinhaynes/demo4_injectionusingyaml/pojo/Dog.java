package top.calvinhaynes.demo4_injectionusingyaml.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 狗
 * Component注解：将此类加入组件，从而Spring框架可以扫描到
 * ConfigurationProperties： 绑定外部属性配置的注解
 *
 * @author CalvinHaynes
 * @date 2021/09/12
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "dog")
public class Dog {
    private String name;
    private int age;
}
