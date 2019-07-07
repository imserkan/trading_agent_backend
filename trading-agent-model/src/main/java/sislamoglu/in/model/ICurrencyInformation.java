package sislamoglu.in.model;

import java.util.Date;

public interface ICurrencyInformation{

    /*
     * Returns the close price of the specific currency
     * @param none
     * @returns Double
     * */
    Double getClosePrice();

    /*
     * Returns the high price of the specific currency
     * @param none
     * @returns Double
     * */
    Double getHighPrice();

    /*
     * Returns the low price of the specific currency
     * @param none
     * @returns Double
     * */
    Double getLowPrice();

    /*
     * Returns the open price of the specific currency
     * @param none
     * @returns Double
     * */
    Double getOpenPrice();


    /*
     * Returns the opening volume of the specific currency
     * @param none
     * @returns Double
     * */
    Double getVolumeFrom();

    /*
     * Returns the closing volume of the specific currency
     * @param none
     * @returns Double
     * */
    Double getVolumeTo();

    /*
     * Returns the time interval of the specific currency
     * @param none
     * @returns Date
     * */
    Date getTimeInterval();

    /*
     * Sets the close price of the specific currency
     * @param Double
     * @returns void
     * */
    void setClosePrice(Double closePrice);

    /*
     * Sets the high price of the specific currency
     * @param Double
     * @returns void
     * */
    void setHighPrice(Double highPrice);

    /*
     * Sets the low price of the specific currency
     * @param Double
     * @returns void
     * */
    void setLowPrice(Double lowPrice);

    /*
     * Sets the open price of the specific currency
     * @param Double
     * @returns void
     * */
    void setOpenPrice(Double openPrice);

    /*
     * Sets the closing volume of the specific currency
     * @param Double
     * @returns void
     * */
    void setVolumeFrom(Double volumeFrom);

    /*
     * Sets the opening volume of the specific currency
     * @param Double
     * @returns void
     * */
    void setVolumeTo(Double volumeTo);

    /*
     * Sets the time interval of the specific currency
     * @param Date
     * @returns void
     * */
    void setTimeInterval(Date timeInterval);
}
