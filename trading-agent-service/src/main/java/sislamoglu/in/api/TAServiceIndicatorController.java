package sislamoglu.in.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sislamoglu.in.model.IndicatorCurrencyParameters;
import sislamoglu.in.service.TAServiceIndicatorService;

@RestController
@RequestMapping("${tradingagent.service.context}")
@CrossOrigin
public class TAServiceIndicatorController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TAServiceIndicatorService taServiceIndicatorService;

    @PostMapping(value = "/strategy", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> strategyBuilder(@RequestBody IndicatorCurrencyParameters indicatorCurrencyParameters) {
        String strategyResponse= null;
        try{
            strategyResponse = taServiceIndicatorService.constructStrategy(indicatorCurrencyParameters);
        }catch (Exception ex){
            logger.error(ex.getMessage(), ex);
        }
        return new ResponseEntity<>(strategyResponse, HttpStatus.OK);
    }
}
