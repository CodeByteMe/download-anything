package top.cyblogs.download.enums;

/**
 * 一个泛型规定下载的类型和该类型对应的URL前缀
 * 使用该泛型类可以使用下载器工厂类获取相应的下载器
 * 2019年7月15日
 */
public enum DownloadType {

    /**
     * AV
     */
    AV("www.bilibili.com/video/av"),
    /**
     * EP
     */
    EP("www.bilibili.com/bangumi/play/ep"),
    /**
     * AM
     */
    AM("www.bilibili.com/audio/am"),
    /**
     * AU
     */
    AU("www.bilibili.com/audio/au"),
    /**
     * LIVE
     */
    LIVE("live.bilibili.com"),
    /**
     * SS
     */
    SS("www.bilibili.com/bangumi/play/ss"),
    /**
     * VC
     */
    VC("vc.bilibili.com/video");

    /**
     * 该类型的URL前缀
     */
    private String urlPrefix;

    /**
     * 下载类型
     *
     * @param urlPrefix URL前缀
     */
    DownloadType(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

    /**
     * 获取类型的链接前缀
     *
     * @return 返回该类型的链接前缀
     */
    public String getUrlPrefix() {
        return urlPrefix;
    }

    @Override
    public String toString() {
        return "Type{" +
                "urlPrefix='" + urlPrefix + '\'' +
                '}';
    }
}