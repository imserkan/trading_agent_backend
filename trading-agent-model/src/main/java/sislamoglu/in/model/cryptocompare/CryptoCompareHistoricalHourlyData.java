package sislamoglu.in.model.cryptocompare;

import lombok.Data;

import java.util.Date;

@Data
public class CryptoCompareHistoricalHourlyData implements ICryptoCompareHistoricalHourlyData{

    private Double high;
    private Double low;
    private Double close;
    private Double open;
    private Double volumeTo;
    private Double volumeFrom;
    private Date time;

}
