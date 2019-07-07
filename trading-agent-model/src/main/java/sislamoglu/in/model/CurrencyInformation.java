package sislamoglu.in.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class CurrencyInformation implements ICurrencyInformation{

    private Double closePrice;
    private Double highPrice;
    private Double lowPrice;
    private Double openPrice;
    private Double volumeFrom;
    private Double volumeTo;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss.SSSZ")
    private Date timeInterval;

}
