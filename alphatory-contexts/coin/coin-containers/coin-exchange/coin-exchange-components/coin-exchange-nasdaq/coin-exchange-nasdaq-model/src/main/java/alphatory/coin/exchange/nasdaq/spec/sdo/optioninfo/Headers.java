package alphatory.coin.exchange.nasdaq.spec.sdo.optioninfo;

public record Headers(
        String expiryDate,
        String c_Last,
        String c_Change,
        String c_Bid,
        String c_Ask,
        String c_Volume,
        String c_Openinterest,
        String strike,
        String p_Last,
        String p_Change,
        String p_Bid,
        String p_Ask,
        String p_Volume,
        String p_Openinterest
) {
}
