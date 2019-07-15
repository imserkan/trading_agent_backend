package sislamoglu.in.client;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sislamoglu.in.model.Currency;
import sislamoglu.in.model.CurrencyParameters;

import java.io.Serializable;

@Service
public class TAServiceToConnectorClient implements Serializable {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    RestTemplate restTemplate;

    @Value("${tradingagent.connector.historical.hourly.data.url}")
    private String taConnectorHistoricalHourlyDataUrl;

    @JsonDeserialize
    public Currency getHistoricalHourlyDataFromConnectorService(CurrencyParameters currencyParameters){
        ResponseEntity<Currency> responseEntity = restTemplate.exchange(taConnectorHistoricalHourlyDataUrl, HttpMethod.POST, constructHttpHeaders(currencyParameters), Currency.class);
        Currency currency = null;
        logger.debug("TradingAgent-ConnectorService returned with status code {} ", responseEntity.getStatusCode());
        if (responseEntity.getStatusCode() == HttpStatus.OK){
            currency = responseEntity.getBody();
        }else{
            logger.error("TradingAgent-ConnectorService returned with status code {} ", responseEntity.getStatusCode());
        }
        return currency;
    }

    private HttpEntity<CurrencyParameters> constructHttpHeaders(CurrencyParameters currencyParameters){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<CurrencyParameters> entity = new HttpEntity<CurrencyParameters>(currencyParameters, httpHeaders);
        return entity;
    }

}
