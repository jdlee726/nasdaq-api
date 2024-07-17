package alphatory.coin.exchange.common.libs.utils.retropit.impl;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.http.RealResponseBody;
import okio.BufferedSink;
import okio.GzipSink;
import okio.GzipSource;
import okio.Okio;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

class CompressionInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response
    intercept(Chain chain) throws IOException {
        var originalRequest = chain.request();
        if (originalRequest.body() == null || originalRequest.header("Content-Encoding") != null) {
            return chain.proceed(originalRequest);
        }

        var compressionRequest = originalRequest.newBuilder()
                // This interceptor compresses the HTTP request body. Many webservers can't handle this!
//                .header("Content-Encoding", "gzip")
//                .method(originalRequest.method(), gzip(originalRequest.body()))
                .header("Accept-Encoding", "gzip, deflate, br, zstd")
                .build();
        var response = chain.proceed(compressionRequest);
        return unzip(response);
    }

    private RequestBody
    gzip(final RequestBody body) {
        return new RequestBody() {
            @Override
            public MediaType contentType() {
                return body.contentType();
            }

            @Override
            public long contentLength() {
                return -1; // We don't know the compressed length in advance!
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                var gzipSink = Okio.buffer(new GzipSink(sink));
                body.writeTo(gzipSink);
                gzipSink.close();
            }
        };
    }

    // copied from okhttp3.internal.http.HttpEngine (because is private)
    private Response
    unzip(final Response response) {
        if (response.body() == null) {
            return response;
        }

        //check if we have gzip response
        //this is used to decompress gzipped responses
        var contentEncoding = response.headers().get("Content-Encoding");
        if (contentEncoding != null && contentEncoding.equals("gzip")) {
            var responseBody = response.body();
            var strippedHeaders = response.headers().newBuilder().build();
            return response.newBuilder()
                    .headers(strippedHeaders)
                    .body(new RealResponseBody(
                            responseBody.contentType().toString(),
                            responseBody.contentLength(),
                            Okio.buffer(new GzipSource(responseBody.source()))
                    ))
                    .build();
        } else {
            return response;
        }
    }
}
