package top.cyblogs.data;

import top.cyblogs.model.DownloadInfo;

import java.util.List;

/**
 * 下载状态
 */
public class DownloadStatus {

    /**
     * 下载中
     */
    public static List<DownloadInfo> downloading;

    /**
     * 已完成
     */
    public static List<DownloadInfo> finished;

    /**
     * 已删除
     */
    public static List<DownloadInfo> removed;
}
