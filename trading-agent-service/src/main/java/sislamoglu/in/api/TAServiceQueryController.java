package sislamoglu.in.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sislamoglu.in.model.Currency;
import sislamoglu.in.model.paging.PagedDataResponse;
import sislamoglu.in.model.query.QueryCurrencyParams;
import sislamoglu.in.service.TAQueryService;

@RestController
@RequestMapping("${tradingagent.service.context}")
@CrossOrigin
public class TAServiceQueryController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TAQueryService queryService;

    @GetMapping(value = "${api.list.cryptocompare.id.context}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Currency> getCurrencyInformation(@PathVariable String id) {
        ResponseEntity<Currency> responseEntity = null;
        try{
            responseEntity = new ResponseEntity<Currency>(queryService.getCurrencyInformation(id), HttpStatus.OK);
            return responseEntity;
        }catch (Exception ex){
            logger.error(ex.getMessage(), ex);
        }
        return responseEntity;
    }

    @PostMapping(value = "${api.list.cryptocompare.query.context}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PagedDataResponse<Currency>> queryCurrencyInformation(@RequestBody QueryCurrencyParams currencyParams){
        ResponseEntity<PagedDataResponse<Currency>> responseEntity = null;
        try{
            responseEntity = new ResponseEntity<PagedDataResponse<Currency>>(queryService.queryCurrency(currencyParams), HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex.getMessage(), ex);
        }
        return responseEntity;
    }

    

}
