package sislamoglu.in.model.cryptocompare;

import java.util.Date;

public interface ICryptoCompareHistoricalHourlyData {

    /*
     * Returns the high price of the currency
     * @param none
     * @returns Double
     * */
    Double getHigh();

    /*
     * Returns the close price of the currency
     * @param none
     * @returns close
     * */
    Double getClose();

    /*
     * Returns the low price of the currency
     * @param none
     * @returns Double
     * */
    Double getLow();

    /*
     * Returns the open price of the currency
     * @param none
     * @returns Double
     * */
    Double getOpen();

    /*
     * Returns the current volume of the currency in that time interval
     * @param none
     * @returns Double
     * */
    Double getVolumeTo();

    /*
     * Returns the previous volume of the currency in that time interval
     * @param none
     * @returns Double
     * */
    Double getVolumeFrom();

    /*
     * Returns the time of the currency
     * @param none
     * @returns Date
     * */
    Date getTime();


    /*
     * Sets the high price of the currency
     * @param Double
     * @returns void
     * */
    void setHigh(Double high);

    /*
     * Sets the close price of the currency
     * @param Double
     * @returns void
     * */
    void setClose(Double close);

    /*
     * Sets the low price of the currency
     * @param Double
     * @returns void
     * */
    void setLow(Double low);

    /*
     * Sets the open price of the currency
     * @param Double
     * @returns void
     * */
    void setOpen(Double open);

    /*
     * Sets the current volume of the currency in that time interval
     * @param Double
     * @returns void
     * */
    void setVolumeTo(Double volumeTo);

    /*
     * Sets the previous volume of the currency in that time interval
     * @param Double
     * @returns void
     * */
    void setVolumeFrom(Double volumeFrom);

    /*
     * Sets the time of the currency
     * @param Date
     * @returns void
     * */
    void setTime(Date time);
}
