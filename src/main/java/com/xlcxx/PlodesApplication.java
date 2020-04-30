package com.xlcxx;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.xlcxx.config.auth.damain.SecurityProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.xlcxx.plodes.mapper.*")
@EnableCaching
@EnableConfigurationProperties({SecurityProperties.class})
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class PlodesApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PlodesApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(PlodesApplication.class, args);
	}

}
