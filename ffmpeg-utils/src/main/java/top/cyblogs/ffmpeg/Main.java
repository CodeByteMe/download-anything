package top.cyblogs.ffmpeg;

import top.cyblogs.ffmpeg.exec.Convert2Ts;
import top.cyblogs.ffmpeg.exec.DownloadM3U8;
import top.cyblogs.ffmpeg.listener.FFMpegListener;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        Convert2Ts.exec(new File("C:\\Users\\dnydi\\Desktop\\做个迷宫\\xxx.mp4"), new File("C:\\Users\\dnydi\\Desktop\\做个迷宫\\xxx.mp4"), new FFMpegListener() {
            @Override
            public void start() {
                System.out.println("开始了");
            }

            @Override
            public void progress(long current, long total) {
                System.out.println(current + "/" + total);
            }

            @Override
            public void over() {
                System.out.println("结束了");
            }
        });

//        downloadM();
    }

    private static void downloadM() {
        DownloadM3U8.exec("https://valipl.cp31.ott.cibntv.net/677441EAF923671637CDE32F6/03000600005E3EE90B59A9C6CCEAADCC6FB62C-10D3-428C-B446-F92765DF0CFD-1-114.m3u8?ccode=0502&duration=2441&expire=18000&psid=81a432e5051aec1fdd98de286d48324e&ups_client_netip=3cded01b&ups_ts=1582454486&ups_userid=&utid=rzHiFYGqt00CATzeX0G8Pq5s&vid=XNDUzNDk2NjQ4OA&vkey=Ae0f78bb3bb1964b31f70d019a3aaf120&sm=1&operate_type=1&dre=u37&si=73&dst=1&iv=0&s=cdaced4474c24aa49db1&bc=2",
                new File("C:\\Users\\dnydi\\Desktop\\做个迷宫\\xxx.mp4"), new FFMpegListener() {
                    @Override
                    public void start() {
                        System.out.println("开始了");
                    }

                    @Override
                    public void progress(long current, long total) {
                        System.out.println(current + "/" + total);
                    }

                    @Override
                    public void over() {
                        System.out.println("结束了");
                    }
                });
    }
}
