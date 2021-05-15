package cn.edu.shu.xj.ser.config;

import cn.edu.shu.xj.ser.handler.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置拦截器
 * 在配置类上添加了注解@Configuration，标明了该类是一个配置类并且会将该类作为一个SpringBean添加到IOC容器内
 * WebMvcConfigurerAdapter该抽象类其实里面没有任何的方法实现，只是空实现了接口
 * WebMvcConfigurer内的全部方法，并没有给出任何的业务逻辑处理，这一点设计恰到好处的让我们不必去实现那些我们不用的方法，
 * 都交由WebMvcConfigurerAdapter抽象类空实现,如果我们需要针对具体的某一个方法做出逻辑处理,仅仅需要在WebMvcConfigurerAdapter子类中@Override对应方法就可以了。
 * InterceptorRegistry内的addInterceptor需要一个实现HandlerInterceptor接口的拦截器实例，addPathPatterns方法用于设置拦截器的过滤路径规则。
 * 这里我拦截所有请求，通过判断是否有@LoginRequired注解 决定是否需要登录
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/**");
    }
    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }
}
