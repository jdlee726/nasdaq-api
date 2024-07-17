package alphatory.coin.exchange.nasdaq.manager.sdo;

import alphatory.coin.exchange.nasdaq.spec.sdo.optioninfo.Row;
import lombok.Builder;

import java.util.LinkedHashMap;
import java.util.List;

@Builder
public record NasdaqOptionChain(
        Integer totalRecord,
        String lastTrade,
        LinkedHashMap<String, List<Row>> linkedHashMap
) {
}
