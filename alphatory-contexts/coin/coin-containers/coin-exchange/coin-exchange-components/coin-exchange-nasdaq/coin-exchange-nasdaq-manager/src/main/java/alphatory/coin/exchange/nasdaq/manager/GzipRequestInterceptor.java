package alphatory.coin.exchange.nasdaq.manager;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

class GzipRequestInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response
    intercept(Chain chain) throws IOException {
        var originalRequest = chain.request();

        // do not set content encoding in negative use case
        if (originalRequest.body() == null || originalRequest.header("Content-Encoding") != null) {
            return chain.proceed(originalRequest);
        }
        var newRequest = originalRequest.newBuilder()
                .header("Content-Encoding", "gzip")
                .method(originalRequest.method(), gzip(originalRequest.body()))
                .build();

        var response = chain.proceed(newRequest);
        return response;
    }

    private RequestBody
    gzip(final RequestBody body) {
        return new RequestBody() {
            @Override
            public MediaType
            contentType() {
                return body.contentType();
            }

            @Override
            public long
            contentLength() {
                return -1; // We don't know the compressed length in advance!
            }

            @Override
            public void
            writeTo(@NotNull BufferedSink sink) throws IOException {
                var gzipSink = Okio.buffer(new GzipSink(sink));
                body.writeTo(gzipSink);
                gzipSink.close();
            }
        };
    }

}
