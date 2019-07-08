package sislamoglu.in.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sislamoglu.in.model.CurrencyParameters;
import sislamoglu.in.model.cryptocompare.ConversionType;
import sislamoglu.in.model.cryptocompare.CryptoCompareHistoricalHourly;
import sislamoglu.in.model.cryptocompare.CryptoCompareHistoricalHourlyData;
import sislamoglu.in.util.APIUrls.CryptoCompareUrls;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class TAConnectorService implements Serializable {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CryptoCompareUrls cryptoCompareUrls;

    public List<String> getOnlineServers(CurrencyParameters currencyParameters){
        List<String> onlineServerList = new ArrayList<String>();
        String queryUrl = cryptoCompareUrls.constructMultiPriceToBTC(currencyParameters);
        ResponseEntity<JSONObject> response = restTemplate.exchange(queryUrl, HttpMethod.GET, constructHttpHeaders(), JSONObject.class);
        if (response.getStatusCode() == HttpStatus.OK){
            onlineServerList.add("CryptoCompare");
            logger.debug("{} is added to the online servers list.", "CryptoCompare");
        }else{
            logger.error("{} cannot be added to the online servers list.", "CryptoCompare");
        }
        return onlineServerList;
    }

    private JSONObject getHistoricalHourlyData(CurrencyParameters currencyParameters){
        JSONObject jsonObject = null;
        String querryUrl = cryptoCompareUrls.constructHistoricalHourlyToBTC(currencyParameters);
        ResponseEntity<String> response = restTemplate.exchange(querryUrl, HttpMethod.GET, constructHttpHeaders(), String.class);
        if (response.getStatusCode() == HttpStatus.OK){
            logger.debug("CryptoCompare -- {} -- HistoricalHourlyData -- {}/{}" , response.getStatusCode(), currencyParameters.getFsym().get(0), currencyParameters.getTsym().get(0));
            if (response.getBody() != null){
                jsonObject = new JSONObject(response.getBody());
                return jsonObject;
            }else{
                logger.error("CryptoCompare -- HistoricalHourlyData -- {}/{} -- EMPTY", currencyParameters.getFsym().get(0), currencyParameters.getTsym().get(0));
            }
        }else{
            logger.error("CryptoCompare -- {} -- HistoricalHourlyData -- {}/{}", response.getStatusCode(), currencyParameters.getFsym().get(0), currencyParameters.getTsym().get(0));
        }
        return jsonObject;
    }

    private HttpEntity<String> constructHttpHeaders(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>("trading agent request", httpHeaders);
        return entity;
    }

    public CryptoCompareHistoricalHourly parseJSONObject(CurrencyParameters currencyParameters){
        JSONObject jsonObject = getHistoricalHourlyData(currencyParameters);
        CryptoCompareHistoricalHourly cryptoCompareHistoricalHourly = new CryptoCompareHistoricalHourly();
        cryptoCompareHistoricalHourly.setResponse((String) jsonObject.get("Response"));
        cryptoCompareHistoricalHourly.setType((Integer) jsonObject.get("Type"));
        cryptoCompareHistoricalHourly.setFirstValueInArray((Boolean) jsonObject.get("FirstValueInArray"));
        cryptoCompareHistoricalHourly.setAggregated((Boolean) jsonObject.get("Aggregated"));
        ConversionType conversionType = new ConversionType();
        JSONObject conversionTypeObject = (JSONObject) jsonObject.get("ConversionType");
        conversionType.setType((String) conversionTypeObject.get("type"));
        conversionType.setType((String) conversionTypeObject.get("conversionSymbol"));
        cryptoCompareHistoricalHourly.setConversionType(conversionType);
        //TODO: Date can be formatted using the SimpleDataFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ")
        cryptoCompareHistoricalHourly.setTimeFrom(new Date((Integer) jsonObject.get("TimeFrom")));
        cryptoCompareHistoricalHourly.setTimeTo(new Date((Integer) jsonObject.get("TimeTo")));
        cryptoCompareHistoricalHourly.setHasWarning((Boolean) jsonObject.get("HasWarning"));
        List<Object> objectList = ((JSONArray) jsonObject.get("Data")).toList();
        ArrayList<CryptoCompareHistoricalHourlyData> cryptoCompareHistoricalHourlyDataList = cryptoCompareHistoricalHourly.getCryptoCompareHistoricalHourlyDataList();
        for (Object object: objectList){
            CryptoCompareHistoricalHourlyData cryptoCompareHistoricalHourlyData = new CryptoCompareHistoricalHourlyData();
            cryptoCompareHistoricalHourlyData.setClose(Double.valueOf(((HashMap) object).get("close").toString()));
            cryptoCompareHistoricalHourlyData.setHigh(Double.valueOf(((HashMap) object).get("high").toString()));
            cryptoCompareHistoricalHourlyData.setLow(Double.valueOf(((HashMap) object).get("low").toString()));
            cryptoCompareHistoricalHourlyData.setOpen(Double.valueOf(((HashMap) object).get("open").toString()));
            cryptoCompareHistoricalHourlyData.setVolumeFrom(Double.valueOf(((HashMap) object).get("volumefrom").toString()));
            cryptoCompareHistoricalHourlyData.setVolumeTo(Double.valueOf(((HashMap) object).get("volumeto").toString()));
            cryptoCompareHistoricalHourlyData.setTime(new Date((Integer) ((HashMap) object).get("time")));
            cryptoCompareHistoricalHourlyDataList.add(cryptoCompareHistoricalHourlyData);
        }
        cryptoCompareHistoricalHourly.setCryptoCompareHistoricalHourlyDataList(cryptoCompareHistoricalHourlyDataList);
        return cryptoCompareHistoricalHourly;
    }

}
