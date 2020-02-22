package top.cyblogs.ffmpeg.command;

import top.cyblogs.data.PathData;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * FFMpeg的命令
 *
 * @author CY
 */
public class FFMpegCommand {

    private static final String FFMPEG = PathData.FFMPEG.getAbsolutePath();

    /**
     * 使用分离器文件合并视频
     */
    public static List<String> mergeVideo(File seperator, File out) {
        return List.of(
                String.format("\"%s\"", FFMPEG),
                "-f", "concat", "-safe", "0", "-i",
                String.format("\"%s\"", seperator.getAbsoluteFile()),
                "-c", "copy", "-y",
                String.format("\"%s\"", out.getAbsoluteFile())
        );
    }

    /**
     * 音频和视频文件合并在一起的命令
     *
     * @param video 视频文件
     * @param audio 音频文件
     * @param out   输出文件
     * @return 命令
     */
    public static List<String> mergeVideoAndAudio(File video, File audio, File out) {
        return List.of(
                String.format("\"%s\"", FFMPEG),
                "-i", String.format("\"%s\"", video.getAbsolutePath()),
                "-i", String.format("\"%s\"", audio.getAbsolutePath()),
                "-c:v", "copy", "-c:a", "aac", "-strict", "experimental", "-map", "0:v:0?", "-map", "1:a:0?",
                String.format("\"%s\"", out.getAbsolutePath())
        );
    }

    /**
     * 下载M3U8文件到视频的命令
     *
     * @return 命令
     */
    public static List<String> downloadM3U8(String m3u8URL, File out) {
        return List.of(
                String.format("\"%s\"", FFMPEG),
                "-allowed_extensions", "ALL", "-protocol_whitelist", "\"file,http,https,rtp,udp,tcp,tls,crypto\"",
                "-i", String.format("\"%s\"", m3u8URL),
                "-c", "copy", String.format("\"%s\"", out.getAbsolutePath())
        );
    }

    /**
     * 合并视频文件命令（该命令使用了concat的方式，所以只能合并TS文件）
     *
     * @return 命令
     */
    public static List<String> mergeTS(List<File> videos, File out) {
        return List.of(
                String.format("\"%s\"", FFMPEG),
                "-i", String.format("\"concat:%s\"", videos.stream().map(File::getAbsolutePath)
                        .collect(Collectors.joining("|"))),
                "-c", "copy", "-bsf:a", "aac_adtstoasc",
                String.format("\"%s\"", out.getAbsolutePath())
        );
    }

    /**
     * 将视频转为TS格式的命令
     *
     * @return 命令
     */
    public static List<String> convert2TS(File video, File out) {

        // 全部替换成.ts后缀
        String path = out.getAbsolutePath();
        String lastPath = path.substring(0, path.lastIndexOf(".")) + ".ts";

        return List.of(
                String.format("\"%s\"", FFMPEG),
                "-i", String.format("\"%s\"", video.getAbsolutePath()),
                "-vcodec", "copy", "-acodec", "copy", "-vbsf", "h264_mp4toannexb",
                String.format("\"%s\"", lastPath)
        );
    }
}