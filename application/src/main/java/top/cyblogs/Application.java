package top.cyblogs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import top.cyblogs.init.DownloadAria2c;
import top.cyblogs.init.DownloadFFMpeg;

/**
 * 程序启动类
 */
@EnableScheduling
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        DownloadAria2c.download();
        DownloadFFMpeg.download();
        SpringApplication.run(Application.class, args);
    }
}
