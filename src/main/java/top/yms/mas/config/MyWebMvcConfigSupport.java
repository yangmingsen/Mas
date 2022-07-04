package top.yms.mas.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import top.yms.mas.converts.WebMvcConfigurationSupportConfigurer;

@Configuration
public class MyWebMvcConfigSupport extends WebMvcConfigurationSupportConfigurer {


    public MasHandlerInterceptor getMasHandlerInterceptor() {
        return new MasHandlerInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getMasHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/api/**","/actuator/**","/favicon.ico","/health");
        super.addInterceptors(registry);
    }
}
