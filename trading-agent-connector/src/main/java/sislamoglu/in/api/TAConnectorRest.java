package sislamoglu.in.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sislamoglu.in.model.CurrencyParameters;
import sislamoglu.in.model.cryptocompare.CryptoCompareHistoricalHourly;
import sislamoglu.in.service.TAConnectorService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "${tradingagent.connector.context}")
public class TAConnectorRest {

    @Autowired
    private TAConnectorService taConnectorService;

    @PostMapping(value = "${tradingagent.connector.availableSources.context}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<String>> getAvailableSources(@RequestBody CurrencyParameters currencyParameters){
        return new ResponseEntity<List<String>>(taConnectorService.getOnlineServers(currencyParameters), HttpStatus.OK);
    }

    @PostMapping(value = "${tradingagent.connector.historical.hourly.data.context}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CryptoCompareHistoricalHourly> getHistoricalHourlyData(@RequestBody CurrencyParameters currencyParameters){
        return new ResponseEntity<CryptoCompareHistoricalHourly>(taConnectorService.parseJSONObject(currencyParameters), HttpStatus.OK);
    }
}
