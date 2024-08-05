package edu.config;

import edu.repository.LogRepository;
import edu.service.TranslateService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public TranslateService translateService() {
        return new TranslateService();
    }

    @Bean
    public LogRepository logRepository() {
        return new LogRepository();
    }

}
