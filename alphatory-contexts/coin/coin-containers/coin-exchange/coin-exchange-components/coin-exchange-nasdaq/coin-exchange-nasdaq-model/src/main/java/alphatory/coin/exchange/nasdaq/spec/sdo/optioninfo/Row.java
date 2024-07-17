package alphatory.coin.exchange.nasdaq.spec.sdo.optioninfo;

public record Row(
        String expirygroup,
        String expiryDate,
        String c_Last,
        String c_Change,
        String c_Bid,
        String c_Ask,
        String c_Volume,
        String c_Openinterest,
        Boolean c_colour,
        String strike,
        String p_Last,
        String p_Change,
        String p_Bid,
        String p_Ask,
        String p_Volume,
        String p_Openinterest,
        Boolean p_colour,
        String drillDownURL
) {
}
