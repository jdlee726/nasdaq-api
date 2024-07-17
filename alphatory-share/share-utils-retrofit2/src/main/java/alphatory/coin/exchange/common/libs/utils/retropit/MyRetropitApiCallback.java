package alphatory.coin.exchange.common.libs.utils.retropit;

import alphatory.coin.exchange.common.libs.utils.retropit.exception.MyRetropitApiException;

/**
 * MyRetropitApiCallback is a functional interface used together with the MyRetropitApiAsyncClient to provide a non-blocking REST client.
 *
 * @param <T> the return type from the callback
 */
public interface MyRetropitApiCallback<T> {

    /**
     * Called whenever a response comes back from the MyExchange API.
     *
     * @param response the expected response object
     * @throws MyRetropitApiException if it is not possible to obtain the expected response object (e.g. incorrect API-KEY).
     */
    void onResponse(T response) throws MyRetropitApiException;
}
