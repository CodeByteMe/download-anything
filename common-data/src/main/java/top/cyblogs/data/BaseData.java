package top.cyblogs.data;

import java.io.File;

//import javafx.scene.image.Image;

/**
 * 程序中使用到的基本数据
 *
 * @author CY
 * 2019年7月15日
 */
public class BaseData {


    /**
     * 该软件的ID，不允许修改
     */
    public transient static final String APP_ID = "fbdaf00122894448aea7f4cb9307a6e8";

    /**
     * 默认的下载路径，当前软件所处的运行路径
     */
    public static String path = System.getProperty("user.home") + File.separator + "Downloads" + File.separator;
}
