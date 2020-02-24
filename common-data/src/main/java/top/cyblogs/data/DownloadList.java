package top.cyblogs.data;

import top.cyblogs.model.DownloadItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 存放下载的数据
 *
 * @author CY
 */
public class DownloadList {

    /**
     * 用来存放下载列表
     */
    public transient static List<DownloadItem> list = new ArrayList<>();
}
