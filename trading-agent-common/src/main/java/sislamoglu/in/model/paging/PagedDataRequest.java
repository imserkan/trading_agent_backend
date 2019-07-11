package sislamoglu.in.model.paging;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PagedDataRequest {

    private Page page;
    private Sort sort;
}
