package sislamoglu.in;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan("sislamoglu.in.model")
public class TradingAgentDataParserApplication {

    public static void main(String[] args){
        SpringApplication.run(TradingAgentDataParserApplication.class, args);
    }
}
