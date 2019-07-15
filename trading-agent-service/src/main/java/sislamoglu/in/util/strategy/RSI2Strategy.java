package sislamoglu.in.util.strategy;

import org.ta4j.core.BaseStrategy;
import org.ta4j.core.Rule;
import org.ta4j.core.Strategy;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.RSIIndicator;
import org.ta4j.core.indicators.SMAIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.trading.rules.*;
import sislamoglu.in.model.IndicatorCurrencyParameters;

public class RSI2Strategy {

    public Strategy buildStrategy(TimeSeries timeSeries, IndicatorCurrencyParameters indicatorCurrencyParameters){
        if (timeSeries == null){
            throw new IllegalArgumentException("Series cannot be null");
        }
        ClosePriceIndicator closePrice = new ClosePriceIndicator(timeSeries);
        SMAIndicator shortSma = new SMAIndicator(closePrice, indicatorCurrencyParameters.getShortSmaCount());
        SMAIndicator longSma = new SMAIndicator(closePrice, indicatorCurrencyParameters.getLongSmaCount());

        RSIIndicator rsiIndicator = new RSIIndicator(closePrice, indicatorCurrencyParameters.getRsiBarCount());

        // Entry Rule
        Rule entryRule = new OverIndicatorRule(shortSma, longSma)
                .and(new CrossedDownIndicatorRule(rsiIndicator, indicatorCurrencyParameters.getCrossDownList().get(0)))
                .and(new OverIndicatorRule(shortSma, closePrice));

        // Exit Rule
        Rule exitRule = new UnderIndicatorRule(shortSma, longSma)
                .and(new CrossedDownIndicatorRule(rsiIndicator, indicatorCurrencyParameters.getCrossDownList().get(1)))
                .or(new StopLossRule(closePrice, indicatorCurrencyParameters.getStopLossPercentage()))
                .or(new StopGainRule(closePrice, indicatorCurrencyParameters.getStopGainPercentage()));

        return new BaseStrategy(entryRule, exitRule);
    }
}
