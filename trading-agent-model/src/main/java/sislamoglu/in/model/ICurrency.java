package sislamoglu.in.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.ta4j.core.TimeSeries;
import sislamoglu.in.model.ta4j.CurrencyTimeSeries;

import java.util.List;

public interface ICurrency {

    /*
    * Returns the name of the currency
    * @param none
    * @returns String
    * */
    String getName();

    /*
     * Returns the size of the currency
     * @param none
     * @returns int
     * */
    int getDataSize();

    /*
     * Returns the data of the currency
     * @param none
     * @returns CurrencyTimeSeries
     * */
    List<CurrencyInformation> getCurrencyInformationList();

    /*
     * Returns the currency time series
     * @param none
     * @returns CurrencyTimeSeries
     * */
//    CurrencyTimeSeries getCurrencyTimeSeries();

    /*
     * Sets the name of the currency
     * @param String
     * @returns void
     * */
    void setName(String name);

    /*
     * Sets the size of the currency
     * @param String
     * @returns void
     * */
    void setDataSize(int size);

    /*
     * Sets the data of the currency
     * @param CurrencyTimeSeries
     * @returns void
     * */
    void setCurrencyInformationList(List<CurrencyInformation> currencyInformationList);

    /*
     * Sets the currency time series
     * @param CurrencyTimeSeries
     * @returns void
     * */
//    void setCurrencyTimeSeries(CurrencyTimeSeries currencyTimeSeries);
}
