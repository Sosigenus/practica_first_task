package practica.springstudentsz.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.servers.Server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API студентов",
                description = "Описание API студентов с фильтрацией, сортировкой и пагинацией.",
                version = "1.0",
                contact = @Contact(
                        name = "Разработчик",
                        email = "dev@example.com",
                        url = "https://example.com"
                )
        )
)
public class SpringFoxConfig {
    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .servers(
                        List.of(
                                new Server().url("http://localhost:8080")
                        )
                )
                .info(
                        new io.swagger.v3.oas.models.info.Info()
                                .title("Our Students")
                                .description("API для работы со студентами")
                                .version("1.0")
                );
    }
}
