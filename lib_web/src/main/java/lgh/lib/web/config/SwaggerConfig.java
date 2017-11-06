package lgh.lib.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import lgh.lib.web.config.dto.ApiInfoSetting;
import lgh.lib.web.config.dto.ContactInfoSetting;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	@Bean
	public Docket defaultDocket(ApiInfoSetting apiInfoSetting) {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo(apiInfoSetting))
				.groupName("all")
				.useDefaultResponseMessages(true)
				.forCodeGeneration(true)
				.pathMapping("/")
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(Predicates.not(PathSelectors.regex("/error")))
				.build();
	}

    @Bean
    public Docket exampleDocket(ApiInfoSetting apiInfoSetting) {
        return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo(apiInfoSetting))
                .groupName("exmaple")
                .select()
                .apis(RequestHandlerSelectors.basePackage("lgh.app.example"))
                .paths(PathSelectors.any())
                .build();
    }

	private ApiInfo apiInfo(ApiInfoSetting apiInfoSetting) {
		ContactInfoSetting contactInfo = apiInfoSetting.getContact();
		Contact contact = new Contact(contactInfo.getName(), contactInfo.getUrl(), contactInfo.getEmail());
		return new ApiInfoBuilder().title(apiInfoSetting.getTitle()).description(apiInfoSetting.getDescription())
				.version(apiInfoSetting.getVersion()).license(apiInfoSetting.getLicense())
				.licenseUrl(apiInfoSetting.getLicenseUrl()).termsOfServiceUrl(apiInfoSetting.getTermsOfServiceUrl())
				.contact(contact).build();
	}
}