package sislamoglu.in.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sislamoglu.in.model.CurrencyParameters;
import sislamoglu.in.model.cryptocompare.CryptoCompareHistoricalHourly;

@Service
public class TADataParserConnectorClient {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    RestTemplate restTemplate;

    @Value("${tradingagent.connector.historical.hourly.data.url}")
    private String taConnectorHistoricalHourlyDataUrl;

    public CryptoCompareHistoricalHourly getHistoricalHourlyDataFromConnectorService(CurrencyParameters currencyParameters){
        ResponseEntity<CryptoCompareHistoricalHourly> responseEntity = restTemplate.exchange(taConnectorHistoricalHourlyDataUrl, HttpMethod.POST, constructHttpHeaders(currencyParameters), CryptoCompareHistoricalHourly.class);
        CryptoCompareHistoricalHourly cryptoCompareHistoricalHourly = null;
        logger.debug("TradingAgent-ConnectorService returned with status code {} ", responseEntity.getStatusCode());
        if (responseEntity.getStatusCode() == HttpStatus.OK){
            cryptoCompareHistoricalHourly = responseEntity.getBody();
        }else{
            logger.error("TradingAgent-ConnectorService returned with status code {} ", responseEntity.getStatusCode());
        }
        return cryptoCompareHistoricalHourly;
    }

    private HttpEntity<CurrencyParameters> constructHttpHeaders(CurrencyParameters currencyParameters){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<CurrencyParameters> entity = new HttpEntity<CurrencyParameters>(currencyParameters, httpHeaders);
        return entity;
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
