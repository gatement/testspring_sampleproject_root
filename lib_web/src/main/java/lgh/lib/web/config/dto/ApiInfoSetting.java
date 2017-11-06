package lgh.lib.web.config.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@ConfigurationProperties("app.api-info")
@Component
public class ApiInfoSetting {
	private String title;
	private String description;
	private String version;
	private String license;
	private String licenseUrl;
	private String termsOfServiceUrl;
	private ContactInfoSetting contact;
}
