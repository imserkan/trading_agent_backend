package sislamoglu.in.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sislamoglu.in.client.TADataParserConnectorClient;
import sislamoglu.in.model.Currency;
import sislamoglu.in.model.CurrencyInformation;
import sislamoglu.in.model.CurrencyParameters;

import java.util.*;

@Service
public class TADataParserService {

    @Autowired
    private TADataParserConnectorClient connectorClient;

    public Currency getCurrencyInformation(){
        CurrencyParameters currencyParameters = new CurrencyParameters();
        currencyParameters.setFsym(Arrays.asList("WTC"));
        currencyParameters.setTsym(Arrays.asList("BTC"));
        currencyParameters.setE("CCCAGG");
        currencyParameters.setAggregate(1);
        currencyParameters.setAggregatePredictableTimePeriods(true);
        currencyParameters.setLimit(168);
        currencyParameters.setExtraParams("trading-agent");
        currencyParameters.setSign(false);
        Map<String, String> resultMap = connectorClient.getHistoricalHourlyDataFromConnectorService(currencyParameters);

        Currency currency = new Currency();
        currency.setName(currencyParameters.getFsym().get(0)+currencyParameters.getTsym().get(0));
        List<String> strList = (ArrayList) (resultMap.get("Data"));
//        ArrayList<HashMap> dataList = (ArrayList<HashMap<String, String>>)(resultMap.get("Data"));
//        parseToCurrencyInformation(resultMap, currency);
        return currency;
    }

    private void parseToCurrencyInformation(ArrayList<HashMap<String,String>> resultList, Currency currency){
        List<CurrencyInformation> currencyInformationList = currency.getCurrencyInformationList();
        for(HashMap resultMap: resultList){

        }
    }
}
