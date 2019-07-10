package sislamoglu.in.model.paging;

import lombok.Data;
import lombok.NoArgsConstructor;
import sislamoglu.in.model.enums.ESortDirection;

@Data
@NoArgsConstructor
public class Sort {

    private ESortDirection sortDirection;
    private String orderBy;
}
