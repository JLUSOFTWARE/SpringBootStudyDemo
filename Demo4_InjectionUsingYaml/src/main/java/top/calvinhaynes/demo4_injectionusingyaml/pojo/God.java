package top.calvinhaynes.demo4_injectionusingyaml.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 神
 *
 * @author CalvinHaynes
 * @date 2021/09/12
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "god")
public class God {
    private String name;
    private Person person;
}
