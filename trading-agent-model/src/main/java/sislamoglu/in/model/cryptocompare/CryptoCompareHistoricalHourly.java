package sislamoglu.in.model.cryptocompare;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class CryptoCompareHistoricalHourly implements ICryptoCompareHistoricalHourly{

    private String response;
    //TODO: check for the ratelimit response from the CryptoCompare API
//    private String rateLimit;
    private Integer type;
    private Boolean firstValueInArray;
    private ConversionType conversionType;
    private Boolean aggregated;
    private Date timeFrom;
    private Date timeTo;
    private Boolean hasWarning;
    private ArrayList<CryptoCompareHistoricalHourlyData> cryptoCompareHistoricalHourlyDataList;


    public ArrayList<CryptoCompareHistoricalHourlyData> getCryptoCompareHistoricalHourlyDataList() {
        if (this.cryptoCompareHistoricalHourlyDataList == null){
            return new ArrayList<CryptoCompareHistoricalHourlyData>();
        }
        return this.cryptoCompareHistoricalHourlyDataList;
    }
}
