# Spring Boot Starter

## 前言

Starter是Spring Boot中的一个非常重要的概念，Starter相当于模块，它能将模块所需的依赖整合起来并对模块内的Bean根据环境（ 条件）进行自动配置。使用者只需要依赖相应功能的Starter，无需做过多的配置和依赖，Spring Boot就能自动扫描并加载相应的模块。

- 它整合了这个模块需要的依赖库；
- 提供对模块的配置项给使用者；
- 提供自动配置类对模块内的Bean进行自动装配；

例如，在Maven的依赖中加入spring-boot-starter-web就能使项目支持Spring MVC，并且Spring Boot还为我们做了很多默认配置，无需再依赖spring-web、spring-webmvc等相关包及做相关配置就能够立即使用起来。

## 编写一个Starter

### 1 - 新建Maven工程（pom.xml）

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>
    <groupId>top.calvinhaynes</groupId>
    <artifactId>study-client-starter</artifactId>
    <version>1.0-SNAPSHOT</version>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-autoconfigure</artifactId>
            <version>2.5.4</version>
        </dependency>
    </dependencies>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>2.5.4</version>
                <scope>import</scope>
                <type>pom</type>
            </dependency>
        </dependencies>
    </dependencyManagement>
    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

</project>
```

### 2 - 写一个简单的业务

**HelloService.java**

```java
package top.calvinhaynes.service;

import org.springframework.stereotype.Component;

/**
 * 你好,服务
 *
 * @author CalvinHaynes
 * @date 2021/09/13
 */
@Component
public interface HelloService {
    /**
     * 说“你好”
     *
     * @return {@link String}
     */
    public String sayHello();
}
```

**HelloServiceImpl.java**

```java
package top.calvinhaynes.service;

import org.springframework.stereotype.Component;

/**
 * 你好服务impl
 *
 * @author CalvinHaynes
 * @date 2021/09/13
 */
@Component
public class HelloServiceImpl implements HelloService{
    /**
     * 说“你好”
     *
     * @return {@link String}
     */
    @Override
    public String sayHello() {
        return "Hello My First SpringBoot Starter!";
    }
}
```

### 3 - 自动配置类的编写

**SpringBoot的自动配置机制会扫描到这个Configuration**

```java
package top.calvinhaynes.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 自动配置类
 *
 * @author CalvinHaynes
 * @date 2021/09/13
 */
@Configuration
@ComponentScan({"top.calvinhaynes.service"})
public class HelloServiceAutoConfiguration {

}
```

### 4 - 编写spring.factories

通过编写spring.factories让自动配置扫描其中的配置并注入IOC容器

![image](https://cdn.jsdelivr.net/gh/CalvinHaynes/ImageHub@main/BlogImage/image.5yah24ouy8c0.png)

```properties
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
  top.calvinhaynes.config.HelloServiceAutoConfiguration
```

**可以看出我们刚才写的Starter对于使用者来说非常方便，除了在POM中引入依赖，不需要其他操作就可以直接使用模块内部的接口注入**

