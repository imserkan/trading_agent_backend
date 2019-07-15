package sislamoglu.in.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.ta4j.core.BaseTimeSeries;
import org.ta4j.core.Strategy;
import org.ta4j.core.TimeSeriesManager;
import org.ta4j.core.TradingRecord;
import org.ta4j.core.analysis.criteria.TotalProfitCriterion;
import sislamoglu.in.model.Currency;
import sislamoglu.in.model.IndicatorCurrencyParameters;
import sislamoglu.in.util.TAServiceConvertToTimeSeries;
import sislamoglu.in.util.strategy.CCICorrectionStrategy;
import sislamoglu.in.util.strategy.GlobalExtremaStrategy;
import sislamoglu.in.util.strategy.MovingMomentumStrategy;
import sislamoglu.in.util.strategy.RSI2Strategy;

@Service
public class TAServiceIndicatorService {

    @Autowired
    TAServiceConvertToTimeSeries convertToTimeSeries;

    @Autowired
    MongoTemplate mongoTemplate;

    public String constructStrategy(IndicatorCurrencyParameters indicatorCurrencyParameters){

        String currencyName = indicatorCurrencyParameters.getFsym().get(0)+indicatorCurrencyParameters.getTsym().get(0);

        //TODO: CurrencyNotFoundException
        Currency currency = mongoTemplate.findOne(
                Query.query(Criteria.where("name").is(currencyName)), Currency.class);

        BaseTimeSeries baseTimeSeries = convertToTimeSeries.convertCurrencyInformationListToTimeSeries(currency.getName(), currency.getCurrencyInformationList());
        Strategy strategy = null;
        //TODO: IndicatorNotFoundException
        switch (indicatorCurrencyParameters.getIndicatorStrategyClass()){
            case RSISMA:
                RSI2Strategy rsi2Strategy = new RSI2Strategy();
                strategy = rsi2Strategy.buildStrategy(baseTimeSeries, indicatorCurrencyParameters);
                break;
            case MOVINGMOMENTUM:
                MovingMomentumStrategy movingMomentumStrategy = new MovingMomentumStrategy();
                strategy = movingMomentumStrategy.buildStrategy(baseTimeSeries, indicatorCurrencyParameters);
                break;
            case GLOBALEXTREMASTRATEGY:
                GlobalExtremaStrategy globalExtremaStrategy = new GlobalExtremaStrategy();
                strategy = globalExtremaStrategy.buildStrategy(baseTimeSeries, indicatorCurrencyParameters);
                break;
            case CCICORRECTIONSTRATEGY:
                CCICorrectionStrategy cciCorrectionStrategy = new CCICorrectionStrategy();
                strategy = cciCorrectionStrategy.buildStrategy(baseTimeSeries, indicatorCurrencyParameters);
                break;
            default:
                return "Indicator Strategy not found!";
        }
        TimeSeriesManager timeSeriesManager = new TimeSeriesManager(baseTimeSeries);
        TradingRecord tradingRecord = timeSeriesManager.run(strategy);
        return "Number of trades for the strategy: " + tradingRecord.getTradeCount() + " | "
                + "Total profit for the strategy: " + new TotalProfitCriterion().calculate(baseTimeSeries, tradingRecord);
    }
}
