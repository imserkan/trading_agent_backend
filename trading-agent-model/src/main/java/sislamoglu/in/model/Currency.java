package sislamoglu.in.model;

import lombok.Data;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss.SSSZ")
    private Date createdAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss.SSSZ")
    private Date lastModifiedAt;

    private List<CurrencyInformation> currencyInformationList = new ArrayList<CurrencyInformation>();

    @PrePersist
    protected void prePersist() {
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void preUpdate(){
        this.lastModifiedAt = new Date();
    }

}
