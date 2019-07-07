package sislamoglu.in.model;

import java.util.List;

public interface ICurrencyParameters {

    /*
     * Returns the cryptocurrency symbol of interest
     * [Min_length-1][Max_length-10]
     * @param none
     * @returns String
     * */
    List<String> getFsym();

    /*
     * Returns the cryprocurrency symbol to convert into
     * [Min_length-1][Max_length-10]
     * @param none
     * @returns String
     * */
    List<String> getTsym();

    /*
     * Returns name of the exchange to obtain data from
     * [Min_length-2][Max_length-30]
     * [Default-CCCAGG]
     * @param none
     * @returns String
     * */
    String getE();

    /*
     * Returns time period to aggregate the data over
     * [Min-1][Max-30]
     * [Default-1]
     * @param none
     * @returns int
     * */
    int getAggregate();

    /*
     * True by default, only used when the aggregate param is also in use. If false it will aggregate based on the
     * current time.
     * If the param is false and the time you make the call is 1pm-2pm, with aggregate 2, it will create the time
     * slots: ...9am, 11am, 1pm.
     * If the param is false and the time you make the call is 2pm - 3pm, with aggregate 2, it will create the time
     * slots: ...10am, 12am, 2pm.
     * If the param is true (default) and the time you make the call is 1pm-2pm, with aggregate 2, it will create the
     * time slots: ...8am, 10am, 12am.
     * If the param is true (default) and the time you make the call is 2pm-3pm, with aggregate 2, it will create the
     * time slots: ...10am, 12am, 2pm.
     * [Default-true]
     * @param none
     * @returns Boolean
     * */
    Boolean getAggregatePredictableTimePeriods();

    /*
     * The number of data points to return
     * [Min-1][Max-2000]
     * [Default-168]
     * @param none
     * @returns int
     * */
    int getLimit();

    /*
     * Returns the historical data before that timestamp. If you want to get all the available historical data, you
     * can use limit=2000 and keep going back in time using the toTs param. You can then keep requesting batches using:
     * &limit=2000&toTs={the earliest timestamp received}
     * [Min-1][Max-2000]
     * [Default-168]
     * @param none
     * @returns int
     * */
    int getToTs();

    /*
     * The name of your application (we recommend you send it)
     * [Min_length-1][Max_length-2000]
     * [Default-Not_Available]
     * @param none
     * @returns String
     * */
    String getExtraParams();

    /*
     * If set to true, the server will sign the requests (by default we don't sign them), this is useful for usage in
     * smart contracts
     * [Default-false]
     * @param none
     * @returns Boolean
     * */
    Boolean getSign();

    /*
     * Sets the cryptocurrency symbol of interest
     * @param List<String>
     * @returns void
     * */
    void setFsym(List<String> fsym);

    /*
     * Sets the cryptocurrency symbol to convert into
     * @param List<String>
     * @returns void
     * */
    void setTsym(List<String> tsym);

    /*
     * Sets the exchange to obtain data
     * @param String
     * @returns void
     * */
    void setE(String e);

    /*
     * Sets the time period to aggregate the data over
     * @param int
     * @returns void
     * */
    void setAggregate(int aggregate);

    /*
     * Specific for aggregate
     * @param Boolean
     * @returns void
     * */
    void setAggregatePredictableTimePeriods(Boolean aggregatePredictableTimePeriods);

    /*
     * Sets the number of datapoints to return
     * @param int
     * @returns void
     * */
    void setLimit(int limit);

    /*
     * Sets the historical data before that timestamp
     * @param int
     * @returns void
     * */
    void setToTs(int toTs);


    /*
     * Sets the name of your application
     * @param String
     * @returns void
     * */
    void setExtraParams(String extraParams);

    /*
     * Sets whether the server signs the requests or not
     * @param Boolean
     * @returns void
     * */
    void setSign(Boolean sign);
}
