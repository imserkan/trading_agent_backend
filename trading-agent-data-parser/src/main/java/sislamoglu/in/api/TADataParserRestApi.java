package sislamoglu.in.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sislamoglu.in.model.Currency;
import sislamoglu.in.service.TADataParserService;

@RestController
@RequestMapping("${tradingagent.dataparser.context}")
public class TADataParserRestApi {

    @Autowired
    TADataParserService dataParserService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Currency> getCurrencyInformation(){
        return new ResponseEntity<Currency>(dataParserService.getCurrencyInformation(), HttpStatus.OK);
    }
}
