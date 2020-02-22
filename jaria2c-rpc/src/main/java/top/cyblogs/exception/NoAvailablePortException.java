package top.cyblogs.exception;

/**
 * 没有可以使用的端口的时候抛出此异常
 *
 * @author CY
 */
public class NoAvailablePortException extends RuntimeException {

    public NoAvailablePortException() {
        super();
    }

    public NoAvailablePortException(String message) {
        super(message);
    }

    public NoAvailablePortException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoAvailablePortException(Throwable cause) {
        super(cause);
    }

    protected NoAvailablePortException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
