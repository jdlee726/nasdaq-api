package alphatory.coin.exchange.common.libs.utils.retropit;

import okhttp3.RequestBody;
import okio.Buffer;

import java.io.IOException;

public class CommonUtil {

    /**
     * Extracts the request body into a String.
     *
     * @return request body as a string
     */
    public static String bodyToString(RequestBody request) {
        if (request == null) {
            return null;
        }
        try {
            final var copy = request;
            final var buffer = new Buffer();
            copy.writeTo(buffer);
            return buffer.readUtf8();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
