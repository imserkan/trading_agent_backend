package sislamoglu.in.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sislamoglu.in.model.CurrencyParameters;
import sislamoglu.in.service.TACommandService;

import java.net.URI;

@RestController
@RequestMapping("${tradingagent.service.context}")
@CrossOrigin
public class TAServiceCommandController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    TACommandService service;

    @Value("${tradingagent.service.context}")
    private String tradingagentServiceContext;

    @PostMapping(value = "${api.list.cryptocompare.context}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<URI> saveCurrencyInformation(@RequestBody CurrencyParameters currencyParameters) {
        URI uri = null;
        try{
            String savedCurrencyId = service.saveCurrencyInformation(currencyParameters);
            uri = new URI(tradingagentServiceContext + "/" + savedCurrencyId);
        }catch (Exception ex){
            logger.error(ex.getMessage(), ex);
        }
        return new ResponseEntity<>(uri, HttpStatus.CREATED);
    }

    @PutMapping(value = "${api.list.cryptocompare.context}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<URI> updateCurrencyInformation(@RequestBody CurrencyParameters currencyParameters) {
        URI uri = null;
        try{
            String savedCurrencyId = service.saveCurrencyInformation(currencyParameters);
            uri = new URI(tradingagentServiceContext + "/" + savedCurrencyId);
        }catch (Exception ex){
            logger.error(ex.getMessage(), ex);
        }
        return new ResponseEntity<>(uri, HttpStatus.OK);
    }

    @DeleteMapping(value = "${api.list.cryptocompare.id.context}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> deleteCurrencyInformation(@PathVariable String id) {
        ResponseEntity<String> responseEntity = null;
        try{
            responseEntity = new ResponseEntity<String>(service.deleteCurrencyInformation(id), HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex.getMessage(), ex);
        }
        return responseEntity;
    }
}
