package lgh.lib.data.jpa.repository;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "lgh.**.repository")
@EntityScan(basePackages = "lgh.**.entity")
public class RepositoriesConfig {
}
