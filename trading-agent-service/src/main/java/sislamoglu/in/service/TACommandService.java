package sislamoglu.in.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.ta4j.core.BaseTimeSeries;
import sislamoglu.in.client.TAServiceToConnectorClient;
import sislamoglu.in.model.Currency;
import sislamoglu.in.model.CurrencyParameters;
import sislamoglu.in.model.ta4j.CurrencyTimeSeries;
import sislamoglu.in.repository.CurrencyTimeSeriesRepository;
import sislamoglu.in.util.TAServiceConvertToTimeSeries;
import sislamoglu.in.util.TAServiceCurrencyUpdater;


@Service
public class TACommandService {

    @Autowired
    private TAServiceToConnectorClient connectorClient;

    @Autowired
    private TAServiceConvertToTimeSeries convertToTimeSeries;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    TAQueryService queryService;

    @Autowired
    CurrencyTimeSeriesRepository currencyTimeSeriesRepository;

    TAServiceCurrencyUpdater serviceCurrencyUpdater = new TAServiceCurrencyUpdater();

    public String saveCurrencyInformation(CurrencyParameters currencyParameters) {

        Currency currency = connectorClient.getHistoricalHourlyDataFromConnectorService(currencyParameters);
        currency.setName(currencyParameters.getFsym().get(0)+currencyParameters.getTsym().get(0));
        currency.setCurrencyIdentifier(currency.getName());
        // Set the CurrencyTimeSeries
        CurrencyTimeSeries currencyTimeSeries = new CurrencyTimeSeries();
        BaseTimeSeries timeSeries = convertToTimeSeries.convertCurrencyInformationListToTimeSeries(currency.getName(), currency.getCurrencyInformationList());
        currencyTimeSeries.setTimeSeries(timeSeries);
        currencyTimeSeries.setCurrencyIdentifier(currency.getName());

        Currency prevCurrency = mongoTemplate.findOne(
                Query.query(Criteria.where("name").is(currency.getName()))
        , Currency.class);
        if (prevCurrency != null){
            CurrencyTimeSeries prevCurrencyTimeSeries = mongoTemplate.findOne(
                    Query.query(Criteria.where("currencyIdentifier").is(currencyTimeSeries.getCurrencyIdentifier()))
                    , CurrencyTimeSeries.class);
            if (prevCurrencyTimeSeries != null){
                serviceCurrencyUpdater.addAdditionalCurrencyInformation(prevCurrency, currency);
                serviceCurrencyUpdater.addAdditionalTimeSeriesInformation(prevCurrencyTimeSeries, currencyTimeSeries);
                currencyTimeSeries = prevCurrencyTimeSeries;
                currency = prevCurrency;
            }
        }
        currency.setDataSize(currency.getCurrencyInformationList().size());

        Currency savedCurrency = mongoTemplate.save(currency);
//        CurrencyTimeSeries savedCurrencyTimeSeries = mongoTemplate.save(currencyTimeSeries);
//        CurrencyTimeSeries savedCurrencyTimeSeries = currencyTimeSeriesRepository.save(currencyTimeSeries);
        return savedCurrency.getId();
    }

    public String deleteCurrencyInformation(String id){
        Currency currency = queryService.getCurrencyInformation(id);
        mongoTemplate.remove(currency);
        return "Currency with name: " + currency.getName() + ", witd ID: " + currency.getId() + "is successfully deleted";
    }

}
