package top.cyblogs.data;

/**
 * 应用程序初始化设置数据
 *
 * @author CY
 */
public class SettingsData {

    /**
     * 文件默认保存的路径
     */
    public static String path = PathData.DOWNLOAD_PATH;

    /**
     * 同时下载文件的线程数量
     */
    public static Integer threadNum = 5;

    /**
     * 如果文件存在就跳过它
     */
    public static boolean skipIfExists = true;
}