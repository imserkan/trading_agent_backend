package sislamoglu.in.model;

import lombok.Data;

import java.util.List;

@Data
public class CurrencyParameters implements ICurrencyParameters{

    private List<String> fsym;
    private List<String> tsym;
    private String e;
    private int aggregate;
    private Boolean aggregatePredictableTimePeriods;
    private int limit;
    private int toTs;
    private String extraParams;
    private Boolean sign;

}
