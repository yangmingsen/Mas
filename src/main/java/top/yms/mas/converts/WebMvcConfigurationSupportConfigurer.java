package top.yms.mas.converts;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import top.yms.mas.config.HttpThreadRecordInterceptor;

@Configuration
// @ConfigurationProperties(prefix = "spring.jackson")
public class WebMvcConfigurationSupportConfigurer extends WebMvcConfigurationSupport {
    // @Autowired
    // HandlerInterceptor httpInterceptor; // 定义时间格式转换器

    // private String dataFormat;

    @Bean
    public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setDateFormat(new SimpleDateFormat("yyyyMMddHHmmss"));
        converter.setObjectMapper(mapper);
        return converter;
    } // 添加转换器

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //
        // 将我们定义的时间格式转换器添加到转换器列表中,
        // 这样jackson格式化时候但凡遇到Date类型就会转换成我们定义的格式
        //
        converters.add(jackson2HttpMessageConverter());
    }

    @Autowired
    HttpThreadRecordInterceptor httpThreadRecordInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(httpThreadRecordInterceptor);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }

}