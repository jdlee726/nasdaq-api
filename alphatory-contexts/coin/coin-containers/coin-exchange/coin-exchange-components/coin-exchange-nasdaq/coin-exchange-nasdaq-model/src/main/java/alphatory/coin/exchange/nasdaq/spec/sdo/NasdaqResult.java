package alphatory.coin.exchange.nasdaq.spec.sdo;

public record NasdaqResult<T>(
        T data,
        Object message,
        Status status
) {
}
