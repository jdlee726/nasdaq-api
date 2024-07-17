package alphatory.coin.exchange.common.libs.utils.retropit.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.math.BigDecimal;

public class CustomBigDecimalDeserializer extends JsonDeserializer<BigDecimal> {

    @Override
    public BigDecimal deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        var value = p.getText();
        if (value != null) {
            // 콤마 제거
            value = value.replace(",", "");
            try {
                return new BigDecimal(value);
            } catch (NumberFormatException e) {
                throw new IOException("Unable to parse BigDecimal", e);
            }
        }
        return null;
    }
}
