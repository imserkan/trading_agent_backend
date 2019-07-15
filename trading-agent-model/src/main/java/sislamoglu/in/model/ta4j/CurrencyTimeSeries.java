package sislamoglu.in.model.ta4j;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.kaiso.relmongo.annotation.FetchType;
import io.github.kaiso.relmongo.annotation.OneToOne;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.ta4j.core.TimeSeries;

import javax.persistence.Column;


@Document
@Data
public class CurrencyTimeSeries implements ICurrencyTimeSeries, Cloneable{

    @Id
    @Column(name = "currency_time_series_id")
    private String id;
    private String currencyIdentifier;
    private TimeSeries timeSeries;

//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "currency_id", nullable = false)
//    @JsonIgnore
//    private Currency currency;

}
