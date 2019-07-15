package sislamoglu.in.util;

import org.springframework.stereotype.Service;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseTimeSeries;
import org.ta4j.core.num.PrecisionNum;
import sislamoglu.in.model.CurrencyInformation;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class TAServiceConvertToTimeSeries {

    public BaseTimeSeries convertCurrencyInformationListToTimeSeries(String currencyName, List<CurrencyInformation> currencyInformationList){
        BaseTimeSeries baseTimeSeries = new BaseTimeSeries(currencyName);
        for (CurrencyInformation currencyInformation: currencyInformationList){
            BaseBar baseBar = new BaseBar(ZonedDateTime.ofInstant(currencyInformation.getTimeInterval().toInstant(), ZoneId.systemDefault()),
                    currencyInformation.getOpenPrice(),
                    currencyInformation.getHighPrice(),
                    currencyInformation.getLowPrice(),
                    currencyInformation.getClosePrice(),
                    currencyInformation.getVolumeFrom(), PrecisionNum::valueOf);
            baseTimeSeries.addBar(baseBar);
        }
        return baseTimeSeries;
    }
}
