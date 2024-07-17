package alphatory.coin.exchange.common.libs.utils.retropit.impl;

import alphatory.coin.exchange.common.libs.utils.retropit.MyRetropitApiCallback;
import alphatory.coin.exchange.common.libs.utils.retropit.exception.MyRetropitApiException;
import alphatory.share.utils.json.JsonException;
import alphatory.share.utils.json.JsonUtil;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

/**
 * MyExchange API WebSocket listener.
 */
public class MyRetropitApiWebSocketListener<T> extends WebSocketListener {

    private final MyRetropitApiCallback<T> callback;

    private final Class<T> eventClass;

    public MyRetropitApiWebSocketListener(MyRetropitApiCallback<T> callback, Class<T> eventClass) {
        this.callback = callback;
        this.eventClass = eventClass;
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        try {
            T event = JsonUtil.fromJson(text, eventClass);
            callback.onResponse(event);
        } catch (JsonException e) {
            throw new MyRetropitApiException(e);
        }
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        throw new MyRetropitApiException(t);
    }
}
