package sislamoglu.in.util.APIUrls;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sislamoglu.in.model.CurrencyParameters;

@Service
public class CryptoCompareUrls {
    @Value("${api.list.cryptocompare.base.url}")
    private String baseApiUrl;

    @Value("${api.list.cryptocompare.base.data.pricemulti.fsyms.url}")
    private String apiDataMultiPriceUrl;

    @Value("${api.list.cryptocompare.base.data.pricemulti.tsyms.context}")
    private String apiDataMultiTsymsContext;

    @Value("${api.list.cryptocompare.base.data.historical.hourly.fsym.url}")
    private String apiHistoricalHourlyUrl;

    @Value("${api.list.cryptocompare.base.data.historical.hourly.tsym.context}")
    private String apiHistoricalHourlyTsymContext;

    @Value("${api.list.cryptocompare.base.data.historical.hourly.e.context}")
    private String apiHistoricalHourlyEContext;

    @Value("${api.list.cryptocompare.base.data.historical.hourly.aggregate.context}")
    private String apiHistoricalHourlyAggregateContext;

    @Value("${api.list.cryptocompare.base.data.historical.hourly.aggregatePredictableTimePeriods.context}")
    private String apiHistoricalHourlyAggregatePredictableTimePeriodsContext;

    @Value("${api.list.cryptocompare.base.data.historical.hourly.limit.context}")
    private String apiHistoricalHourlyLimitContext;

    @Value("${api.list.cryptocompare.base.data.historical.hourly.toTs.context}")
    private String apiHistoricalHourlyToTsContext;

    @Value("${api.list.cryptocompare.base.data.historical.hourly.extraParams.context}")
    private String apiHistoricalHourlyExtraParamsContext;

    @Value("${api.list.cryptocompare.base.data.historical.hourly.sign.context}")
    private String apiHistoricalHourlySignContext;

    @Value("${api.keys.cryptocompare}")
    private String apiKey;

    public String constructMultiPriceToBTC(CurrencyParameters currencyParameters){
        String constructedUrl = apiDataMultiPriceUrl;
        for(String currency: currencyParameters.getFsym()){
            constructedUrl = constructedUrl + currency + ",";
        }
        constructedUrl = constructedUrl.substring(0, constructedUrl.length()-1);
        constructedUrl = constructedUrl + "&" + apiDataMultiTsymsContext;
        for(String convert: currencyParameters.getTsym()){
            constructedUrl = constructedUrl + convert + ",";
        }
        constructedUrl = constructedUrl.substring(0, constructedUrl.length()-1);
        constructedUrl = constructedUrl + "api_key=" + apiKey;
        return constructedUrl;
    }

    public String constructHistoricalHourlyToBTC(CurrencyParameters currencyParameters){
        String constructedUrl = apiHistoricalHourlyUrl;
        constructedUrl = constructedUrl + currencyParameters.getFsym().get(0) + "&"
                + apiHistoricalHourlyTsymContext + currencyParameters.getTsym().get(0) + "&"
                + apiHistoricalHourlyEContext + currencyParameters.getE() + "&"
                + apiHistoricalHourlyAggregateContext + currencyParameters.getAggregate() + "&"
                + apiHistoricalHourlyAggregatePredictableTimePeriodsContext + currencyParameters.getAggregatePredictableTimePeriods().toString() + "&"
                + apiHistoricalHourlyLimitContext + currencyParameters.getLimit() + "&"
//                + apiHistoricalHourlyToTsContext + currencyParameters.getToTs() + "&"
                + apiHistoricalHourlyExtraParamsContext + currencyParameters.getExtraParams() + "&"
                + apiHistoricalHourlySignContext+ currencyParameters.getSign().toString() + "&"
                + "api_key=" + apiKey;
        return constructedUrl;
    }
}
