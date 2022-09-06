# 静态资源映射规则

## 前言

关于WebMVC的自动配置类WebMvcAutoConfiguration中存在着静态资源映射的配置

其中WebMvcAutoConfigurationAdapter静态类WebMVC自动配置适配器中有这样一个方法addResourceHandlers，此方法配置了静态资源处理器

```java
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
   if (!this.resourceProperties.isAddMappings()) {
       //如果外部自定义静态资源处理配置，则默认配置失效
      logger.debug("Default resource handling disabled");
      return;
   }
    //针对webjars静态资源的路径映射，项目URL/webjars/**自动对应了classpath:/META-INF/resources/webjars/目录
   addResourceHandler(registry, "/webjars/**", "classpath:/META-INF/resources/webjars/");
    
    //设置自己项目中自定义静态资源的路径
    //this.resourceProperties.getStaticLocations()
   addResourceHandler(registry, this.mvcProperties.getStaticPathPattern(), (registration) -> {
      registration.addResourceLocations(this.resourceProperties.getStaticLocations());
      if (this.servletContext != null) {
         ServletContextResource resource = new ServletContextResource(this.servletContext, SERVLET_LOCATION);
         registration.addResourceLocations(resource);
      }
   });
}
```

## webjars静态资源

**例如本Demo中引入font-awesome的webjars静态资源**

```xml
<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>font-awesome</artifactId>
    <version>5.15.4</version>
</dependency>
```

**访问的时候要根据webjar包的路径进行访问**（http://localhost:8080/webjars/font-awesome/5.15.4/svgs/regular/address-book.svg）

![image](https://cdn.jsdelivr.net/gh/CalvinHaynes/ImageHub@main/BlogImage/image.1trz62af6vq8.png)

## 自己项目中自定义静态资源

==**this.resourceProperties.getStaticLocations()**==

```java
public String[] getStaticLocations() {
   return this.staticLocations;
}
```

```java
private String[] staticLocations = CLASSPATH_RESOURCE_LOCATIONS;
```

```java
private static final String[] CLASSPATH_RESOURCE_LOCATIONS = { "classpath:/META-INF/resources/",
      "classpath:/resources/", "classpath:/static/", "classpath:/public/" };
```

从上面可以看出以下四个目录存放的静态资源（==**优先级从高到低**==）可以被我们识别：（classpath就是指项目src中resources目录）

```java
"classpath:/META-INF/resources/" 
"classpath:/resources/" 
"classpath:/static/" 
"classpath:/public/"
```

**我们可以在resources根目录下新建对应的文件夹，都可以存放我们的静态文件；**