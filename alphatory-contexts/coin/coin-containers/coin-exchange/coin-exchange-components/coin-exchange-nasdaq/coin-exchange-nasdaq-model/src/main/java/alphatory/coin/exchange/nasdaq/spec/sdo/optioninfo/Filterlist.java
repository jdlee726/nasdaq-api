package alphatory.coin.exchange.nasdaq.spec.sdo.optioninfo;

public record Filterlist(
        FilterInfo excode,
        FilterInfo callput,
        FilterInfo money,
        FilterInfo type,
        FilterInfo fromdate
) {
}
