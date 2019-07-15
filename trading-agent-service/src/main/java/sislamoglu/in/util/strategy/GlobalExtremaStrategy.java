package sislamoglu.in.util.strategy;

import org.ta4j.core.BaseStrategy;
import org.ta4j.core.Rule;
import org.ta4j.core.Strategy;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.helpers.*;
import org.ta4j.core.trading.rules.OverIndicatorRule;
import org.ta4j.core.trading.rules.UnderIndicatorRule;
import sislamoglu.in.model.IndicatorCurrencyParameters;

public class GlobalExtremaStrategy {

    private static final int NB_BARS_PER_WEEK = 12 * 24 * 7;

    public Strategy buildStrategy(TimeSeries timeSeries, IndicatorCurrencyParameters indicatorCurrencyParameters){
        if (timeSeries == null){
            throw new IllegalArgumentException("Series cannot be null");
        }
        ClosePriceIndicator closePrice = new ClosePriceIndicator(timeSeries);
        MaxPriceIndicator maxPrices = new MaxPriceIndicator(timeSeries);
        HighestValueIndicator weekMaxPrice = new HighestValueIndicator(maxPrices, NB_BARS_PER_WEEK);
        // Getting the min price over the past week
        MinPriceIndicator minPrices = new MinPriceIndicator(timeSeries);
        LowestValueIndicator weekMinPrice = new LowestValueIndicator(minPrices, NB_BARS_PER_WEEK);

        // Going long if the close price goes below the min price
        MultiplierIndicator downWeek = new MultiplierIndicator(weekMinPrice, 1.004);
        Rule entryRule = new UnderIndicatorRule(closePrice, downWeek);
        MultiplierIndicator upWeek = new MultiplierIndicator(weekMaxPrice, 0.996);
        Rule exitRule = new OverIndicatorRule(closePrice, upWeek);

        return new BaseStrategy(entryRule, exitRule);
    }
}
