package sislamoglu.in.util;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sislamoglu.in.model.Currency;
import sislamoglu.in.model.CurrencyInformation;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class TAServiceCurrencyUpdater {

    Logger logger = LoggerFactory.getLogger(getClass());

    public void addAdditionalCurrencyInformation(Currency databaseCurrency, Currency updatedCurrency){
        Date lastUpdateDate_databaseCurrency = databaseCurrency
                .getCurrencyInformationList()
                .get(databaseCurrency.getCurrencyInformationList().size())
                .getTimeInterval();

        Date lastUpdateDate_updatedCurrency = updatedCurrency
                .getCurrencyInformationList()
                .get(updatedCurrency.getCurrencyInformationList().size())
                .getTimeInterval();

        List<CurrencyInformation> currencyInformationList = databaseCurrency.getCurrencyInformationList();
        List<CurrencyInformation> updatedCurrencyInformationList = updatedCurrency.getCurrencyInformationList();
        int updatePoint = Integer.MAX_VALUE;
        for (int i = 0; i < updatedCurrencyInformationList.size(); i++){
            if (updatedCurrencyInformationList.get(i).getTimeInterval().compareTo(lastUpdateDate_databaseCurrency) > 0){
                updatePoint = i;
                logger.debug("The Currenct {} is updated from {} to {}",
                        databaseCurrency.getName(),
                        lastUpdateDate_databaseCurrency,
                        lastUpdateDate_updatedCurrency);
                break;
            }
        }
        currencyInformationList.addAll(updatedCurrencyInformationList.subList(updatePoint, updatedCurrencyInformationList.size()));
    }
}
