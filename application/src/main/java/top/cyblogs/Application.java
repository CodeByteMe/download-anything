package top.cyblogs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.cyblogs.init.DownloadAria2c;
import top.cyblogs.init.DownloadFFMpeg;

/**
 * 这是我写过最自由的代码了，真的是想到哪写到哪，毕竟是打草稿嘛，你应当向往自由的
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        DownloadAria2c.download();
        DownloadFFMpeg.download();
        SpringApplication.run(Application.class, args);
    }
}
