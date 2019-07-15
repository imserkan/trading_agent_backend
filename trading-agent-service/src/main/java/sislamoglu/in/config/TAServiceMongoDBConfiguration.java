package sislamoglu.in.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.Arrays;

@Configuration
public class TAServiceMongoDBConfiguration extends AbstractMongoConfiguration {

    @Value("${tradingagent.service.mongodb.databaseName}")
    private String databaseName;

    @Value("${tradingagent.service.mongodb.databaseHost}")
    private String databaseHost;

    @Value("${http.localhost.port.mongodb_port}")
    private int databasePort;

    public MongoClient mongoClient() {
        return new MongoClient(databaseHost, databasePort);
    }

    protected String getDatabaseName() {
        return databaseName;
    }

    @Bean
    public CustomConversions customConversions() {
        return new MongoCustomConversions(Arrays.asList(
                new ZonedDateTimeToDocumentConverter(),
                new DocumentToZonedDateTimeConverter()
        ));
    }

}
