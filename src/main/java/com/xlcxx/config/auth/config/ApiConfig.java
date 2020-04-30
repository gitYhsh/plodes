package com.xlcxx.config.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ApiConfig {

	@Bean
	public Docket createRequstApi() {

		List<ResponseMessage> responseMessageList = new ArrayList<>();
		responseMessageList.add(new ResponseMessageBuilder().code(200).message("请求成功").responseModel(new ModelRef("APiResult")).build());
		responseMessageList.add(new ResponseMessageBuilder().code(500).message("服务器内部错误").responseModel(new ModelRef("APiResult")).build());
		return new Docket(DocumentationType.SWAGGER_2)
				.globalResponseMessage(RequestMethod.GET, responseMessageList)
				.globalResponseMessage(RequestMethod.POST, responseMessageList)
				.groupName("接口文档")
				.apiInfo(apiInfo())
				.select()
				.apis( RequestHandlerSelectors.basePackage("com.xlcxx.web.controller.system"))
				.paths(PathSelectors.any())
				.build();
	}
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("新里程目标系统免认证api文档")
				.description("免认证前端接口文档")
				.version("1.0")
				.build();
	}
}