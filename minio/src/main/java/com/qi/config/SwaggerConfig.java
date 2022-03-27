package com.qi.config; // package com.qi.miniodemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author jiaqi.zhang
 * @version 1.0
 * @date 2022/3/17 16:40
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
  @Bean
  public Docket createRestApi() {
    //    return new Docket(DocumentationType.SWAGGER_2)
    //        .apiInfo(apiInfo())
    //        .select()
    //        .apis(RequestHandlerSelectors.basePackage("com.qi"))
    //        .paths(PathSelectors.any())
    //        .build();
    return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("FileProxyByMinio API Doc")
        .description("This is a restful api document of Kitty.")
        .version("1.0")
        .build();
  }
}
