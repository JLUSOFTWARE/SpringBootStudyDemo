# SpringBoot自定义配置

## 前言

SpringBoot的自动配置是SpringBoot框架的巨大优势，它将原本Spring框架需要写一大堆复杂的配置文件的流程省去了，开发者只需要写业务具体的配置即可快速搭建应用，但是有时候我们也想自定义一些配置（修改SpringBoot自动配置的默认配置）或者我们找不到有哪些配置是我们可以自定义的，本文会探索一种思路，一种快速定位可以自定义配置的属性的思路。

## 在application.yaml中修改配置

### 1 - 所有可以配置的属性名哪里去找?

- **方法一**：官网整理了所有可以更改的属性表，如下：

https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#application-properties.core

- **方法二**：application.yaml能修改的配置都是一个xxxxProperties的类中的属性，一般这种类都会在xxxxAutoConfiguration的类中通过@EnableConfigurationProperties(xxxxProperties.class)的方式引入，所以可以通过这种思路查找到对应的属性，并且有一些注释可供参考
- **方法三**：Spring Boot 的jar 包（**位于 `spring-boot-autoconfigure-2.5.4.jar `下的 `META-INF/spring-configuration-metadata.json`**）含元数据文件，**提供所有支持的配置属性的详细信息**。（元数据）

### 2 - 可以自定义配置类xxxxProperties

您可以使用注解`@ConfigurationProperties`，并且使用`spring-boot-configuration-processor`的jar包，从注释的项目中轻松生成自己的配置元数据文件。该 jar 包括一个 Java 注释处理器，它在您的项目编译时被调用。

#### 什么是元数据？

Spring Boot 的jar 包（**位于 `spring-boot-autoconfigure-2.5.4.jar `下的 `META-INF/spring-configuration-metadata.json`**）含元数据文件，**提供所有支持的配置属性的详细信息**。该文件旨在让IDE开发者提供上下文相关的帮助和“代码补全”，因为用户正在使用`application.properties`或`application.yml`文件。

大部分元数据文件是在编译时通过处理所有带注释的项目自动生成的`@ConfigurationProperties`。但是，可以为极端情况或更高级的用例[手动编写部分元数据](https://docs.spring.io/spring-boot/docs/current/reference/html/configuration-metadata.html#configuration-metadata.annotation-processor.adding-additional-metadata)。

#### **配置注解处理器**

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
    <optional>true</optional>
</dependency>
```

#### Properties类编写（自动元数据生成）

- 处理器选取用 注释的类和方法`@ConfigurationProperties`。

- 如果该类也用 注释`@ConstructorBinding`，则需要一个构造函数，并且为每个构造函数参数创建一个属性。
- 否则，通过对集合和映射类型进行特殊处理的标准 getter 和 setter 的存在来发现属性（即使仅存在 getter 也会检测到）。

- 注解处理器还支持使用的`@Data`，`@Getter`和`@Setter`Lombok的注释。

考虑以下示例：

```java
@ConfigurationProperties(prefix = "my.server")
public class MyServerProperties {

    /**
     * Name of the server.
     */
    private String name;

    /**
     * IP address to listen to.
     */
    private String ip = "127.0.0.1";

    /**
     * Port to listener to.
     */
    private int port = 9797;

    // getters/setters ...
```

这公开了三个`my.server.name`没有默认值`my.server.ip`和`my.server.port`默认值的属性，分别为`"127.0.0.1"`和`9797`。字段上的 Javadoc 用于填充`description`属性。例如，描述`my.server.ip`为“要侦听的IP地址。”。

## 在JavaConfig配置类内自定义配置

### 1 - SpringMVC自定义配置

