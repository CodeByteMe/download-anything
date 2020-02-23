package top.cyblogs.data;

import java.io.File;

/**
 * 软件所有到的路径数据
 *
 * @author CY
 */
public class PathData {

    /**
     * 一堆的文件夹名称
     */
    public transient static final String TEMP_DIRECTION = System.getProperty("java.io.tmpdir");
    public transient static final String USER_HOME_DIRECTION = System.getProperty("user.home") + File.separator;
    public transient static final String CY_DIRECTION = "CySoftware" + File.separator;
    public transient static final String APP_DATA_DIRECTION = "AppData" + File.separator + "Local" + File.separator;
    public transient static final String DOWNLOAD_DIRECTION = "Downloads" + File.separator;
    public transient static final String COMMONS_DIRECTION = "Commons" + File.separator;

    /**
     * 临时文件保存的路径
     */
    public transient static final String TEMP_FILE_PATH = TEMP_DIRECTION + CY_DIRECTION;

    /**
     * 默认的下载路径
     */
    public transient static final String DOWNLOAD_PATH = USER_HOME_DIRECTION + DOWNLOAD_DIRECTION;

    /**
     * 程序的数据目录
     */
    public transient static final String APP_DATA_PATH = USER_HOME_DIRECTION + APP_DATA_DIRECTION + CY_DIRECTION;

    /**
     * 程序的Commons目录
     *
     * @apiNote 主要用于存放公用的应用，比如说FFMpeg或者Aria2c
     */
    public transient static final String COMMONS_PATH = APP_DATA_PATH + COMMONS_DIRECTION;

    /**
     * FFMpeg EXE路径
     */
    public transient static final File FFMPEG = new File(COMMONS_PATH + "ffmpeg.exe");
    public transient static final File FFMPEG_ZIP = new File(COMMONS_PATH + "ffmpeg.zip");

    /**
     * Aria2c EXE路径
     */
    public transient static final File ARIA2C = new File(COMMONS_PATH + "aria2c.exe");
    public transient static final File ARIA2C_ZIP = new File(COMMONS_PATH + "aria2c.zip");

    /**
     * 设置文件保存的路径
     */
    public transient static final String SETTING_FILE_PATH = APP_DATA_PATH + BaseData.APP_NAME + File.separator + "settings.conf";
}
