package top.cyblogs.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DownloadInfo {

    private String downloadType;

    private String fileName;

    private String status;

    private String statusFormat;

    private long totalSize;

    private long downloadSize;

    private String targetPath;

    private String source;

    private long currentSpeed;

    // yayayayaya 皮一下，代码写的已经乱起八糟了，希望有个大神重整一下代码结构和数据结构，能重新规划一下前端更好，要规范的那种...
}
