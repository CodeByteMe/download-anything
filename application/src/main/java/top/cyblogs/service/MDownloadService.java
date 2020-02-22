package top.cyblogs.service;

import org.springframework.stereotype.Service;
import top.cyblogs.BiliBiliSupport;
import top.cyblogs.exception.UrlNotSupportException;

@Service
public class MDownloadService {

    public void download(String url, String cookie) {
        if (url.contains("bilibili.com")) {
            BiliBiliSupport.start(url, cookie);
        } else {
            throw new UrlNotSupportException("您要下载的URL还没有被支持, 请查看支持列表");
        }
    }
}
