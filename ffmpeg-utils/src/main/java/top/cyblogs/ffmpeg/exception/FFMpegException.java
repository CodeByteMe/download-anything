package top.cyblogs.ffmpeg.exception;

/**
 * FFMpeg异常
 *
 * @author CY
 */
public class FFMpegException extends RuntimeException {

    public FFMpegException() {
        super();
    }

    public FFMpegException(String message) {
        super(message);
    }

    public FFMpegException(String message, Throwable cause) {
        super(message, cause);
    }

    public FFMpegException(Throwable cause) {
        super(cause);
    }

    protected FFMpegException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
