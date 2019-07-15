package sislamoglu.in.util.dataparser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import sislamoglu.in.model.Currency;
import sislamoglu.in.model.CurrencyInformation;
import sislamoglu.in.model.CurrencyParameters;
import sislamoglu.in.model.cryptocompare.CryptoCompareHistoricalHourly;
import sislamoglu.in.model.cryptocompare.CryptoCompareHistoricalHourlyData;
import sislamoglu.in.service.TAConnectorService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
public class CryptoCompareDataParserService implements Serializable {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TAConnectorService connectorService;

    @Value("${tradingagent.connector.historical.hourly.data.url}")
    private String taConnectorHistoricalHourlyDataUrl;

    public Currency convertToCurrencyInformation(CurrencyParameters currencyParameters){

        Currency currency = new Currency();
        currency.setName(currencyParameters.getFsym().get(0)+currencyParameters.getTsym().get(0));
        CryptoCompareHistoricalHourly cryptoCompareHistoricalHourly = getHistoricalHourlyDataFromConnectorService(currencyParameters);
        parseToCurrencyInformation(cryptoCompareHistoricalHourly, currency);
        return currency;
    }

    private void parseToCurrencyInformation(CryptoCompareHistoricalHourly cryptoCompareHistoricalHourly, Currency currency){
        List<CurrencyInformation> currencyInformationList = new ArrayList<CurrencyInformation>();
        for(CryptoCompareHistoricalHourlyData cryptoCompareHistoricalHourlyData: cryptoCompareHistoricalHourly.getCryptoCompareHistoricalHourlyDataList()){
            CurrencyInformation currencyInformation = new CurrencyInformation();
            currencyInformation.setClosePrice(cryptoCompareHistoricalHourlyData.getClose());
            currencyInformation.setHighPrice(cryptoCompareHistoricalHourlyData.getHigh());
            currencyInformation.setLowPrice(cryptoCompareHistoricalHourlyData.getLow());
            currencyInformation.setOpenPrice(cryptoCompareHistoricalHourlyData.getOpen());
            currencyInformation.setVolumeFrom(cryptoCompareHistoricalHourlyData.getVolumeFrom());
            currencyInformation.setVolumeTo(cryptoCompareHistoricalHourlyData.getVolumeTo());
            currencyInformation.setTimeInterval(cryptoCompareHistoricalHourlyData.getTime());
            currencyInformationList.add(currencyInformation);
        }
        currency.setCurrencyInformationList(currencyInformationList);

    }

    private CryptoCompareHistoricalHourly getHistoricalHourlyDataFromConnectorService(CurrencyParameters currencyParameters){
        return connectorService.parseJSONObject(currencyParameters);
    }

    private HttpEntity<CurrencyParameters> constructHttpHeaders(CurrencyParameters currencyParameters){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<CurrencyParameters> entity = new HttpEntity<CurrencyParameters>(currencyParameters, httpHeaders);
        return entity;
    }
}
