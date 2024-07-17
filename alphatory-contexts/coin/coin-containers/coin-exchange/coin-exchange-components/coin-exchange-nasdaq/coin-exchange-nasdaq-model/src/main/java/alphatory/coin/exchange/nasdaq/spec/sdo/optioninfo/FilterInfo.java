package alphatory.coin.exchange.nasdaq.spec.sdo.optioninfo;

import alphatory.coin.exchange.nasdaq.spec.sdo.LabelAndValue;

import java.util.List;

public record FilterInfo(
        String filtertitle,
        String filtertype,
        String filterid,
        List<LabelAndValue> filter
) {
}
