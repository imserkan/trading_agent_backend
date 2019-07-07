package sislamoglu.in.model.cryptocompare;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface ICryptoCompareHistoricalHourly {

    /*
     * Returns the http response of the CryptoCompare API request
     * @param none
     * @returns String
     * */
    String getResponse();

    /*
     * Returns the type of the CryptoCompare API request
     * @param none
     * @returns Integer
     * */
    Integer getType();

    /*
     * Returns the first value in array of the CryptoCompare API request
     * @param none
     * @returns Boolean
     * */
    Boolean getFirstValueInArray();


    /*
     * Returns the conversion type of the CryptoCompare API request
     * @param none
     * @returns ConversionType
     * */
    ConversionType getConversionType();

    /*
     * Returns aggregation status of the CryptoCompare API request
     * @param none
     * @returns boolean
     * */
    Boolean getAggregated();

    /*
     * Returns starting time of the CryptoCompare API request
     * @param none
     * @returns Date
     * */
    Date getTimeFrom();

    /*
     * Returns ending time of the CryptoCompare API request
     * @param none
     * @returns Date
     * */
    Date getTimeTo();

    /*
     * Returns warning status of the CryptoCompare API request
     * @param none
     * @returns boolean
     * */
    Boolean getHasWarning();

    /*
     * Sets the http response of the CryptoCompare API request
     * @param String
     * @returns void
     * */
    void setResponse(String response);

    /*
     * Sets the type of the CryptoCompare API request
     * @param Integer
     * @returns void
     * */
    void setType(Integer type);

    /*
     * Sets the first value in array of the CryptoCompare API request
     * @param Boolean
     * @returns void
     * */
    void setFirstValueInArray(Boolean firstValueInArray);

    /*
     * Sets the conversion type of the CryptoCompare API request
     * @param ConversionType
     * @returns void
     * */
    void setConversionType(ConversionType conversionType);

    /*
     * Sets aggregation status of the CryptoCompare API request
     * @param Boolean
     * @returns void
     * */
    void setAggregated(Boolean aggregated);

    /*
     * Sets starting time of the CryptoCompare API request
     * @param Date
     * @returns void
     * */
    void setTimeFrom(Date timeFrom);
    /*
     * Sets ending time of the CryptoCompare API request
     * @param Date
     * @returns void
     * */
    void setTimeTo(Date timeTo);

    /*
     * Sets warning status of the CryptoCompare API request
     * @param Boolean
     * @returns void
     * */
    void setHasWarning(Boolean hasWarning);

    /*
     * Sets the list of the currency data of the CryptoCompare API request
     * @param List<CryptoCompareHistoricalHourlyData>
     * @returns void
     * */
    void setCryptoCompareHistoricalHourlyDataList(ArrayList<CryptoCompareHistoricalHourlyData> cryptoCompareHistoricalHourlyDataList);

}
