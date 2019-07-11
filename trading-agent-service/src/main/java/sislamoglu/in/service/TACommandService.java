package sislamoglu.in.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import sislamoglu.in.client.TAServiceToConnectorClient;
import sislamoglu.in.model.Currency;
import sislamoglu.in.model.CurrencyParameters;
import sislamoglu.in.util.TAServiceCurrencyUpdater;


@Service
public class TACommandService {

    @Autowired
    private TAServiceToConnectorClient connectorClient;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private TAQueryService queryService;

    TAServiceCurrencyUpdater serviceCurrencyUpdater = new TAServiceCurrencyUpdater();

    public String saveCurrencyInformation(CurrencyParameters currencyParameters){

        Currency currency = connectorClient.getHistoricalHourlyDataFromConnectorService(currencyParameters);
        currency.setName(currencyParameters.getFsym().get(0)+currencyParameters.getTsym().get(0));
        Currency prevCurrency = mongoTemplate.findOne(
                Query.query(Criteria.where("name").is(currency.getName()))
        , Currency.class);
        if (prevCurrency != null){
            serviceCurrencyUpdater.addAdditionalCurrencyInformation(prevCurrency, currency);
            currency = prevCurrency;
        }
        currency.setDataSize(currency.getCurrencyInformationList().size());
        Currency savedCurrency = mongoTemplate.save(currency);
        return savedCurrency.getId();
    }

    public String deleteCurrencyInformation(String id){
        Currency currency = queryService.getCurrencyInformation(id);
        mongoTemplate.remove(currency);
        return "Currency with name: " + currency.getName() + ", witd ID: " + currency.getId() + "is successfully deleted";
    }

}
