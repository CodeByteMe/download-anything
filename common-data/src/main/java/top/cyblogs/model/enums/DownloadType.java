package top.cyblogs.model.enums;

import lombok.Getter;

/**
 * 下载类型枚举
 *
 * @author CY
 */
@Getter
public enum DownloadType {

    /**
     * 音乐
     */
    AUDIO(new String[]{"wav", "mp3", "flac", "ape", "aac"}),

    /**
     * 视频
     */
    VIDEO(new String[]{"mp4", "flv", "rmvb", "ts"}),

    /**
     * 文档
     */
    DOCUMENT(new String[]{"doc", "docx", "pdf", "text", "html", "xml"}),

    /**
     * 压缩文件
     */
    ZIP(new String[]{"zip", "rar", "7z"}),

    /**
     * 图片
     */
    PICTURE(new String[]{"jpg", "png", "bmp", "gif"}),

    /**
     * 歌词
     */
    LRC(new String[]{"lrc"}),

    /**
     * 字幕
     */
    ASS(new String[]{"ass"});

    private String[] extensions;

    DownloadType(String[] extensions) {
        this.extensions = extensions;
    }
}