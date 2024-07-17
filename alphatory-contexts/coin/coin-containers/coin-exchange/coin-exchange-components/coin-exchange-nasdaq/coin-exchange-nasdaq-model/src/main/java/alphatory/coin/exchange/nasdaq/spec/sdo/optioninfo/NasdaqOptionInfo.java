package alphatory.coin.exchange.nasdaq.spec.sdo.optioninfo;

public record NasdaqOptionInfo(
        Integer totalRecord,
        String lastTrade,
        TableInfo table,
        Filterlist filterlist
) {
}
