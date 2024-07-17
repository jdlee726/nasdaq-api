package alphatory.coin.exchange.nasdaq.spec.sdo.stockinfo;

import java.util.List;

public record Notification(
        String headline,
        List<EventType> eventTypes
) {
}
