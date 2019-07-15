package sislamoglu.in.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.kaiso.relmongo.annotation.CascadeType;
import io.github.kaiso.relmongo.annotation.FetchType;
import io.github.kaiso.relmongo.annotation.OneToOne;
import lombok.Data;
import org.springframework.data.annotation.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import sislamoglu.in.model.ta4j.CurrencyTimeSeries;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

@Document
@Data
public class Currency implements ICurrency, Cloneable{

    @Id
    @Column(name = "currency_id")
    private String id;
    private String name;
    private String currencyIdentifier;
    private int dataSize;
    @CreatedBy
    private String createdBy;
    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss.SSSZ")
    private Date createdAt;
    @LastModifiedBy
    private String lastModifiedBy;
    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss.SSSZ")
    private Date lastModifiedAt;
    private List<CurrencyInformation> currencyInformationList;

//    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "currency", orphanRemoval = true)
//    @JsonIgnore
//    private CurrencyTimeSeries currencyTimeSeries;

}
