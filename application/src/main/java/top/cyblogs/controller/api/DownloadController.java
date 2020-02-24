package top.cyblogs.controller.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.cyblogs.model.response.BaseResponse;
import top.cyblogs.service.MDownloadService;

import javax.annotation.Resource;

/**
 * @author CY
 */
@RestController
@RequestMapping("download")
@CrossOrigin(origins = "*")
public class DownloadController {

    @Resource
    private MDownloadService downloadService;

    @RequestMapping("addDownload")
    public BaseResponse<String> addDownload(String url, String cookie) {
        downloadService.download(url, cookie);
        return BaseResponse.ok("正在给你添加下载呢!!!");
    }
}
