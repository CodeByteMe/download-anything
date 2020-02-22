package top.cyblogs.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * [Entity]
 * aria2.getGlobalStat()方法的返回值
 * ===============================
 * https://aria2.github.io/manual/en/html/aria2c.html#aria2.getGlobalStat
 * <p>
 * TODO 未进行文档人工翻译
 */
@Data
public class Aria2cGlobalStat {

    @JsonProperty("numActive")
    private String numActive;

    @JsonProperty("numWaiting")
    private String numWaiting;

    @JsonProperty("downloadSpeed")
    private String downloadSpeed;

    @JsonProperty("uploadSpeed")
    private String uploadSpeed;

    @JsonProperty("numStopped")
    private String numStopped;

    @JsonProperty("numStoppedTotal")
    private String numStoppedTotal;
}