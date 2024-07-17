package alphatory.coin.exchange.nasdaq.spec.sdo.stockinfo;

import java.util.List;

public record NasdaqStockInfo(
        String symbol,
        String companyName,
        String stockType,
        String exchange,
        Boolean isNasdaqListed,
        Boolean isNasdaq100,
        Boolean isHeld,
        PrimaryData primaryData,
        Object secondaryData,
        String marketStatus,
        String assetClass,
        KeyStats keyStats,
        List<Notification> notifications
) {
}
