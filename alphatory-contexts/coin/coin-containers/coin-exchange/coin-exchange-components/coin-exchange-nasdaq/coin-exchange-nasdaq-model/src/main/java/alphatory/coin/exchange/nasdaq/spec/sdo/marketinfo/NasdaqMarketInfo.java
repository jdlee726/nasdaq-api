package alphatory.coin.exchange.nasdaq.spec.sdo.marketinfo;

public record NasdaqMarketInfo(
        String country,
        String marketIndicator,
        String uiMarketIndicator,
        String marketCountDown,
        String preMarketOpeningTime,
        String preMarketClosingTime,
        String marketOpeningTime,
        String marketClosingTime,
        String afterHoursMarketOpeningTime,
        String afterHoursMarketClosingTime,
        String previousTradeDate,
        String nextTradeDate,
        Boolean isBusinessDay,
        String mrktStatus,
        String mrktCountDown
) {
}
