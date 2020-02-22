package top.cyblogs.data;

import java.io.Serializable;

/**
 * 应用程序初始化设置数据
 */
public class SettingsData implements Serializable {

    private static final long serialVersionUID = -8296332170304591062L;

    /**
     * 是否要下载视频的弹幕
     */
    public static boolean isDownloadDanmu = true;

    /**
     * 是否要下载音乐的歌词
     */
    public static boolean isDownloadLrc = true;

    /**
     * 文件默认保存的路径
     */
    public static String path = BaseData.path;

//    /**
//     * 初始化程序设置 OK
//     */
//    public static void initializeSettings() {
//        // 涉及硬盘的读
//        new Thread(() -> {
//            SettingsData baseSettingsData = SerializationUtil.deserialize(settingsFilePath, SettingsData.class);
//            if (baseSettingsData == null) {
//                return;
//            }
//            BaseData.threadNum = baseSettingsData.threadNum == 0 ? BaseData.threadNum : baseSettingsData.threadNum;
//            BaseData.path = baseSettingsData.path == null ? BaseData.path : baseSettingsData.path;
//            BaseData.currentQuality = baseSettingsData.currentQuality == null ? BaseData.currentQuality : baseSettingsData.currentQuality;
//            BaseData.isDownloadLrc = baseSettingsData.isDownloadLrc;
//            BaseData.isDownloadDanmu = baseSettingsData.isDownloadDanmu;
//        }).start();
//    }
//
//    /**
//     * 刷新设置 OK
//     */
//    public static void refreshSettings() {
//        // 涉及硬盘的写
//        new Thread(() -> {
//            SettingsData baseSettingsData = new SettingsData();
//            baseSettingsData.threadNum = BaseData.threadNum;
//            baseSettingsData.path = BaseData.path;
//            baseSettingsData.isDownloadLrc = BaseData.isDownloadLrc;
//            baseSettingsData.isDownloadDanmu = BaseData.isDownloadDanmu;
//            SerializationUtil.serialize(baseSettingsData, settingsFilePath);
//        }).start();
//    }
}