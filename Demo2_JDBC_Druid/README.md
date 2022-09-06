# Demo2_JDBC_Druid

## 前言

本Demo中测试JDBC的整合以及Druid数据源的引入

## 整合JDBC

SpringBoot整合JDBC流程如下：

![SpringBoot整合JDBC](https://cdn.jsdelivr.net/gh/CalvinHaynes/ImageHub@main/BlogImage/SpringBoot整合JDBC.6lt9ijcfz4s0.png)

### **依赖设置：**

```xml
<!-- spring jdbc 操作模版 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>

<!-- springboot web开发 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- mysql 数据库连接 -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
```

## Druid数据源的引入

### 依赖设置

```xml
<!-- 引入druid数据源 -->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.2.6</version>
</dependency>

<!--Druid 的 Spring starter-->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid-spring-boot-starter</artifactId>
    <version>1.2.6</version>
</dependency>
```

### 设置一些常用配置

```yaml
spring:
  datasource:   # 外部注入datasource参数
    # 数据源基本配置
    username: root
    password: 123456
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/spring_boot_test?useUnicode=true&characterEncoding=utf8&useSSL=true
    type: com.alibaba.druid.pool.DruidDataSource

    #druid 数据源专有配置
    initialSize: 5
    minIdle: 5
    maxActive: 20
    maxWait: 60000
    timeBetweenEvictionRunsMillis: 60000
    minEvictableIdleTimeMillis: 300000
    validationQuery: SELECT 1 FROM DUAL
    testWhileIdle: true
    testOnBorrow: false
    testOnReturn: false
    poolPreparedStatements: true

    #配置监控统计拦截的filters，stat:监控统计、log4j：日志记录、wall：防御sql注入
    #如果允许时报错  java.lang.ClassNotFoundException: org.apache.log4j.Priority
    #则导入 log4j 依赖即可，Maven 地址：https://mvnrepository.com/artifact/log4j/log4j
    filters: stat,wall,log4j
    maxPoolPreparedStatementPerConnectionSize: 20
    useGlobalDataSourceStat: true
    connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500
```

**log4j依赖**：

```xml
<!--引入log4j-->
<dependency>
    <groupId>log4j</groupId>
    <artifactId>log4j</artifactId>
    <version>1.2.17</version>
</dependency>
```

### 配置DruidConfig配置类

一些通用的Druid的优秀配置如下：

- Druid的后台管理：通过登入 http://localhost:8080/druid/ 就可以进入Druid集成的后台管理界面进行后台任务执行的监测
- 配置Druid监控的Filter过滤器：配置Web和Druid数据源之间的管理关联监控统计，决定过滤掉哪些请求等等

```java
/**
 * Druid的配置
 *
 * @author CalvinHaynes
 * @date 2021 /09/06
 */
@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    /**
     * 配置 Druid 监控管理后台的Servlet；
     * 内置 Servlet 容器时没有web.xml文件，
     * 所以使用 Spring Boot 的注册 Servlet 方式.
     *
     * @return the servlet registration bean
     */
    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        // 这些参数可以在 com.alibaba.druid.support.http.StatViewServlet
        // 的父类 com.alibaba.druid.support.http.ResourceServlet 中找到
        Map<String, String> initParams = new HashMap<>();

        //参数设置
        //后台管理界面的登录账号
        initParams.put("loginUsername", "CalvinHaynes");
        //后台管理界面的登录密码
        initParams.put("loginPassword", "999999");

        //设置后台允许谁来访问，设置allow属性，
        //属性值为localhost时表示只有本机可以访问
        //为空或者为null时，表示允许所有访问
        initParams.put("allow", "");

        //deny：Druid 后台拒绝谁访问
        initParams.put("deny", "192.168.1.20");

        //设置初始化参数
        bean.setInitParameters(initParams);
        return bean;
    }


    /**
     * Web stat filter filter registration bean.
     * <p>
     * 配置 Druid 监控 之  web 监控的 filter
     * <p>
     * WebStatFilter：用于配置Web和Druid数据源之间的管理关联监控统计
     *
     * @return the filter registration bean
     */
    @Bean
    public FilterRegistrationBean<WebStatFilter> webStatFilter() {
        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());

        //exclusions：设置哪些请求进行过滤排除掉，从而不进行统计
        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions", "*.js,*.css,/druid/*,/jdbc/*");
        bean.setInitParameters(initParams);

        //"/*" 表示过滤所有请求
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
```

