package top.cyblogs.listener;

import top.cyblogs.model.DownloadItem;

import java.io.File;
import java.util.List;

/**
 * 合并视频监听
 *
 * @author CY
 */
@FunctionalInterface
public interface MergeVideoListener {

    /**
     * 开始合并视频
     *
     * @param videoList      视频列表
     * @param targetFile     目标文件
     * @param downloadStatus 下载状态管理
     */
    void startMerge(List<File> videoList, File targetFile, DownloadItem downloadStatus);
}
