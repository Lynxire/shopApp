package terabu;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@OpenAPIDefinition(
        info = @Info(
                title = "Shop",
                version = "1.0.0",
                contact = @Contact(
                        name = "Telegram",
                        url = "https://t.me/terabu"
                )
        )
)
@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}