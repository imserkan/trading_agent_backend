
package sislamoglu.in.util;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ta4j.core.Bar;
import sislamoglu.in.model.Currency;
import sislamoglu.in.model.CurrencyInformation;
import sislamoglu.in.model.ta4j.CurrencyTimeSeries;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class TAServiceCurrencyUpdater {

    Logger logger = LoggerFactory.getLogger(getClass());

    public void addAdditionalCurrencyInformation(Currency databaseCurrency, Currency updatedCurrency){
        Date lastUpdateDate_databaseCurrency = databaseCurrency
                .getCurrencyInformationList()
                .get(databaseCurrency.getCurrencyInformationList().size()-1)
                .getTimeInterval();

        Date lastUpdateDate_updatedCurrency = updatedCurrency
                .getCurrencyInformationList()
                .get(updatedCurrency.getCurrencyInformationList().size()-1)
                .getTimeInterval();

        List<CurrencyInformation> currencyInformationList = databaseCurrency.getCurrencyInformationList();
        List<CurrencyInformation> updatedCurrencyInformationList = updatedCurrency.getCurrencyInformationList();
        int updatePoint = databaseCurrency.getCurrencyInformationList().size();
        for (int i = 0; i < updatedCurrencyInformationList.size(); i++){
            if (updatedCurrencyInformationList.get(i).getTimeInterval().compareTo(lastUpdateDate_databaseCurrency) > 0){
                updatePoint = i;
                logger.debug("The Currency {} is updated from {} to {}",
                        databaseCurrency.getName(),
                        lastUpdateDate_databaseCurrency,
                        lastUpdateDate_updatedCurrency);
                break;
            }
        }
        currencyInformationList.addAll(updatedCurrencyInformationList.subList(updatePoint, updatedCurrencyInformationList.size()));
    }

    public void addAdditionalTimeSeriesInformation(CurrencyTimeSeries databaseCurrencyTimeSeries, CurrencyTimeSeries updatedCurrencyTimeSeries){
        Date lastUpdateDate_databaseCurrencyTimeSeries = Date.from(databaseCurrencyTimeSeries
                .getTimeSeries()
                .getLastBar()
                .getEndTime()
                .toInstant()
        );

        Date lastUpdateDate_updateCurrencyTimeSeries = Date.from(updatedCurrencyTimeSeries
                .getTimeSeries()
                .getLastBar()
                .getEndTime()
                .toInstant()
        );

        List<Bar> currencyInformationList = databaseCurrencyTimeSeries.getTimeSeries().getBarData();
        List<Bar> updatedCurrencyInformationList = updatedCurrencyTimeSeries.getTimeSeries().getBarData();
        int updatePoint = databaseCurrencyTimeSeries.getTimeSeries().getBarData().size();
        for (int i = 0; i < updatedCurrencyInformationList.size(); i++){
            if (Date.from(updatedCurrencyInformationList.get(i).getEndTime().toInstant()).compareTo(lastUpdateDate_databaseCurrencyTimeSeries) > 0){
                updatePoint = i;
                logger.debug("The Currency {} is updated from {} to {}",
                        databaseCurrencyTimeSeries.getCurrencyIdentifier(),
                        lastUpdateDate_databaseCurrencyTimeSeries,
                        lastUpdateDate_updateCurrencyTimeSeries);
                break;
            }
        }
        currencyInformationList.addAll(updatedCurrencyInformationList.subList(updatePoint, updatedCurrencyInformationList.size()));
    }
}