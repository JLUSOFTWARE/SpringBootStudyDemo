# 利用Yaml配置文件给JavaBean赋值

## 前言

Spring Boot 允许您将配置外部化，以便您可以在不同环境中使用相同的应用程序代码。您可以使用各种外部配置源，包括 Java 属性文件、YAML 文件、环境变量和命令行参数。

==属性注入的方法==：属性值可以通过直接注射到你的bean`@Value`注释，通过Spring的访问`Environment`抽象，或者被[绑定到结构化对象](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.external-config.typesafe-configuration-properties)通过`@ConfigurationProperties`。

## 本Demo测试流程

### 1 - 实体类配置

```java
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
```

```java
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
```

### 2 - yaml文件配置

```yaml
person:
  name: CalvinHaynes
  age: 3
  happy: false
  birth: 2000/01/01
  email: 1006488386@qq.com
  maps: {k1: v1, k2: v2}
  lists:
    - code
    - girl
    - music
  dog:
    name: belly
    age: 3
```

### 3 - 测试类

```java
@SpringBootTest
class Demo4InjectionUsingYamlApplicationTests {

    @Autowired
    Person person;

    @Test
    void contextLoads() {
        System.out.println(person);
    }
}
```

### 4 - 输出结果

```bash
Person(name=CalvinHaynes, age=3, email=1006488386@qq.com, happy=false, birth=Sat Jan 01 00:00:00 CST 2000, maps={k1=v1, k2=v2}, lists=[code, girl, music], dog=Dog(name=belly, age=3))
```

## JSR303校验

### 什么是JSR303校验

JSR303数据校验，这个就是我们可以在字段增加一层过滤器验证，可以保证数据的合法性

### 配置依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

### JSR303常见注解（使用）

**Bean Validation 中内置的 constraint**

![在这里插入图片描述](https://img-blog.csdnimg.cn/20210525154255398.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0lfcl9vX25fTV9hX24=,size_16,color_FFFFFF,t_70)

**Hibernate Validator 附加的 constraint**

![在这里插入图片描述](https://img-blog.csdnimg.cn/20210525154332279.png)

```java
@NotNull(message="名字不能为空")
private String userName;
@Max(value=120,message="年龄最大不能查过120")
private int age;
@Email(message="邮箱格式错误")
private String email;

空检查
@Null       验证对象是否为null
@NotNull    验证对象是否不为null, 无法查检长度为0的字符串
@NotBlank   检查约束字符串是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
@NotEmpty   检查约束元素是否为NULL或者是EMPTY.
    
Booelan检查
@AssertTrue     验证 Boolean 对象是否为 true  
@AssertFalse    验证 Boolean 对象是否为 false  
    
长度检查
@Size(min=, max=) 验证对象（Array,Collection,Map,String）长度是否在给定的范围之内  
@Length(min=, max=) string is between min and max included.

日期检查
@Past       验证 Date 和 Calendar 对象是否在当前时间之前  
@Future     验证 Date 和 Calendar 对象是否在当前时间之后  
@Pattern(value)    验证 String 对象是否符合正则表达式的规则

```

## 配置文件

### 1 - **多配置文件**

我们在主配置文件编写的时候，文件名可以是 application-{profile}.properties/yml , 用来指定多个环境版本；

#### properties配置文件

**例如：**

application-test.properties 代表测试环境配置

application-dev.properties 代表开发环境配置

但是Springboot并不会直接启动这些配置文件，它**默认使用application.properties主配置文件**；

我们需要通过一个配置来选择需要激活的环境：

```properties
#比如在配置文件中指定使用dev环境，我们可以通过设置不同的端口号进行测试；
#我们启动SpringBoot，就可以看到已经切换到dev下的配置了；
spring.profiles.active=dev
```

#### yaml配置文件

和properties配置文件中一样，但是使用yml去实现不需要创建多个配置文件，可以利用多文档块，更加方便了 !

```yml
#选择哪一个环境的配置
#这里可以在每个环境配置redis，数据库（mysql），消息（kafka）等相关的组件的配置
spring:
  profiles:
    active: dev

#文档块区分为三个---
---

server:
  port: 8083
spring:
  profiles: dev #配置环境的名称

#文档块区分为三个---
---

server:
  port: 8084
spring:
  profiles: prod  #配置环境的名称

#文档块区分为三个---
---

server:
  port: 8085
spring:
  profiles: test  #配置环境的名称
```

**注意：如果yml和properties同时都配置了端口，并且没有激活其他环境 ， 默认会使用properties配置文件的！**

==上例从启动SpringBoot项目时Console的输出就可以看出：（配置dev环境的端口就是8083）==

```bash
2021-09-12 18:45:19.568  INFO 7296 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer :Tomcat started on port(s): 8083 (http) with context path ''
```

==YAML多文档块多环境配置非常简单，切换环境只需要修改==

```yaml
spring:
  profiles:
    active: prod
```

中active对应的环境的值即可。

### 2 - 配置文件加载位置

**外部加载配置文件的方式十分多，我们选择最常用的即可，在开发的资源文件中进行配置！**

官方外部配置文件说明参考文档

![img](https://img2020.cnblogs.com/blog/1905053/202004/1905053-20200412221627864-281289739.png)

springboot 启动会扫描以下位置的application.properties或者application.yml文件作为Spring boot的默认配置文件：

- 优先级1：项目根目录下的config文件夹配置文件（测试：设置为==8088==端口）
- 优先级2：项目根目录下配置文件（测试：设置为==8087==端口）
- 优先级3：资源路径下的config文件夹配置文件（测试：设置为==8086==端口）
- 优先级4：资源路径下配置文件（测试：设置为==8085==端口）

优先级由高到底，高优先级的配置会覆盖低优先级的配置；

==**SpringBoot会从这四个位置全部加载主配置文件，也就是说如果所有的文件配置了一样的信息，会进行覆盖，但是加载的时候会进行互补配置，低优先级；**==

我们在最低级的配置文件中设置一个项目访问路径的配置来测试互补配置

```properties
server:
  port: 8085
  servlet:
    #配置项目的访问路径
    context-path: /calvinhaynes
```

**控制台输出**

```bash
2021-09-12 19:04:59.741  INFO 2084 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8088 (http) with context path '/calvinhaynes'
```

## 参考资料

https://blog.csdn.net/I_r_o_n_M_a_n/article/details/117257278

https://segmentfault.com/a/1190000015482585

https://www.cnblogs.com/zhuchengbo/p/12688161.html#4
