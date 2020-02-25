package top.cyblogs.model;

import lombok.Data;
import lombok.experimental.Accessors;
import top.cyblogs.model.enums.DownloadStatus;
import top.cyblogs.model.enums.DownloadType;

/**
 * 对应一个下载项
 *
 * @author CY
 */
@Data
@Accessors(chain = true)
public class DownloadItem {

    /**
     * 下载ID，目标路径的md5值
     */
    private String downloadId;

    /**
     * 下载的状态
     */
    private DownloadStatus status;

    /**
     * 下载的类型
     */
    private DownloadType downloadType;

    /**
     * 文件的名字
     */
    private String fileName;

    /**
     * 状态文本，人为规定
     */
    private String statusFormat;

    /**
     * 总尺寸
     */
    private String totalSize;

    /**
     * 目标位置
     */
    private String targetPath;

    /**
     * 下载来源
     */
    private String source;

    /**
     * 当前速度
     */
    private String currentSpeed;

    /**
     * 当前进度
     */
    private String progressFormat;

    /**
     * 当前进度
     */
    private Double progress;
}
