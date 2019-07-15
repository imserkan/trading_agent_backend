package sislamoglu.in.util.strategy;

import org.ta4j.core.BaseStrategy;
import org.ta4j.core.Rule;
import org.ta4j.core.Strategy;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.MACDIndicator;
import org.ta4j.core.indicators.StochasticOscillatorKIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.trading.rules.*;
import sislamoglu.in.model.IndicatorCurrencyParameters;

public class MovingMomentumStrategy {

    public Strategy buildStrategy(TimeSeries timeSeries, IndicatorCurrencyParameters indicatorCurrencyParameters){
        if (timeSeries == null) {
            throw new IllegalArgumentException("Series cannot be null");
        }

        ClosePriceIndicator closePrice = new ClosePriceIndicator(timeSeries);

        // The bias is bullish when the shorter-moving average moves above the longer moving average.
        // The bias is bearish when the shorter-moving average moves below the longer moving average.
        EMAIndicator shortEma = new EMAIndicator(closePrice, indicatorCurrencyParameters.getShortEmaCount());
        EMAIndicator longEma = new EMAIndicator(closePrice, indicatorCurrencyParameters.getLongEmaCount());

        StochasticOscillatorKIndicator stochasticOscillK = new StochasticOscillatorKIndicator(timeSeries, indicatorCurrencyParameters.getStochasticBarCount());

        MACDIndicator macd = new MACDIndicator(closePrice, indicatorCurrencyParameters.getMacdPeriodCount().get(0), indicatorCurrencyParameters.getMacdPeriodCount().get(1));
        EMAIndicator emaMacd = new EMAIndicator(macd, indicatorCurrencyParameters.getEmaMacdPeriodCount());

        // Entry rule
        Rule entryRule = new OverIndicatorRule(shortEma, longEma)
                .and(new CrossedDownIndicatorRule(stochasticOscillK, indicatorCurrencyParameters.getCrossDownList().get(0)))
                .and(new OverIndicatorRule(macd, emaMacd));

        // Exit rule
        Rule exitRule = new UnderIndicatorRule(shortEma, longEma)
                .and(new CrossedUpIndicatorRule(stochasticOscillK,indicatorCurrencyParameters.getCrossDownList().get(1)))
                .and(new UnderIndicatorRule(macd, emaMacd))
                .or(new StopLossRule(closePrice, indicatorCurrencyParameters.getStopLossPercentage()))
                .or(new StopGainRule(closePrice, indicatorCurrencyParameters.getStopGainPercentage()));

        return new BaseStrategy(entryRule, exitRule);
    }
}
