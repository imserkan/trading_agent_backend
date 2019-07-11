package sislamoglu.in.model.query;

import lombok.Data;
import sislamoglu.in.model.paging.PagedDataRequest;

import java.util.Date;

@Data
public class QueryCurrencyParams extends PagedDataRequest {

    private String currencyName;
    private Date lastModified_startDate;
    private Date lastModified_endDate;
    private Date createdAt_startDate;
    private Date createdAt_endDate;
    private String createdBy;
    private String lastModifiedBy;

}
