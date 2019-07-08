package sislamoglu.in.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sislamoglu.in.client.TAServiceToConnectorClient;
import sislamoglu.in.model.Currency;
import sislamoglu.in.model.CurrencyParameters;
import sislamoglu.in.repository.TAServiceCurrencyRepository;


@Service
public class TAService {

    @Autowired
    private TAServiceToConnectorClient connectorClient;

    @Autowired
    private TAServiceCurrencyRepository currencyRepository;

    public String saveCurrencyInformation(CurrencyParameters currencyParameters){

        Currency currency = connectorClient.getHistoricalHourlyDataFromConnectorService(currencyParameters);
        Currency prevCurrency = currencyRepository.findByName(currency.getName());
        if (prevCurrency != null){
            // Update the Currency
            currency = prevCurrency;
        }else{
            // Create the Currency
        }
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

}
