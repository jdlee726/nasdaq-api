package alphatory.coin.exchange.nasdaq.manager;

import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.internal.http.RealResponseBody;
import okio.GzipSource;
import okio.Okio;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

class GzipResponseInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response
    intercept(Chain chain) throws IOException {
        var originalRequest = chain.request();

        var newRequest = originalRequest.newBuilder()
                // .header("Host","api.nasdaq.com")
                .header("Accept", "*/*")
                // .header("Accept-Encoding", "deflate, gzip, br, zstd")
                .header("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:128.0) Gecko/20100101 Firefox/128.0")
                // .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36")
                .header("Accept-Language", "en-US,en;q=0.5'")
                .build();

        var response = chain.proceed(newRequest);
        return gunzip(response);
    }

    // copied from okhttp3.internal.http.HttpEngine (because is private)
    private Response
    gunzip(final Response response) {
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
