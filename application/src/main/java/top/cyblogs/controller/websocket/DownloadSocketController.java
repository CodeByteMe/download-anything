package top.cyblogs.controller.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;
import top.cyblogs.data.DownloadList;
import top.cyblogs.model.DownloadItem;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * 该类用来每秒向前台发送一次下载状态的list集合
 *
 * @author CY
 */
@Slf4j
@RestController
public class DownloadSocketController {

    @Resource
    private SimpMessagingTemplate messagingTemplate;

    /**
     * 每秒钟都要向前台发送一次状态
     */
    @Scheduled(cron = "*/1 * * * * ?")
    public void downloadStatus() {
        List<DownloadItem> downloadList = DownloadList.list;
        Collections.reverse(downloadList);
        messagingTemplate.convertAndSend("/downloadStatus/notice", downloadList);
    }
}
