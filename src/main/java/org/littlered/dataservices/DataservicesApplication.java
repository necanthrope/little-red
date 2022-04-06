package org.littlered.dataservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"org.littlered.dataservices.entity"})
@EnableJpaRepositories(basePackages = {"org.littlered.dataservices.repository"})
@EnableTransactionManagement
//@EnableCaching

//The ComponentScan annotation tells spring where to look for valid component classes.
//The app will fail without at least .controllers.
@ComponentScan({
		"org.littlered.dataservices.config",
		"org.littlered.dataservices.documentor",
		"org.littlered.dataservices.entity",
		"org.littlered.dataservices.repository",
		"org.littlered.dataservices.rest",
		"org.littlered.dataservices.security",
		"org.littlered.dataservices.service",
		"org.littlered.dataservices.listener"
})
public class DataservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataservicesApplication.class, args);
	}

}
