package top.calvinhaynes.demo4_injectionusingyaml.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 人
 * Component注解：将此类加入组件，从而Spring框架可以扫描到
 * Validated：开启JSR303 数据校验支持
 * ConfigurationProperties： 绑定外部属性配置的注解
 *
 * @author CalvinHaynes
 * @date 2021/09/12
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "person")
@Validated
public class Person {
    private String name;
    private Integer age;
    @Email(message = "邮件格式错误")
    private String email;
    private Boolean happy;
    private Date birth;
    private Map<String,Object> maps;
    private List<Object> lists;
    private Dog dog;

}
