package sislamoglu.in.model.paging;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PagedDataRequest {

    private Page page;
    private List<Sort> sortList;
}
