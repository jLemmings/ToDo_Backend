package ch.aintevenmad.todo;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.time.LocalDate;
import java.util.Collections;


@SpringBootApplication
public class ToDoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ch.aintevenmad.todo.ToDoApplication.class, args);
    }

    @Bean
    ApplicationRunner init(ToDoRepository repository) {
        return args -> {
            LocalDate date = LocalDate.now();
            repository.save(new ToDo("PMB", date, "Study you lazy ass!", false));
            repository.save(new ToDo("GMDU", date, "You have done it all wrong", true));
            repository.save(new ToDo("INMA", date, "Do something else", true));
            repository.save(new ToDo("SLGP", date, "Do this", false));
            repository.findAll().forEach(System.out::println);

        };
    }


    @Bean
    public FilterRegistrationBean<CorsFilter> simpleCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
