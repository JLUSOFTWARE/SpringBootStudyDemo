# Thymeleaf和SpringBoot中的MVC

## 前言

Thymeleaf 是适用于 Web 和独立环境的现代服务器端 Java 模板引擎，能够处理 HTML、XML、JavaScript、CSS 甚至纯文本。

Thymeleaf 的主要目标是提供一种优雅且高度可维护的模板创建方式。为了实现这一点，它建立在*自然模板*的概念之上，以不影响模板用作设计原型的方式将其逻辑注入模板文件。这改善了设计的沟通并弥合了设计和开发团队之间的差距。（**意思就是在不影响原本HTML文件语法的基础上增添了个插件**）

------

SpringBoot中的MVC

The [Spring Web MVC framework](https://docs.spring.io/spring/docs/5.2.4.RELEASE/spring-framework-reference/web.html#mvc) (often referred to as simply “Spring MVC”) is a rich “model view controller” web framework. Spring MVC lets you create special `@Controller` or `@RestController` beans to handle incoming HTTP requests. Methods in your controller are mapped to HTTP by using `@RequestMapping` annotations.

## Thymeleaf

### 1 - 标准表达式语法

- 简单的表达：
  - 变量表达式： `${...}`
  - 选择变量表达式： `*{...}`
  - 消息表达： `#{...}`
  - 链接 URL 表达式： `@{...}`
  - 片段表达式： `~{...}`
- 文字
  - 文本字面量：`'one text'`, `'Another one!'`,...
  - 数字字面量：`0`, `34`, `3.0`, `12.3`,...
  - 布尔文字：`true`,`false`
  - 空字面量： `null`
  - 文字标记：`one`, `sometext`, `main`,...
- 文字操作：
  - 字符串连接： `+`
  - 字面替换： `|The name is ${name}|`
- 算术运算：
  - 二元运算符：`+`, `-`, `*`, `/`,`%`
  - 减号（一元运算符）： `-`
- 布尔运算：
  - 二元运算符：`and`,`or`
  - 布尔否定（一元运算符）：`!`,`not`
- 比较与相等：
  - 比较器：`>`, `<`, `>=`, `<=`( `gt`, `lt`, `ge`, `le`)
  - 等式运算符：`==`, `!=`( `eq`, `ne`)
- 条件运算符：
  - 如果-那么： `(if) ? (then)`
  - 如果-然后-其他： `(if) ? (then) : (else)`
  - 默认： `(value) ?: (defaultvalue)`
- 特殊代币：
  - 无操作： `_`

所有这些功能都可以组合和嵌套：

```html
'User is of type ' + (${user.isAdmin()} ? 'Administrator' : (${user.type} ?: 'Unknown'))
```

### 2 - 属性优先级

当您`th:*`在同一个标签中编写多个属性时会发生什么？例如：

```html
<ul>
  <li th:each="item : ${items}" th:text="${item.description}">Item description here...</li>
</ul>
```

我们希望该`th:each`属性在 之前执行，`th:text`以便我们得到我们想要的结果，但鉴于 HTML/XML 标准没有赋予标记中属性的写入顺序任何种类的意义，因此*优先级*必须在属性本身中建立机制，以确保这将按预期工作。

因此，所有 Thymeleaf 属性都定义了一个数字优先级，它确定了它们在标签中的执行顺序。这个顺序是：

| 命令 | 特征               | 属性                                       |
| :--- | :----------------- | :----------------------------------------- |
| 1    | 片段包含           | `th:insert` `th:replace`                   |
| 2    | 片段迭代           | `th:each`                                  |
| 3    | 条件评估           | `th:if` `th:unless` `th:switch` `th:case`  |
| 4    | 局部变量定义       | `th:object` `th:with`                      |
| 5    | 通用属性修改       | `th:attr` `th:attrprepend` `th:attrappend` |
| 6    | 具体属性修改       | `th:value` `th:href` `th:src` `...`        |
| 7    | 文本（标签体修改） | `th:text` `th:utext`                       |
| 8    | 片段规格           | `th:fragment`                              |
| 9    | 去除碎片           | `th:remove`                                |

这种优先机制意味着如果属性位置被反转，上面的迭代片段将给出完全相同的结果（尽管它的可读性会稍微降低）：

```html
<ul>
  <li th:text="${item.description}" th:each="item : ${items}">Item description here...</li>
</ul>
```

### 3 - 文本转义问题

**未转义的文本**：

​	我们主页的最简单版本现在似乎已经准备就绪，但还有一些我们没有想到的……如果我们有这样的消息怎么办？

```java
home.welcome=Welcome to our <b>fantastic</b> grocery store!
```

如果我们像以前一样执行这个模板，我们将获得：

```html
<p>Welcome to our &lt;b&gt;fantastic&lt;/b&gt; grocery store!</p>
```

这不是我们所期望的，因为我们的`<b>`标签已被转义，因此它将显示在浏览器中。

这是`th:text`属性的默认行为。如果我们希望 Thymeleaf 尊重我们的 HTML 标签而不是转义它们，我们将不得不使用不同的属性：（`th:utext`对于“未转义的文本”）：

```html
<p th:utext="#{home.welcome}">Welcome to our grocery store!</p>
```

这将像我们想要的那样输出我们的消息：

```html
<p>Welcome to our <b>fantastic</b> grocery store!</p>
```

## SpringBoot MVC

### ==SpringBoot构建一个完整MVC项目的思路==

![SpringBoot创建一个完整MVC项目的](https://cdn.jsdelivr.net/gh/CalvinHaynes/ImageHub@main/BlogImage/SpringBoot创建一个完整MVC项目的.6a0akl3w7ho0.png)

**官方文档地址**：https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/reference/htmlsingle/#boot-features-spring-mvc-auto-configuration

### Spring MVC 自动配置

Spring Boot 为 Spring MVC 提供了自动配置，适用于大多数应用程序。

自动配置在 Spring 的默认值之上添加了以下功能：

- 包括`ContentNegotiatingViewResolver`和`BeanNameViewResolver`两个 bean。
- 支持提供静态资源，包括对 WebJars 的支持
- 自动注册`Converter`，`GenericConverter`和`Formatter` bean。
- 支持`HttpMessageConverters`
- 自动注册`MessageCodesResolver`
- 静态`index.html`支持。
- 自定义`Favicon`支持。
- `ConfigurableWebBindingInitializer`bean 的自动使用。

### SpringMVC在自动配置基础上自定义配置

​		**如果您想保留那些 Spring Boot MVC 自定义并进行更多[MVC 自定义](https://docs.spring.io/spring/docs/5.2.4.RELEASE/spring-framework-reference/web.html#mvc)（拦截器、格式化程序、视图控制器和其他功能），您可以添加自己`@Configuration`的类型类，`WebMvcConfigurer`但不添加 `@EnableWebMvc`.**

#### 自定义视图解析器ViewResolver

##### 1 - **先看看自动配置的ContentNegotiatingViewResolver 内容协商视图解析器**：

ContentNegotiatingViewResolver本身不解析视图，而是委托给其他ViewResolvers，此视图解析器根据请求的媒体类型选择合适的View ，一旦确定了请求的媒体类型，这个解析器就会查询每个委托视图解析器以获得一个View并确定请求的媒体类型是否与视图的内容类型兼容）， 返回最兼容的视图

###### 分析视图解析过程

**resolveViewName方法**

```java
public View resolveViewName(String viewName, Locale locale) throws Exception {
    //.........
    if (requestedMediaTypes != null) {
        List<View> candidateViews = getCandidateViews(viewName, locale, requestedMediaTypes);
        View bestView = getBestView(candidateViews, requestedMediaTypes, attrs);
        if (bestView != null) {
            return bestView;
        }
    }
}
```

**getCandidateViews方法**

就是从viewResolvers属性中读取候选的viewResolvers然后返回

```java
private List<View> getCandidateViews(String viewName, Locale locale, List<MediaType> requestedMediaTypes)
			throws Exception {

		List<View> candidateViews = new ArrayList<>();
		if (this.viewResolvers != null) {
			Assert.state(this.contentNegotiationManager != null, "No ContentNegotiationManager set");
			for (ViewResolver viewResolver : this.viewResolvers) {
				View view = viewResolver.resolveViewName(viewName, locale);
				if (view != null) {
					candidateViews.add(view);
				}
				for (MediaType requestedMediaType : requestedMediaTypes) {
					List<String> extensions = this.contentNegotiationManager.resolveFileExtensions(requestedMediaType);
					for (String extension : extensions) {
						String viewNameWithExtension = viewName + '.' + extension;
						view = viewResolver.resolveViewName(viewNameWithExtension, locale);
						if (view != null) {
							candidateViews.add(view);
						}
					}
				}
			}
		}
		if (!CollectionUtils.isEmpty(this.defaultViews)) {
			candidateViews.addAll(this.defaultViews);
		}
		return candidateViews;
	}
```

**getBestView方法**

从候选的viewResolvers中挑出最好的

```java
@Nullable
private View getBestView(List<View> candidateViews, List<MediaType> requestedMediaTypes, RequestAttributes attrs) {
   for (View candidateView : candidateViews) {
      if (candidateView instanceof SmartView) {
         SmartView smartView = (SmartView) candidateView;
         if (smartView.isRedirectView()) {
            return candidateView;
         }
      }
   }
   for (MediaType mediaType : requestedMediaTypes) {
      for (View candidateView : candidateViews) {
         if (StringUtils.hasText(candidateView.getContentType())) {
            MediaType candidateContentType = MediaType.parseMediaType(candidateView.getContentType());
            if (mediaType.isCompatibleWith(candidateContentType)) {
               mediaType = mediaType.removeQualityValue();
               if (logger.isDebugEnabled()) {
                  logger.debug("Selected '" + mediaType + "' given " + requestedMediaTypes);
               }
               attrs.setAttribute(View.SELECTED_CONTENT_TYPE, mediaType, RequestAttributes.SCOPE_REQUEST);
               return candidateView;
            }
         }
      }
   }
   return null;
}
```

##### 2 - 自定义一个视图解析器并Debug查看

在MVC配置类中：

- 写一个自己的视图解析器静态类（继承ViewResolver接口，重写方法）
- 将自己写的视图解析器注册到Spring的Bean中

```java
package top.calvinhaynes.demo6_thymeleaf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;

/**
 * mvc配置类
 *
 * @author CalvinHaynes
 * @date 2021/09/16
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     * 我的视图解析器注册到 bean 中
     *
     * @return {@link ViewResolver}
     */
    @Bean
    public ViewResolver myViewResolver(){
        return new MyViewResolver();
    }

    private static class MyViewResolver implements ViewResolver {

        /**
         * Resolve the given view by name.
         * <p>Note: To allow for ViewResolver chaining, a ViewResolver should
         * return {@code null} if a view with the given name is not defined in it.
         * However, this is not required: Some ViewResolvers will always attempt
         * to build View objects with the given name, unable to return {@code null}
         * (rather throwing an exception when View creation failed).
         *
         * @param viewName name of the view to resolve
         * @param locale   the Locale in which to resolve the view.
         *                 ViewResolvers that support internationalization should respect this.
         * @return the View object, or {@code null} if not found
         * (optional, to allow for ViewResolver chaining)
         * @throws Exception if the view cannot be resolved
         *                   (typically in case of problems creating an actual View object)
         */
        @Override
        public View resolveViewName(String viewName, Locale locale) throws Exception {
            return null;
        }
    }
}
```

###### Debug查看自己的视图解析器

- ==我们给 DispatcherServlet 中的 doDispatch方法 加个断点进行调试一下，因为所有的请求都会走到这个方法中==

![image](https://cdn.jsdelivr.net/gh/CalvinHaynes/ImageHub@main/BlogImage/image.4mu7zj2ml880.png)

- 访问一下http://localhost:8080/，如果你默认设置的是8080端口的话

![image](https://cdn.jsdelivr.net/gh/CalvinHaynes/ImageHub@main/BlogImage/image.b9akwle0gts.png)

- **展开==this==就可以看到==viewResolvers==属性中出现了自定义的视图解析器**

![image](https://cdn.jsdelivr.net/gh/CalvinHaynes/ImageHub@main/BlogImage/image.3cog0jeuxwy0.png)

#### 自定义转换器和格式化器

