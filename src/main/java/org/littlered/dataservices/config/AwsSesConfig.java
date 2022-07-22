package org.littlered.dataservices.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsSesConfig {
	private final String region;

	@Value("${email.accessKey}")
	private String accessKey;

	@Value("${email.secretAccessKey}")
	private String secretAccessKey;

	@Value("${email.AESRegion}")
	private String aesRegion;

	public AwsSesConfig(@Value("${email.region}") String region) {
		this.region = region;
	}

	/**
	 * Build the AWS ses client
	 *
	 * @return AmazonSimpleEmailServiceClientBuilder
	 */
	@Bean
	public AmazonSimpleEmailService amazonSimpleEmailService() {
		return AmazonSimpleEmailServiceClientBuilder.standard()
				.withCredentials(
						new AWSStaticCredentialsProvider(
								new BasicAWSCredentials(
										accessKey,
										secretAccessKey)))
				.withRegion(Regions.valueOf(aesRegion))
				.build();
	}
}
