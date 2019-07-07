package sislamoglu.in.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sislamoglu.in.client.TADataParserConnectorClient;
import sislamoglu.in.model.Currency;
import sislamoglu.in.model.CurrencyInformation;
import sislamoglu.in.model.CurrencyParameters;
import sislamoglu.in.model.cryptocompare.CryptoCompareHistoricalHourly;
import sislamoglu.in.model.cryptocompare.CryptoCompareHistoricalHourlyData;
import sislamoglu.in.repository.CurrencyRepository;

import java.util.*;

@Service
public class TADataParserService {

    @Autowired
    private TADataParserConnectorClient connectorClient;

    @Autowired
    private CurrencyRepository currencyRepository;

    public String saveCurrencyInformation(CurrencyParameters currencyParameters){

        Currency currency = new Currency();
        currency.setName(currencyParameters.getFsym().get(0)+currencyParameters.getTsym().get(0));
        Currency prevCurrency = currencyRepository.findByName(currency.getName());
        CryptoCompareHistoricalHourly cryptoCompareHistoricalHourly = connectorClient.getHistoricalHourlyDataFromConnectorService(currencyParameters);
        if (prevCurrency != null){
            // Update the Currency
            currency = prevCurrency;
        }else{
            // Create the Currency
        }
        parseToCurrencyInformation(cryptoCompareHistoricalHourly, currency);
        Currency savedCurrency = currencyRepository.save(currency);
        return savedCurrency.getId();
    }

    public Currency getCurrencyInformation(String id) {
        return currencyRepository.findById(id).orElse(new Currency());
    }

    public String deleteCurrencyInformation(String id){
        Currency currency = getCurrencyInformation(id);
        currencyRepository.delete(currency);
        return "Currency with name: " + currency.getName() + ", witd ID: " + currency.getId() + "is successfully deleted";
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
}
