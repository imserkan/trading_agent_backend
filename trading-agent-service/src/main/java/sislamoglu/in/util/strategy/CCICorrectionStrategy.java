package sislamoglu.in.util.strategy;

import org.ta4j.core.BaseStrategy;
import org.ta4j.core.Rule;
import org.ta4j.core.Strategy;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.CCIIndicator;
import org.ta4j.core.num.Num;
import org.ta4j.core.trading.rules.OverIndicatorRule;
import org.ta4j.core.trading.rules.UnderIndicatorRule;
import sislamoglu.in.model.IndicatorCurrencyParameters;

public class CCICorrectionStrategy {

    public Strategy buildStrategy(TimeSeries timeSeries, IndicatorCurrencyParameters indicatorCurrencyParameters){
        if (timeSeries == null) {
            throw new IllegalArgumentException("Series cannot be null");
        }

        CCIIndicator longCci = new CCIIndicator(timeSeries, 200);
        CCIIndicator shortCci = new CCIIndicator(timeSeries, 5);
        Num plus100 = timeSeries.numOf(100);
        Num minus100 = timeSeries.numOf(-100);

        Rule entryRule = new OverIndicatorRule(longCci, plus100) // Bull trend
                .and(new UnderIndicatorRule(shortCci, minus100)); // Signal

        Rule exitRule = new UnderIndicatorRule(longCci, minus100) // Bear trend
                .and(new OverIndicatorRule(shortCci, plus100)); // Signal

        Strategy strategy = new BaseStrategy(entryRule, exitRule);
        strategy.setUnstablePeriod(5);
        return strategy;
    }
}
