package alphatory.share.utils.json;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class ISO8601OffsetDateTimeDeserializer extends InstantDeserializer<OffsetDateTime> {

    // "2020-06-19T17:00:41.040+0900"
    // "2021-01-12T13:45:54.918+09:00"
    public static final DateTimeFormatter dtf = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .append(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            .optionalStart().appendOffset("+HH:MM", "+00:00").optionalEnd()
            .optionalStart().appendOffset("+HHMM", "+0000").optionalEnd()
            .optionalStart().appendOffset("+HH", "Z").optionalEnd()
            .toFormatter();

    public ISO8601OffsetDateTimeDeserializer() {
        super(InstantDeserializer.OFFSET_DATE_TIME, dtf);
    }

    @Override
    public JsonDeserializer<OffsetDateTime> createContextual(DeserializationContext ctxt, BeanProperty property) {
        // ignore context (i.e. formatting pattern that will be used for serialization)
        return this;
    }

}
