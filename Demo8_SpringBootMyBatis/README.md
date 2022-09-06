# SpringBoot和MyBatis的整合

## 前言

SpringBoot整合MyBatis思路图如下：

![中心主题](https://cdn.jsdelivr.net/gh/CalvinHaynes/ImageHub@main/BlogImage/中心主题.1xd9d563w5ls.png)

## 1 - 添加依赖

```xml
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- 引入 myBatis，这是 MyBatis官方提供的适配 Spring Boot 的，而不是Spring Boot自己的-->
<dependency>
   <groupId>org.mybatis.spring.boot</groupId>
   <artifactId>mybatis-spring-boot-starter</artifactId>
   <version>2.2.0</version>
</dependency>

<dependency>
   <groupId>mysql</groupId>
   <artifactId>mysql-connector-java</artifactId>
   <scope>runtime</scope>
</dependency>

<!--lombok看需求添加-->
<dependency>
   <groupId>org.projectlombok</groupId>
   <artifactId>lombok</artifactId>
   <optional>true</optional>
</dependency>
```

## 2 - application.yaml常用配置

```yaml
spring:
  datasource:
    username: root
    password: 123456
    url: jdbc:mysql://localhost:3306/ssmintegration?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC
    driver-class-name: com.mysql.cj.jdbc.Driver

mybatis:
  #指定myBatis的核心配置文件与Mapper映射文件
  mapper-locations: classpath:mapper/*.xml
  # 注意：对应实体类的路径
  type-aliases-package: top.calvinhaynes.demo8_springbootmybatis.pojo
  configuration:
    # 数据库下划线字段名自动映射到实体类的驼峰命名
    map-underscore-to-camel-case: true
```

## 3 - 测试类测试

```java
package top.calvinhaynes.demo8_springbootmybatis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class Demo8SpringBootMyBatisApplicationTests {

   @Autowired
   DataSource dataSource;

   @Test
   void contextLoads() throws SQLException {
      System.out.println("数据源>>>>>>" + dataSource.getClass());
      Connection connection = dataSource.getConnection();
      System.out.println("连接>>>>>>>>>" + connection);
      System.out.println("连接地址>>>>>" + connection.getMetaData().getURL());
      connection.close();
   }

}
```

## 4 - 其余部分根据Demo自行查看

## 其他资料

MyBatis-Spring-Starter的官方文档：http://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/

