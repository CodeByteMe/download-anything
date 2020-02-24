package top.cyblogs.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * [Entity]
 * aria2.getPeers方法的返回值
 * ========================
 * https://aria2.github.io/manual/en/html/aria2c.html#aria2.getPeers
 * <p>
 * TODO 未进行文档人工翻译
 */
@Data
public class Aria2cPeer {

    /**
     * URLEncode对等ID
     */
    @JsonProperty("peerId")
    private String peerId;

    /**
     * 对端的IP地址。
     */
    @JsonProperty("ip")
    private String ip;

    /**
     * 对等体的端口号。
     */
    @JsonProperty("port")
    private int port;

    /**
     * 对等体下载进度的十六进制表示形式。
     * 最高位对应于索引为0的作品。
     * 置位表示作品可用，未置位表示作品缺失。
     * 最后的任何备用位都设置为零。
     */
    @JsonProperty("bitfield")
    private String bitfield;

    /**
     * true如果aria2使同伴窒息。否则false。
     */
    @JsonProperty("amChoking")
    private Boolean amChoking;

    /**
     * true如果同伴正在窒息aria2。否则false
     */
    @JsonProperty("peerChoking")
    private Boolean peerChoking;

    /**
     * 该客户端从对等方获得的下载速度（字节/秒）。
     */
    @JsonProperty("downloadSpeed")
    private long downloadSpeed;

    /**
     * 此客户端上载到对等方的上载速度（字节/秒）
     */
    @JsonProperty("uploadSpeed")
    private long uploadSpeed;

    /**
     * true如果此对等方是播种机。否则false。
     */
    @JsonProperty("seeder")
    private Boolean seeder;
}
