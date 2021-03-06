package top.yms.mas.config;


import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.LinkedList;

@Configuration
public class Swagger2Configuration {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build();
    }

    //基本信息的配置，信息会在api文档上显示
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Mas接口文档")
                .description("Mas相关接口的文档")
                .termsOfServiceUrl("http://localhost:8080/")
                .version("1.0")
                .build();
    }


    static final String AUTHORIZATION_HEAD = "token";
    static final String USER_IDENTIFIER = "USER-ID";//redisSessionId 的身份标识

    //@Bean
    public Docket templateApi()
    {
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name(AUTHORIZATION_HEAD).description("token")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build();
//        ParameterBuilder adminTokenPar = new ParameterBuilder();
//        adminTokenPar.name(SessionConstants.ADMIN_AUTHORIZATION_HEAD).description("管理控制台 token 令牌" )
//                .modelRef(new ModelRef("string" )).parameterType("header" ).required(false).build();
        ParameterBuilder user = new ParameterBuilder();
        user.name(USER_IDENTIFIER).description("user-id")
                .modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        LinkedList<Parameter> list = new LinkedList<>();
        list.add(tokenPar.build());
//        list.add(adminTokenPar.build());
        list.add(user.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()

                //控制暴露出去的路径下的实例
                //如果某个接口不想暴露,可以使用以下注解
                //@ApiIgnore 这样,该接口就不会暴露在 swagger2 的页面下
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build()
                .directModelSubstitute(Long.class, String.class)
                .globalOperationParameters(list);
    }




}
