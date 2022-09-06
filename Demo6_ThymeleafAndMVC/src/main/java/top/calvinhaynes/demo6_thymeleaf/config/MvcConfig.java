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
