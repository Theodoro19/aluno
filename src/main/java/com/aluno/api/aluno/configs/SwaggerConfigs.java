package com.aluno.api.aluno.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfigs {
	@Bean
	public Docket restApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.aluno.api.aluno.controller"))
				.paths(PathSelectors.regex("/aluno.*")).build().apiInfo(restApiEndPointsInfo());
	}

	private ApiInfo restApiEndPointsInfo() {
		return new ApiInfoBuilder().title("Aluno API").description("REST API para cadastro de aluno.")
				.contact(
						new Contact("Raphael Theodoro", "https://github.com/Theodoro19", "raphaeltheodoro@hotmail.com"))
				.license("Apache 2.0").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html").version("1.0.0")
				.build();
	}
}
