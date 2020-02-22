package top.cyblogs.ffmpeg.listener;

/**
 * FFMpeg的回调
 *
 * @author CY
 */
public abstract class FFMpegListener {

    /**
     * 开始执行
     */
    public void start() {
    }

    /**
     * 进度信息 TODO
     */
    public void progress(double current, double total) {
    }

    /**
     * 执行完成
     */
    public void over() {
    }
}