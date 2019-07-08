package sislamoglu.in.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

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
}
