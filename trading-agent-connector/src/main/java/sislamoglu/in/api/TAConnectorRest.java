package sislamoglu.in.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sislamoglu.in.model.Currency;
import sislamoglu.in.model.CurrencyParameters;
import sislamoglu.in.service.TAConnectorService;
import sislamoglu.in.util.dataparser.CryptoCompareDataParserService;

import java.util.List;

@RestController
@RequestMapping(value = "${tradingagent.connector.context}")
public class TAConnectorRest {

    @Autowired
    private TAConnectorService connectorService;

    @Autowired
    private CryptoCompareDataParserService cryptoCompareDataParserService;

    @PostMapping(value = "${tradingagent.connector.availableSources.context}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<String>> getAvailableSources(@RequestBody CurrencyParameters currencyParameters){
        return new ResponseEntity<List<String>>(connectorService.getOnlineServers(currencyParameters), HttpStatus.OK);
    }

    @PostMapping(value = "${tradingagent.connector.historical.hourly.data.context}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Currency> getHistoricalHourlyData(@RequestBody CurrencyParameters currencyParameters){
        return new ResponseEntity<Currency>(cryptoCompareDataParserService.convertToCurrencyInformation(currencyParameters), HttpStatus.OK);
    }
}
