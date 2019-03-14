package pl.edu.wat.web.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;
import java.util.TimeZone;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"pl.edu.wat.web", "pl.edu.wat.backend"})
@EnableJpaRepositories("pl.edu.wat.backend.repository")
@EntityScan("pl.edu.wat.domain.entity")
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperCustomization() {
        return jacksonObjectMapperBuilder -> {
                jacksonObjectMapperBuilder.timeZone(TimeZone.getDefault());
                jacksonObjectMapperBuilder.locale(Locale.getDefault());
        };
    }
}

