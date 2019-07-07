package sislamoglu.in.model.cryptocompare;

import lombok.Data;

@Data
public class ConversionType implements IConversionType{

    private String conversionSymbol;
    private String type;
}
