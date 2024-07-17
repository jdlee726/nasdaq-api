package alphatory.coin.exchange.nasdaq.spec.sdo.stockinfo;

import alphatory.coin.exchange.nasdaq.spec.sdo.LabelAndValue;

public record EventType(
        String message,
        String eventName,
        LabelAndValue url,
        String id
) {
}
