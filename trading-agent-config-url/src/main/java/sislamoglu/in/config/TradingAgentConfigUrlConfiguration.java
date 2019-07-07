package sislamoglu.in.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.util.ResourceUtils;

@Configuration
@PropertySources({
    @PropertySource(ResourceUtils.CLASSPATH_URL_PREFIX + "application-config-url-base.yml"),
    @PropertySource(ResourceUtils.CLASSPATH_URL_PREFIX + "application-config-url-integration.yml")
})
public class TradingAgentConfigUrlConfiguration {
}
