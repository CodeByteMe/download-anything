package top.cyblogs.exception;

/**
 * 网络异常
 *
 * @author CY
 */
public class InternetException extends RuntimeException {

    public InternetException() {
        super();
    }

    public InternetException(String message) {
        super(message);
    }

    public InternetException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternetException(Throwable cause) {
        super(cause);
    }

    protected InternetException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
