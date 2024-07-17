package alphatory.coin.exchange.nasdaq.spec.sdo.optioninfo;

import java.util.List;

public record TableInfo(
        Object asOf,
        Headers headers,
        List<Row> rows
) {
}
