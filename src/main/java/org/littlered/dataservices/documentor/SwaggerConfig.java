package org.littlered.dataservices.documentor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by Jeremy on 7/4/2017.
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				//.apis(RequestHandlerSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("org.littlered.dataservices.rest.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(metaData());
	}
	private ApiInfo metaData() {
		ApiInfo apiInfo = new ApiInfo(
				"Little Red data service REST API",
				"REST API for Little Red, a registration system for small tabletop game conventions.",
				"1.0",
				"Terms of service",
				new Contact("Jeremy Tidwell", "https://github.com/necanthrope/little-red", "necanthrope@gmail.com"),
				"Apache License Version 2.0",
				"https://www.apache.org/licenses/LICENSE-2.0");
		return apiInfo;
	}
}
