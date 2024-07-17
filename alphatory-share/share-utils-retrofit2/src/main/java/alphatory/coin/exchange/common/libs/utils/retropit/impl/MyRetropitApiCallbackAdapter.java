package alphatory.coin.exchange.common.libs.utils.retropit.impl;

import alphatory.coin.exchange.common.libs.utils.retropit.MyRetropitApiCallback;
import alphatory.coin.exchange.common.libs.utils.retropit.MyRetropitApiError;
import alphatory.coin.exchange.common.libs.utils.retropit.exception.MyRetropitApiException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.Optional;

/**
 * An adapter/wrapper which transforms a Callback from Retrofit into a MyRetropitApiCallback which is exposed to the client.
 */
public class MyRetropitApiCallbackAdapter<T> implements Callback<T> {

    private final MyRetropitApiCallback<T> callback;

    public MyRetropitApiCallbackAdapter(MyRetropitApiCallback<T> callback) {
        this.callback = callback;
    }

    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            callback.onResponse(response.body());
        } else {
            if (response.code() == 504) {
                // HTTP 504 return code is used when the API successfully sent the message but not get a response within the timeout period.
                // It is important to NOT treat this as a failure; the execution status is UNKNOWN and could have been a success.
                return;
            }
            try {
                var apiError = getMyRetropitApiError(response);
                throw new MyRetropitApiException(apiError);
            } catch (IOException e) {
                throw new MyRetropitApiException(e);
            }
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable throwable) {
        throw new MyRetropitApiException(throwable);
    }


//    private static Retrofit.Builder builder =
//            new Retrofit.Builder()
//                    .baseUrl(MyRetropitApiConstants.API_BASE_URL)
//                    .addConverterFactory(JacksonConverterFactory.create());
//    private static Retrofit retrofit = builder.build();

    /**
     * Extracts and converts the response error body into an object.
     */
    public static MyRetropitApiError getMyRetropitApiError(Response<?> response) throws IOException, MyRetropitApiException {
        var myRetropitApiError = new MyRetropitApiError();
        myRetropitApiError.setCode(response.code());
        myRetropitApiError.setMsg(Optional.ofNullable(response.errorBody()).map(o -> {
            try {
                return o.string();
            } catch (IOException e) {
                return e.getMessage();
            }
        }).orElse(""));
        return myRetropitApiError;

//        return (MyRetropitApiError) retrofit.responseBodyConverter(MyRetropitApiError.class, new Annotation[0])
//                .convert(response.errorBody());

    }
}
