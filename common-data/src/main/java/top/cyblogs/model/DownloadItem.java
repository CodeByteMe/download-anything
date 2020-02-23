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
     * 下载的类型
     */
    private DownloadType downloadType;

    /**
     * 文件的名字
     */
    private String fileName;

    /**
     * 下载的状态
     */
    private DownloadStatus status;

    /**
     * 状态文本，人为规定
     */
    private String statusFormat;

    /**
     * 总尺寸
     */
    private long totalSize;

    /**
     * 下载尺寸
     */
    private long downloadSize;

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
    private long currentSpeed;
}
