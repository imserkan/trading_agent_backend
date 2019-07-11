package sislamoglu.in.model;

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
     * @returns String
     * */
    int getDataSize();

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
}
