package eric.koo.spring.boot.doc.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.io.InputStream;

@Configuration
class SwaggerConfig {
    /*
    Page - /swagger-ui/
    Docs - /v2/api-docs?{groupName}
    */

    @Bean
    public Docket api() {
        final Docket docket = new Docket(DocumentationType.SWAGGER_2);

        return docket.groupName("Eric Koo's Document")
                .apiInfo(apiInfo())
                .ignoredParameterTypes(InputStream.class, Resource.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("eric.koo.spring.boot.doc.core.mvc"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Eric Koo's Document APIs")
                .description("APIs specification")
                .version("v1.0")
                .license("v1.0")
                .contact(new Contact("Eric Koo", "https://github.com/KooWeiHao", null))
                .build();
    }
}
