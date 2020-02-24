package top.cyblogs.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * [Entity]
 * aria2.tellStatus/aria2.tellActive/aria2.tellWaiting/aria2.tellStopped方法的返回值
 * ===============================================================================
 * https://aria2.github.io/manual/en/html/aria2c.html#aria2.tellStatus
 * https://aria2.github.io/manual/en/html/aria2c.html#aria2.tellActive
 * https://aria2.github.io/manual/en/html/aria2c.html#aria2.tellWaiting
 * https://aria2.github.io/manual/en/html/aria2c.html#aria2.tellStopped
 * <p>
 * TODO 未进行文档人工翻译
 *
 * @author CY
 */
@Data
public class Aria2cStatus {

    /**
     * 下载的GID
     */
    @JsonProperty("gid")
    private String gid;

    /**
     * active当前下载/种子下载。
     * waiting用于队列中的下载；下载未开始。
     * paused暂停下载。
     * error对于由于错误而停止的下载。
     * complete停止和完成下载。
     * removed用户删除的下载。
     */
    @JsonProperty("status")
    private String status;

    /**
     * 下载的总长度（以字节为单位）。
     */
    @JsonProperty("totalLength")
    private long totalLength;

    /**
     * 下载的完整长度（以字节为单位）。
     */
    @JsonProperty("completedLength")
    private long completedLength;

    /**
     * 上载的下载长度（以字节为单位）。
     */
    @JsonProperty("uploadLength")
    private long uploadLength;

    /**
     * 下载进度的十六进制表示。
     * 最高位对应于索引为0的块。
     * 任何置位的位指示已加载的块，而未置位的位指示尚未加载和/或缺失的件。
     * 最后的任何溢出位都设置为零。
     * 当尚未开始下载时，该密钥将不包含在响应中。
     */
    @JsonProperty("bitfield")
    private String bitfield;

    /**
     * 此下载的下载速度以字节/秒为单位
     */
    @JsonProperty("downloadSpeed")
    private long downloadSpeed;

    /**
     * 此下载的上传速度（以字节/秒为单位）
     */
    @JsonProperty("uploadSpeed")
    private long uploadSpeed;

    /**
     * InfoHash。仅限BitTorrent。
     */
    @JsonProperty("infoHash")
    private String infoHash;

    /**
     * aria2所连接的播种机数量。仅限BitTorrent
     */
    @JsonProperty("numSeeders")
    private int numSeeders;

    /**
     * true如果本地端点是播种者。否则false。仅限BitTorrent
     */
    @JsonProperty("seeder")
    private Boolean seeder;

    /**
     * 片段长度（以字节为单位）
     */
    @JsonProperty("pieceLength")
    private long pieceLength;

    /**
     * 块数
     */
    @JsonProperty("numPieces")
    private long numPieces;

    /**
     * aria2已连接的对等/服务器数。
     */
    @JsonProperty("connections")
    private int connections;

    /**
     * 此项最后错误的代码（如果有）。
     * 该值是一个字符串。
     * 错误代码 https://aria2.github.io/manual/en/html/aria2c.html#id1
     * 此值仅适用于停止/完成的下载。
     */
    @JsonProperty("errorCode")
    private int errorCode;

    /**
     * 与（相关的）人类可读的错误消息 errorCode。
     */
    @JsonProperty("errorMessage")
    private String errorMessage;

    /**
     * 下载结果生成的GID列表。
     * 例如，aria2下载Metalink文件时，它会生成Metalink中所述的下载内容（请参阅 --follow-metalink选项）。
     * 该值对于跟踪自动生成的下载很有用。
     * 如果没有此类下载，则此密钥将不包含在响应中。
     */
    @JsonProperty("followedBy")
    private String[] followedBy;

    /**
     * followedBy的反向链接。
     * 其中包含的下载内容 followedBy具有此对象的GID following。
     */
    @JsonProperty("following")
    private String[] following;

    /**
     * 父级下载的GID。
     * 某些下载是另一下载的一部分。
     * 例如，如果Metalink中的文件具有BitTorrent资源，则“ .torrent”文件的下载是该父文件的一部分。
     * 如果此下载没有父项，则此密钥将不包含在响应中
     */
    @JsonProperty("belongsTo")
    private String belongsTo;

    /**
     * 保存文件的目录
     */
    @JsonProperty("dir")
    private String dir;

    /**
     * 返回文件列表。
     * 该列表的元素与aria2.getFiles()方法中使用的结构相同。
     */
    @JsonProperty("files")
    private Aria2cFile[] files;

    /**
     * 包含从.torrent（文件）中检索到的信息的结构。仅限BitTorrent
     */
    @JsonProperty("bittorrent")
    private Aria2cTorrent bitTorrent;

    /**
     * 在对文件进行哈希检查时，已验证的字节数。
     * 仅当对该下载进行哈希检查时，该key才存在
     */
    @JsonProperty("verifiedLength")
    private long verifiedLength;

    /**
     * true如果此下载正在等待队列中的哈希检查。
     * 仅当此下载在队列中时，此key才存在。
     */
    @JsonProperty("verifyIntegrityPending")
    private Boolean verifyIntegrityPending;
}