package top.cyblogs.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * [Entity]
 * aria2.getFiles()的返回值
 * =======================
 * https://aria2.github.io/manual/en/html/aria2c.html#aria2.getFiles
 * <p>
 * TODO 未进行文档人工翻译
 */
@Data
public class Aria2cFile {

    /**
     * 文件的索引，从1开始，与文件在多文件torrent中的显示顺序相同。
     */
    @JsonProperty("index")
    private int index;

    /**
     * 文件路径。
     */
    @JsonProperty("path")
    private String path;

    /**
     * 文件大小（以字节为单位）。
     */
    @JsonProperty("length")
    private long length;

    /**
     * 此文件的完整长度（以字节为单位）。
     * 请注意，可能小于方法aria2.tellStatus()返回的completedLength的总和
     * 这是因为，completedLength在 aria2.getFiles()仅包括完成作品。
     * 在另一方面，completedLength 在aria2.tellStatus()还包括部分完成的块。
     */
    @JsonProperty("completedLength")
    private long completedLength;

    /**
     * true如果通过--select-file选项选择了此文件。
     * 如果 --select-file未指定，或者这是单文件种子或根本不是种子下载，
     * 则此值始终为true。否则 false。
     */
    @JsonProperty("selected")
    private Boolean selected;

    /**
     * 返回此文件的URI列表。元素类型与aria2.getUris()方法中使用的结构相同。
     */
    @JsonProperty("uris")
    private Aria2cUri[] uris;
}