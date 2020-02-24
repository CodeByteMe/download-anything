package top.cyblogs.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * [Entity]
 * Aria2cStatus中bitTorrent属性的子结构
 * ==================================
 *
 * @author CY
 * <p>
 * TODO 未进行文档人工翻译
 */
@Data
public class Aria2cTorrent {

    /**
     * 公告URI列表列表。
     * 如果torrent包含announce 和announce-list，announce则将转换为 announce-list格式。
     */
    @JsonProperty("announceList")
    private List<String[]> announceList;

    /**
     * 种子的评论。comment.utf-8如果可用，则使用。
     */
    @JsonProperty("comment")
    private String comment;

    /**
     * 种子的创建时间。该值是自纪元以来的整数，以秒为单位。
     */
    @JsonProperty("creationDate")
    private long creationDate;

    /**
     * torrent的文件模式。值为single或multi。
     */
    @JsonProperty("mode")
    private String mode;

    /**
     * 包含信息字典中数据的结构。
     */
    @JsonProperty("info")
    private Aria2cTorrentInfo info;
}
