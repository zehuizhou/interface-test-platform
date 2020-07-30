package com.rabbit.config;

import com.google.common.collect.Lists;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger文档
 *
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(name = "swagger.enable", havingValue = "true")
public class SwaggerConfig {

	@Bean
	public Docket docket() {
		ParameterBuilder builder = new ParameterBuilder();
		builder.parameterType("header").name(TokenFilter.TOKEN_KEY)
				.description("header参数")
				.required(false)
				.modelRef(new ModelRef("string")); // 在swagger里显示header

		return new Docket(DocumentationType.SWAGGER_2).groupName("自定义接口")
				.apiInfo(new ApiInfoBuilder().title("测试平台自定义接口").description("这些是系统自定义接口")
						.contact(new Contact("兔子快跑", "http://www.baidu.com", "2345@163.com")).version("1.0").build())
//				.enable(true) // 是否开启
				.globalOperationParameters(Lists.newArrayList(builder.build()))
				.select().apis(RequestHandlerSelectors.basePackage("com.rabbit.controller"))// 扫描的路径包
				// 指定路径处理PathSelectors.any()代表所有的路径
				.paths(PathSelectors.any()).build();
	}
}
