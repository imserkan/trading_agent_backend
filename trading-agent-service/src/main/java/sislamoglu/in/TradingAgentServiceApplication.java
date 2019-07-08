package sislamoglu.in;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableMongoRepositories(basePackages = "sislamoglu.in.repository")
@EntityScan("sislamoglu.in.model")
@EnableMongoAuditing
public class TradingAgentServiceApplication {
    public static void main(String[] args){
        SpringApplication.run(TradingAgentServiceApplication.class, args);
    }
}
