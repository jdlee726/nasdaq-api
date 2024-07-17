package alphatory.coin.exchange.common.libs.utils.retropit.exception;

import alphatory.coin.exchange.common.libs.utils.retropit.MyRetropitApiError;

/**
 * An exception which can occur while invoking methods of the MyExchange API.
 */
public class MyRetropitApiException extends RuntimeException {

    /**
     * Error response object returned by MyExchange API.
     */
    private MyRetropitApiError error;

    /**
     * Instantiates a new api exception.
     *
     * @param error an error response object
     */
    public MyRetropitApiException(MyRetropitApiError error) {
        this.error = error;
    }

    /**
     * Instantiates a new api exception.
     */
    public MyRetropitApiException() {
        super();
    }

    /**
     * Instantiates a new api exception.
     *
     * @param message the message
     */
    public MyRetropitApiException(String message) {
        super(message);
    }

    /**
     * Instantiates a new exception.
     *
     * @param cause the cause
     */
    public MyRetropitApiException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new api exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public MyRetropitApiException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @return the response error object from MyExchange API, or null if no response object was returned (e.g. server returned 500).
     */
    public MyRetropitApiError getError() {
        return error;
    }

    @Override
    public String getMessage() {
        if (error != null) {
            return error.getMsg();
        }
        return super.getMessage();
    }
}
