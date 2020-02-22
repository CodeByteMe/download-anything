package top.cyblogs.data;

import java.io.File;
import java.io.Serializable;

public class PathData implements Serializable {

    /**
     * CY软件专用的文件夹
     */
    private transient static final String CY_DIRECTION = "CySoftware";

    /**
     * 程序的数据目录
     */
    private transient static final String APP_DATA_PATH = System.getProperty("user.home") + File.separator + "AppData"
            + File.separator + "Local" + File.separator + CY_DIRECTION + File.separator;

    /**
     * 程序的Commons目录
     * 主要用于存放公用的应用，比如说FFMpeg或者Aria2c
     */
    public transient static final String COMMONS_PATH = APP_DATA_PATH + "Commons" + File.separator;

    /**
     * FFMPEG EXE路径
     */
    public transient static final File FFMPEG = new File(COMMONS_PATH + "ffmpeg.exe");
    public transient static final File FFMPEG_ZIP = new File(COMMONS_PATH + "ffmpeg.zip");

    /**
     * FFMPEG EXE路径
     */
    public transient static final File ARIA2C = new File(COMMONS_PATH + "aria2c.exe");
    public transient static final File ARIA2C_ZIP = new File(COMMONS_PATH + "aria2c.zip");

    /**
     * 序列化文件保存的路径
     * 默认在用户应用数据文件的Local下面的CySoft中的应用ID文件夹中
     */
    public transient static final String APP_CONFIG_PATH = APP_DATA_PATH + BaseData.APP_ID + File.separator;

    /**
     * 临时文件保存的路径
     */
    public transient static final String TEMP_FILE_PATH = System.getProperty("java.io.tmpdir") + CY_DIRECTION + File.separator;

    /**
     * 设置文件保存的路径
     */
    private transient static final String SETTING_FILE_PATH = PathData.APP_CONFIG_PATH + "settings.conf";
}
