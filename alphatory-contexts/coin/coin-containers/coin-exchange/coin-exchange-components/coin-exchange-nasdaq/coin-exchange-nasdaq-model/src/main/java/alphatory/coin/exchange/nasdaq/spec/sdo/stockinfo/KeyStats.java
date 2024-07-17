package alphatory.coin.exchange.nasdaq.spec.sdo.stockinfo;

import alphatory.coin.exchange.nasdaq.spec.sdo.LabelAndValue;

public record KeyStats(
        LabelAndValue fiftyTwoWeekHighLow,
        LabelAndValue dayrange
) {
}
