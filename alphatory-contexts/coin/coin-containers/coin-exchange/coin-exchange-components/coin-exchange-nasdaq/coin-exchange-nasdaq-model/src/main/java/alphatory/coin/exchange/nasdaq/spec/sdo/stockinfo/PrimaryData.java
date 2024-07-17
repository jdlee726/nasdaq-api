package alphatory.coin.exchange.nasdaq.spec.sdo.stockinfo;

public record PrimaryData(
        String lastSalePrice,
        String netChange,
        String percentageChange,
        String deltaIndicator,
        String lastTradeTimestamp,
        Boolean isRealTime,
        String bidPrice,
        String askPrice,
        String bidSize,
        String askSize,
        String volume
) {
}
