package top.cyblogs.exception;

public class UrlNotSupportException extends RuntimeException {

    public UrlNotSupportException() {
        super();
    }

    public UrlNotSupportException(String message) {
        super(message);
    }

    public UrlNotSupportException(String message, Throwable cause) {
        super(message, cause);
    }

    public UrlNotSupportException(Throwable cause) {
        super(cause);
    }

    protected UrlNotSupportException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
