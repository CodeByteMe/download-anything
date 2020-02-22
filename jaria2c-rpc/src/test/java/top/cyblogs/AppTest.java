package top.cyblogs;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import top.cyblogs.input.Aria2cCall;
import top.cyblogs.input.Aria2cOptions;
import top.cyblogs.output.*;
import top.cyblogs.start.Aria2cRpcOptions;
import top.cyblogs.support.Options;
import top.cyblogs.support.Secret;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class AppTest {

    private static Aria2c aria2c;

    private static String desktopPath = System.getProperty("user.home") + File.separator + "Desktop" + File.separator;

    private static String token = Secret.token(null);

    /**
     * 一个下载的例子，QQ
     */
    public static void main(String[] args) {

        new AppTest().init();

        String[] uris = {
                "https://qd.myapp.com/myapp/qqteam/pcqq/PCQQ2020.exe"
        };

        Options of = Options.of(Aria2cOptions.builder()
                .dir(desktopPath + "下载目录")
                .out("QQ.exe")
                .build());

        String gid = aria2c.addUri(token, uris, of, Integer.MAX_VALUE);

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            Aria2cStatus downloadStatus = aria2c.tellStatus(token, gid, new String[]{});
            log.debug("当前的下载速度：{}，响应的数据：{}", downloadStatus.getDownloadSpeed(), downloadStatus);
        }, 0, 1, TimeUnit.SECONDS);
    }

    @BeforeEach
    public void init() {
        aria2c = Aria2c.run();
        token = Secret.token(Aria2cRpcOptions.getInstance().getRpcSecret());
        System.out.println(Aria2cRpcOptions.getInstance());
    }

    @Test
    public void testAddUri() {

        String[] uris = {
                "https://qd.myapp.com/myapp/qqteam/pcqq/PCQQ2020.exe"
        };
        Options of = Options.of(Aria2cOptions.builder().dir(desktopPath + "下载目录").build());
        String gid = aria2c.addUri(token, uris, of, Integer.MAX_VALUE);
        log.debug(gid);
        Assertions.assertNotNull(gid);
    }

    @Test
    public void testAddTorrent() throws IOException {
        String torrent = Aria2c.input2Base64(
                new FileInputStream(desktopPath + "[JYFanSub][Occultic;Nine][12][GB_CN][HEVC][720p].torrent"));

        String gid = aria2c.addTorrent(token, torrent, new String[]{}, Options.empty(), Integer.MAX_VALUE);

        log.debug(gid);
        Assertions.assertNotNull(gid);
    }

    @Test
    public void testAddMetaLink() throws FileNotFoundException {
        String metalink = Aria2c.input2Base64(
                new FileInputStream(desktopPath + "TED-talks-in-low-quality.eu.metalink"));

        String[] gids = aria2c.addMetaLink(token, metalink, Options.empty(), Integer.MAX_VALUE);

        log.debug(Arrays.toString(gids));
        Assertions.assertTrue(gids.length > 0);
    }

    @Test
    public void testRemove() throws InterruptedException {
        String[] uris = {
                "https://ffmpeg.zeranoe.com/builds/win64/static/ffmpeg-20200209-5ad1c1a-win64-static.zip"
        };
        String gid = aria2c.addUri(token, uris, Options.empty(), Integer.MAX_VALUE);
        Thread.sleep(10000);
        String gid2 = aria2c.remove(token, gid);
        log.debug(gid2);
        Assertions.assertNotNull(gid2);
    }

    @Test
    public void testPause() throws InterruptedException {
        String[] uris = {
                "https://ffmpeg.zeranoe.com/builds/win64/static/ffmpeg-20200209-5ad1c1a-win64-static.zip"
        };
        String gid = aria2c.addUri(token, uris, Options.empty(), Integer.MAX_VALUE);
        Thread.sleep(10000);
        String gid2 = aria2c.pause(token, gid);
        log.debug(gid2);
        Assertions.assertNotNull(gid2);
    }

    @Test
    public void testPauseAll() throws InterruptedException {
        String[] uris = {
                "https://ffmpeg.zeranoe.com/builds/win64/static/ffmpeg-20200209-5ad1c1a-win64-static.zip"
        };
        aria2c.addUri(token, uris, Options.empty(), Integer.MAX_VALUE);
        Thread.sleep(10000);
        String gid2 = aria2c.pauseAll(token);
        log.debug(gid2);
        Assertions.assertNotNull(gid2);
    }

    @Test
    public void testGetUris() {
        String[] uris = {
                "https://ffmpeg.zeranoe.com/builds/win64/static/ffmpeg-20200209-5ad1c1a-win64-static.zip"
        };
        String gid = aria2c.addUri(token, uris, Options.empty(), Integer.MAX_VALUE);
        Aria2cUri[] uris1 = aria2c.getUris(token, gid);
        log.debug("响应的数据：{}", Arrays.toString(uris1));
        Assertions.assertTrue(uris1.length > 0);
    }

    @Test
    public void testGetFiles() {
        String[] uris = {
                "https://ffmpeg.zeranoe.com/builds/win64/static/ffmpeg-20200209-5ad1c1a-win64-static.zip"
        };
        String gid = aria2c.addUri(token, uris, Options.empty(), Integer.MAX_VALUE);
        Aria2cFile[] files = aria2c.getFiles(token, gid);
        log.debug("响应的数据：{}", Arrays.toString(files));
        Assertions.assertTrue(files.length > 0);
    }

    @Test
    public void testGetPeers() {
        String[] uris = {
                "magnet:?xt=urn:btih:49cc63b38aa68c64eb1f4445484130bb9294ac7f"
        };
        String gid = aria2c.addUri(token, uris, Options.empty(), Integer.MAX_VALUE);
        Aria2cPeer[] peers = aria2c.getPeers(token, gid);
        log.debug("响应的数据：{}", Arrays.toString(peers));
        Assertions.assertTrue(peers.length > 0);
    }

    @Test
    public void testGetServers() {
        String[] uris = {
                "https://ffmpeg.zeranoe.com/builds/win64/static/ffmpeg-20200209-5ad1c1a-win64-static.zip"
        };
        String gid = aria2c.addUri(token, uris, Options.empty(), Integer.MAX_VALUE);
        Aria2cServer[] servers = aria2c.getServers(token, gid);
        log.debug("响应的数据：{}", Arrays.toString(servers));
        Assertions.assertTrue(servers.length > 0);
    }

    @Test
    public void testTellActive() {
        Aria2cStatus[] downloadStatus = aria2c.tellActive(token, new String[]{});
        log.debug("响应的数据：{}", Arrays.toString(downloadStatus));
        Assertions.assertTrue(downloadStatus.length > 0);
    }

    @Test
    public void testTellWaiting() {
        Aria2cStatus[] downloadStatus = aria2c.tellWaiting(token, 0, 10, new String[]{});
        log.debug("响应的数据：{}", Arrays.toString(downloadStatus));
        Assertions.assertTrue(downloadStatus.length > 0);
    }

    @Test
    public void testTellStoped() {
        Aria2cStatus[] downloadStatus = aria2c.tellStopped(token, 0, 10, new String[]{});
        log.debug("响应的数据：{}", Arrays.toString(downloadStatus));
        Assertions.assertTrue(downloadStatus.length > 0);
    }

    @Test
    public void testChangePosition() {
        String[] uris = {
                "https://ffmpeg.zeranoe.com/builds/win64/static/ffmpeg-20200209-5ad1c1a-win64-static.zip"
        };
        String gid = aria2c.addUri(token, uris, Options.empty(), Integer.MAX_VALUE);
        Integer pos = aria2c.changePosition(token, gid, -1, "POS_CUR");
        log.debug("当前位置：{}", pos);
        Assertions.assertNotNull(pos);
    }

    @Test
    public void testChangeUri() {
        String[] uris = {
                "https://ffmpeg.zeranoe.com/builds/win64/static/ffmpeg-20200209-5ad1c1a-win64-static.zip"
        };
        String gid = aria2c.addUri(token, uris, Options.empty(), Integer.MAX_VALUE);
        Integer[] pos = aria2c.changeUri(token, gid, 1, new String[]{}, new String[]{
                "https://ffmpeg.zeranoe.com/builds/win64/static/ffmpeg-20200209-5ad1c1a-win64-static.zip",
                "https://ffmpeg.zeranoe.com/builds/win64/static/ffmpeg-20200209-5ad1c1a-win64-static.zip",
                "https://ffmpeg.zeranoe.com/builds/win64/static/ffmpeg-20200209-5ad1c1a-win64-static.zip",
                "https://ffmpeg.zeranoe.com/builds/win64/static/ffmpeg-20200209-5ad1c1a-win64-static.zip",
                "https://ffmpeg.zeranoe.com/builds/win64/static/ffmpeg-20200209-5ad1c1a-win64-static.zip",
        }, 0);
        log.debug("删除了：{}个，添加了：{}个", pos[0], pos[1]);
        Assertions.assertEquals(2, pos.length);
    }

    @Test
    public void testGetOption() {
        String[] uris = {
                "https://ffmpeg.zeranoe.com/builds/win64/static/ffmpeg-20200209-5ad1c1a-win64-static.zip"
        };
        Options of = Options.of(Aria2cOptions.builder()
                .referer("https://www.baidu.com")
                .dir(desktopPath + "下载目录")
                .build());
        String gid = aria2c.addUri(token, uris, of, Integer.MAX_VALUE);
        Aria2cOption option = aria2c.getOption(token, gid);
        log.debug("选项: {}", option);
        Assertions.assertNotNull(option);
    }

    @Test
    public void testGetGlobalOption() {
        Aria2cOption option = aria2c.getGlobalOption(token);
        log.debug("选项: {}", option);
        Assertions.assertNotNull(option);
    }

    @Test
    public void testChangeOption() {
        String[] uris = {
                "https://ffmpeg.zeranoe.com/builds/win64/static/ffmpeg-20200209-5ad1c1a-win64-static.zip"
        };
        Options of = Options.of(Aria2cOptions.builder()
                .referer("https://www.baidu.com")
                .dir(desktopPath + "下载目录")
                .build());
        String gid = aria2c.addUri(token, uris, Options.empty(), Integer.MAX_VALUE);
        String ok = aria2c.changeOption(token, gid, of);
        log.debug("返回: {}", ok);
        Assertions.assertEquals("OK", ok);
    }

    @Test
    public void testChangeGlobalOption() {
        Options of = Options.of(Aria2cOptions.builder()
                .referer("https://www.baidu.com")
                .logLevel("debug")
                .dir(desktopPath + "下载目录")
                .build());
        String ok = aria2c.changeGlobalOption(token, of);
        log.debug("返回: {}", ok);
        Assertions.assertEquals("OK", ok);
    }

    @Test
    public void testGetGlobalStat() {
        Aria2cGlobalStat globalStat = aria2c.getGlobalStat(token);
        log.debug("返回: {}", globalStat);
        Assertions.assertNotNull(globalStat);
    }

    @Test
    public void testPurgeDownloadResult() {
        String ok = aria2c.purgeDownloadResult(token);
        log.debug("返回: {}", ok);
        Assertions.assertEquals("OK", ok);
    }

    @Test
    public void testRemoveDownloadResult() {
        String[] uris = {
                "https://ffmpeg.zeranoe.com/builds/win64/static/ffmpeg-20200209-5ad1c1a-win64-static.zip"
        };
        Options of = Options.of(Aria2cOptions.builder()
                .referer("https://www.baidu.com")
                .dir(desktopPath + "下载目录")
                .build());
        String gid = aria2c.addUri(token, uris, of, Integer.MAX_VALUE);
        String ok = aria2c.removeDownloadResult(token, gid);
        log.debug("返回: {}", ok);
        Assertions.assertEquals("OK", ok);
    }

    @Test
    public void testGetVersion() {
        Aria2cVersion version = aria2c.getVersion(token);
        log.debug("返回: {}", version);
        Assertions.assertNotNull(version);
    }

    @Test
    public void testGetSessionInfo() {
        Aria2cSession sessionInfo = aria2c.getSessionInfo(token);
        log.debug("返回: {}", sessionInfo);
        Assertions.assertNotNull(sessionInfo);
    }

    @Test
    public void testShutdown() {
        String ok = aria2c.shutdown(token);
        log.debug("返回: {}", ok);
        Assertions.assertEquals("OK", ok);
    }

    @Test
    public void testSaveSession() {
        aria2c.changeGlobalOption(token,
                Options.of(Aria2cOptions.builder()
                        .saveSession(desktopPath + "下载目录" + File.separator + ".session").build()));

        String ok = aria2c.saveSession(token);
        log.debug("返回: {}", ok);
        Assertions.assertEquals("OK", ok);
    }

    @Test
    public void testMultiCall() {

        Aria2cCall call = Aria2cCall.builder()
                .methodName(Aria2c.ADD_URI)
                .params(
                        List.of(
                                token,
                                new String[]{"https://ffmpeg.zeranoe.com/builds/win64/static/ffmpeg-20200209-5ad1c1a-win64-static.zip"},
                                Options.empty(),
                                Integer.MAX_VALUE
                        )
                ).build();

        Object[] multiCall = aria2c.multicall(call, call);
        log.debug("返回: {}", Arrays.toString(multiCall));
        Assertions.assertEquals(2, multiCall.length);
    }

    @Test
    public void testListMethods() {
        String[] methods = aria2c.listMethods();
        log.debug("返回: {}", Arrays.toString(methods));
        Assertions.assertTrue(methods.length > 0);
    }

    @Test
    public void testListNotifications() {
        String[] notifications = aria2c.listNotifications();
        log.debug("返回: {}", Arrays.toString(notifications));
        Assertions.assertTrue(notifications.length > 0);
    }
}
