package com.coding.examination;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.coding.examination.service.UrlService;

@Profile("test")
@Configuration
public class DemoApplicationTestConfiguration {
	@Bean
	@Primary
	public UrlService urlService() {
		return Mockito.mock(UrlService.class);
	}
}