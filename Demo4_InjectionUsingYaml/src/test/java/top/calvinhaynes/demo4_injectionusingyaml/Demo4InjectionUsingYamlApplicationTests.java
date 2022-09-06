package top.calvinhaynes.demo4_injectionusingyaml;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.calvinhaynes.demo4_injectionusingyaml.pojo.God;
import top.calvinhaynes.demo4_injectionusingyaml.pojo.Person;

@SpringBootTest
class Demo4InjectionUsingYamlApplicationTests {

    @Autowired
    Person person;
    @Autowired
    God god;

    @Test
    void contextLoads() {
        System.out.println(person);
        System.out.println(god);
    }

}
