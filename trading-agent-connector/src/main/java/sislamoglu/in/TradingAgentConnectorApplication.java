package sislamoglu.in;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class TradingAgentConnectorApplication {

    public static void main(String[] args){
        SpringApplication.run(TradingAgentConnectorApplication.class, args);
    }
}
