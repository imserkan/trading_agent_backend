package sislamoglu.in.model.paging;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PagedDataResponse<T> {

    private List<T> data;
    private Long totalCount;
    private PagedDataRequest pagedDataRequest;
}
