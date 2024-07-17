package alphatory.share.utils.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.vavr.control.Try;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.IOException;
import java.net.URL;
import java.time.OffsetDateTime;

import static com.fasterxml.jackson.databind.DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtil {

    //region odtMapper
    public static final ObjectMapper odtMapper = new ObjectMapper()
            .registerModules(
                    new JavaTimeModule()
                            .addSerializer(OffsetDateTime.class, new ISO8601OffsetDateTimeSerializer())
                            .addDeserializer(OffsetDateTime.class, new ISO8601OffsetDateTimeDeserializer())
            )
            .disable(ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
            .disable(FAIL_ON_UNKNOWN_PROPERTIES);

    public static <T> T fromJson3(String json, Class<T> clazz) {
        try {
            return odtMapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }
    //endregion

    //region odtMapperDebug
    public static final ObjectMapper odtMapperDebug = JsonMapper.builder()
//            .enable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY)
            .build()
            .registerModules(
                    new JavaTimeModule()
                            .addSerializer(OffsetDateTime.class, new ISO8601OffsetDateTimeSerializer())
                            .addDeserializer(OffsetDateTime.class, new ISO8601OffsetDateTimeDeserializer())
            )
            .disable(ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
            .disable(FAIL_ON_UNKNOWN_PROPERTIES)
            .enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);

    public static String toJsonDebug(Object obj) {
        try {
            return odtMapperDebug.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new JsonException(e);
        }
    }
    //endregion

    //region objectMapper
    // https://cowtowncoder.medium.com/handling-non-compliant-json-with-jackson-ad0047097392
    private static final ObjectMapper objectMapper = JsonMapper.builder(
                    JsonFactory.builder()
                            .enable(JsonReadFeature.ALLOW_TRAILING_COMMA)
                            .build()
            )
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
            .build()
//            .findAndRegisterModules()
            .registerModule(new JavaTimeModule())
            .registerModule(new Jdk8Module());

    public static String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new JsonException(e);
        }
    }

    public static JsonNode toJsonNode(Object obj) {
        return objectMapper.valueToTree(obj);
    }

    public static <T> T convertValue(Object obj, Class<T> klass) {
        return objectMapper.convertValue(obj, klass);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public static <T> T fromJson(String json, TypeReference<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public static JsonNode readTree(URL url) throws IOException {
        return objectMapper.readTree(url);
    }

    public static JsonNode readTree(String content) {
        return Try.of(() -> objectMapper.readTree(content))
                .onFailure(e -> log.error(ExceptionUtils.getStackTrace(e)))
                .get();
    }

    public static JsonNode readTreeIgnore(String content) {
        return Try.of(() -> objectMapper
                        .disable(FAIL_ON_UNKNOWN_PROPERTIES)
                        .disable(FAIL_ON_EMPTY_BEANS)
                        .readTree(content))
                .onFailure(e -> log.error(ExceptionUtils.getStackTrace(e)))
                .get();
    }

    public static <T> T readValue(URL url, Class<T> valueType) throws IOException {
        return objectMapper.readValue(url, valueType);
    }
    //endregion

    //region objectMapper2
    private static final ObjectMapper objectMapper2 = new ObjectMapper()
//            .findAndRegisterModules()
            .disable(FAIL_ON_UNKNOWN_PROPERTIES);

    public static <T> T fromJson2(String json, Class<T> clazz) {
        try {
            return objectMapper2.readValue(json, clazz);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }
    //endregion

    //region objectMapperIndent
    private static final ObjectMapper objectMapperIndent = new ObjectMapper()
//            .findAndRegisterModules()
            .enable(SerializationFeature.INDENT_OUTPUT);
    //endregion

}
