package top.cyblogs.download;

import com.fasterxml.jackson.databind.JsonNode;
import top.cyblogs.api.MoocApi;
import top.cyblogs.data.BaseData;
import top.cyblogs.service.HlsVideoService;
import top.cyblogs.service.NormalDownloadService;
import top.cyblogs.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MoocDownloader {

    private static void download(String bigTitle, List<Map<String, Object>> maps) throws IOException {
        String largeTitle = "";
        String smallTitle = "";
        for (Map<String, Object> x : maps) {
            Long id = (Long) x.get("id");
            Long chapterId = (Long) x.get("chapterId");
            Long contentId = (Long) x.get("contentId");
            Long contentType = (Long) x.get("contentType");
            Long lessonId = (Long) x.get("lessonId");
            Long termId = (Long) x.get("termId");
            String name = (String) x.get("name");
            // 获取大标题
            if (id != null && chapterId == null && contentId == null && contentType == null && lessonId == null && termId != null) {
                if (!largeTitle.equals(name)) {
                    largeTitle = FileUtils.getPrettyFileName(name);
                }
            }
            // 获取小标题
            else if (contentId == null && chapterId == null && contentType != null && lessonId == null && termId != null) {
                if (!smallTitle.equals(name)) {
                    smallTitle = FileUtils.getPrettyFileName(name);
                }
            } else {
                String title = bigTitle + File.separator + largeTitle + File.separator + smallTitle + File.separator + FileUtils.getPrettyFileName(name);
                System.out.println(title);
                if (contentType != null && contentType == 1) {
                    JsonNode videoSignDto = MoocApi.getVideoResourceUrl(id);
                    String signature = videoSignDto.findValue("signature").asText();
                    String videoId = videoSignDto.findValue("videoId").asText();
                    JsonNode hdItem = MoocApi.getHdVideoUrl(signature, videoId);
                    String realUrl = hdItem.findValue("videoUrl").asText();
                    System.out.println(realUrl);
                    String format = hdItem.findValue("format").asText().trim();
                    if ("hls".equals(format)) {
                        HlsVideoService.download(realUrl, new File(BaseData.path + title + ".mp4"));
                    } else if ("flv".equals(format)) {
                        NormalDownloadService.execDownload(realUrl, new File(BaseData.path + title + ".flv"), null);
                    }
                }

            }
        }
    }
}
