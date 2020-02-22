package top.cyblogs.support;

/**
 * 下载任务的状态
 *
 * @author CY
 */
public class DownloadTaskStatus {

    /**
     * 当前下载中/种子下载中
     */
    public static final String ACTIVE = "active";

    /**
     * 用于队列中的下载；下载未开始
     */
    public static final String WAITING = "waiting";

    /**
     * 暂停下载
     */
    public static final String PAUSED = "paused";

    /**
     * 对于由于错误而停止的下载
     */
    public static final String ERROR = "error";

    /**
     * 停止和完成下载
     */
    public static final String COMPLETE = "complete";

    /**
     * 用户删除的下载
     */
    public static final String REMOVED = "removed";

    /**
     * URI被使用
     */
    public static final String USED = "used";
}
