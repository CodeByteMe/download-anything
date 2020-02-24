package top.cyblogs.support;

import top.cyblogs.data.BiliBiliData;
import top.cyblogs.download.downloader.*;
import top.cyblogs.download.enums.DownloadType;

/**
 * 下载列表服务，根据用户输入的URL获取下载列表
 */
public class BiliBiliSupport {

    public static void start(String url, String cookie) {
        BiliBiliData.cookie = cookie;
        // 下载普通UP主的视频
        if (url.contains(DownloadType.AV.getUrlPrefix())) {
            AvDownloader.download(url);
        }
        // 下载BiliBili音频去的整张专辑
        else if (url.contains(DownloadType.AM.getUrlPrefix())) {
            AmDownloader.download(url);
        }
        // 下载BiliBili的音频
        else if (url.contains(DownloadType.AU.getUrlPrefix())) {
            AuDownloader.download(url);
        }
        // 下载BiliBili的电视剧，电影，纪录片等
        else if (url.contains(DownloadType.EP.getUrlPrefix())) {
            EpDownloader.download(url);
        }
        // 下载BiliBili的直播
        else if (url.contains(DownloadType.LIVE.getUrlPrefix())) {
            LiveDownloader.download(url);
        }
        // 下载BiliBili的番剧
        else if (url.contains(DownloadType.SS.getUrlPrefix())) {
            SsDownloader.download(url);
        }
        // 下载BiliBili的小视频
        else if (url.contains(DownloadType.VC.getUrlPrefix())) {
            VcDownloader.download(url);
        } else {
            throw new IllegalArgumentException(
                    "Bilibili只支持下面的URL(*是通配符)</br><div class='text-danger'>" +
                            "*www.bilibili.com/video/av*</br>" +
                            "*www.bilibili.com/bangumi/play/ep*</br>" +
                            "*www.bilibili.com/audio/am*</br>" +
                            "*www.bilibili.com/audio/au*</br>" +
                            "*live.bilibili.com*</br>" +
                            "*www.bilibili.com/bangumi/play/ss*</br>" +
                            "*vc.bilibili.com/video*</br></div>" +
                            "如果你觉得不够用，请告诉我你想要添加的地址..."
            );
        }
    }
}
