package sislamoglu.in.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sislamoglu.in.model.Currency;
import sislamoglu.in.model.CurrencyParameters;
import sislamoglu.in.service.TADataParserService;

import java.net.URI;

@RestController
@RequestMapping("${tradingagent.dataparser.context}")
@CrossOrigin
public class TADataParserRestApi {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    TADataParserService dataParserService;

    @Value("${tradingagent.dataparser.context}")
    private String tradingagentDataparserContext;

    @PostMapping(value = "${api.list.cryptocompare.context}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<URI> saveCurrencyInformation(@RequestBody CurrencyParameters currencyParameters) {
        URI uri = null;
        try{
            String savedCurrencyId = dataParserService.saveCurrencyInformation(currencyParameters);
            uri = new URI(tradingagentDataparserContext + "/" + savedCurrencyId);
        }catch (Exception ex){
            logger.error(ex.getMessage(), ex);
        }
        return new ResponseEntity<URI>(uri, HttpStatus.CREATED);
    }

    @PutMapping(value = "${api.list.cryptocompare.context}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<URI> updateCurrencyInformation(@RequestBody CurrencyParameters currencyParameters) {
        URI uri = null;
        try{
            String savedCurrencyId = dataParserService.saveCurrencyInformation(currencyParameters);
            uri = new URI(tradingagentDataparserContext + "/" + savedCurrencyId);
        }catch (Exception ex){
            logger.error(ex.getMessage(), ex);
        }
        return new ResponseEntity<URI>(uri, HttpStatus.OK);
    }

    @GetMapping(value = "${api.list.cryptocompare.id.context}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Currency> getCurrencyInformation(@PathVariable String id) {
        ResponseEntity<Currency> responseEntity = null;
        try{
            responseEntity = new ResponseEntity<Currency>(dataParserService.getCurrencyInformation(id), HttpStatus.OK);
            return responseEntity;
        }catch (Exception ex){
            logger.error(ex.getMessage(), ex);
        }
        return responseEntity;
    }

    @DeleteMapping(value = "${api.list.cryptocompare.id.context}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> deleteCurrencyInformation(@PathVariable String id) {
        ResponseEntity<String> responseEntity = null;
        try{
            responseEntity = new ResponseEntity<String>(dataParserService.deleteCurrencyInformation(id), HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex.getMessage(), ex);
        }
        return responseEntity;
    }
}
