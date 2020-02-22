package top.cyblogs.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * [Entity]
 * Aria2cTorrent中info属性的子结构
 * =============================
 *
 * @author CY
 * <p>
 * TODO 未进行文档人工翻译
 */
@Data
public class Aria2cTorrentInfo {

    /**
     * 信息字典中的名称。name.utf-8如果可用，则使用。
     */
    @JsonProperty("name")
    private String name;
}
