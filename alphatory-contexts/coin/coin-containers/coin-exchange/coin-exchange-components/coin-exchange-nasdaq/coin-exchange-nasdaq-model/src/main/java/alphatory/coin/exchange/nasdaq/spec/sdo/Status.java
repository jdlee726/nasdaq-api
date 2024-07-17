package alphatory.coin.exchange.nasdaq.spec.sdo;

public record Status(
        Integer rCode,
        Object bCodeMessage,
        Object developerMessage
) {
}
