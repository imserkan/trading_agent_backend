package sislamoglu.in.model;

import lombok.Data;

import java.util.List;

@Data
public class IndicatorCurrencyParameters extends CurrencyParameters implements IIndicatorCurrencyParameters{

    private EIndicatorClass indicatorClass;
    private EIndicatorStrategyClass indicatorStrategyClass;
    private Double stopLossPercentage;
    private Double stopGainPercentage;
    private Integer shortSmaCount;
    private Integer longSmaCount;
    private Integer shortEmaCount;
    private Integer longEmaCount;
    private List<Double> crossDownList;
    private Integer rsiBarCount;
    private Integer stochasticBarCount;
    private List<Integer> macdPeriodCount;
    private Integer emaMacdPeriodCount;
}
