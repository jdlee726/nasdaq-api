package alphatory.coin.exchange.common.libs.utils.retropit.impl;

import alphatory.coin.exchange.common.libs.utils.retropit.exception.MyRetropitApiException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Generates a MyExchange API implementation based on MyRetropitApiService.
 */
@Slf4j
public class MyRetropitApiServiceGenerator {

    private final OkHttpClient.Builder httpClientBuilder;
    private final Retrofit.Builder retrofitBuilder;
    private Retrofit retrofit;

    private MyRetropitApiServiceGenerator(String API_BASE_URL) {
        httpClientBuilder = new OkHttpClient.Builder()
                .addInterceptor(new CompressionInterceptor());

        retrofitBuilder = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(JacksonConverterFactory.create(JsonMapper.builder()
                        .configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true)
                        .configure(JsonParser.Feature.IGNORE_UNDEFINED, true)
                        .build()
                        .registerModule(new JavaTimeModule())
                        .registerModule(new Jdk8Module())
                        .registerModule(new SimpleModule().addDeserializer(BigDecimal.class, new CustomBigDecimalDeserializer()))
                ));

        if (log.isDebugEnabled()) {
            var interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder.addInterceptor(interceptor);
            retrofitBuilder.client(httpClientBuilder.build());
        }

        retrofit = retrofitBuilder.build();
    }

    public static MyRetropitApiServiceGenerator
    of(String API_BASE_URL) {
        return new MyRetropitApiServiceGenerator(API_BASE_URL);
    }

    public <S> S
    createService(Class<S> serviceClass) {
        return createService(serviceClass, null);
    }

    public <S> S
    createService(Class<S> serviceClass, Interceptor interceptor) {
        if (interceptor != null) {
            if (!httpClientBuilder.interceptors().contains(interceptor)) {
                httpClientBuilder.addInterceptor(interceptor);
                retrofitBuilder.client(httpClientBuilder.build());
                retrofit = retrofitBuilder.build();
            }
        }
        return retrofit.create(serviceClass);
    }

    /**
     * Execute a REST call and block until the response is received.
     */
    private static <T> Response<T>
    executeSync(Call<T> call) {
        try {
            var response = call.execute();
            if (response.isSuccessful()) {
                return response;
            } else {
                var apiError = MyRetropitApiCallbackAdapter.getMyRetropitApiError(response);
                throw new MyRetropitApiException(apiError);
            }
        } catch (IOException e) {
            throw new MyRetropitApiException(e);
        }
    }

    public static <T> T
    executeAndGetBody(Call<T> call) {
        return executeSync(call).body();
    }


}

