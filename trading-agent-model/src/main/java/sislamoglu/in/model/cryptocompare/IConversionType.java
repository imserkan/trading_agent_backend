package sislamoglu.in.model.cryptocompare;

public interface IConversionType {

    /*
     * Returns the conversion symbol of the currency
     * @param none
     * @returns String
     * */
    String getConversionSymbol();

    /*
     * Returns the conversion type of the currency
     * @param none
     * @returns String
     * */
    String getType();

    /*
     * Sets the conversion symbol of the currency
     * @param String
     * @returns void
     * */
    void setConversionSymbol(String conversionSymbol);

    /*
     * Sets the conversion type of the currency
     * @param String
     * @returns void
     * */
    void setType(String type);
}
