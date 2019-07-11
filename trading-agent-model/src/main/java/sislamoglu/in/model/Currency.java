package sislamoglu.in.model;

import lombok.Data;
import org.springframework.data.annotation.*;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Currency implements ICurrency{
    @Id
    private String id;
    private String name;
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
    private List<CurrencyInformation> currencyInformationList = new ArrayList<CurrencyInformation>();

}
