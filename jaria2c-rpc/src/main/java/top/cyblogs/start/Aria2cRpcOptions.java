package top.cyblogs.start;

import lombok.Data;
import lombok.experimental.Accessors;
import top.cyblogs.support.OptionName;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * [Entity]
 * 启动Aria2c的RPC选项
 * 实体类里面保存了端口号
 * <p>
 * TODO 未进行文档人工翻译
 */
@Data
@Accessors(chain = true)
public class Aria2cRpcOptions {

    /*======================单例=====================*/

    private static Aria2cRpcOptions instance = new Aria2cRpcOptions();
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-enable-rpc
     * <p>
     * 启用JSON-RPC/XML-RPC服务器
     * 强烈建议使用--rpc-secret选项设置秘密授权令牌
     * 另请参阅--rpc-listen-port选项
     * 默认：false
     */
    @OptionName("enable-rpc")
    private Boolean enableRpc = true;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-pause
     * <p>
     * 添加后暂停下载
     * 仅当--enable-rpc=true给出时，此选项才有效
     * 默认：false
     */
    @OptionName("pause")
    private Boolean pause = false;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-pause-metadata
     * <p>
     * 暂停由元数据创建的下载。
     * aria2中有3种元数据下载类型：
     * （1）下载.torrent文件。
     * （2）使用磁力链接下载torrent元数据。
     * （3）下载metalink文件。
     * 这些元数据下载将使用其元数据生成下载。
     * 此选项将暂停这些后续下载。
     * 仅当--enable-rpc=true给出此选项时才有效 。
     * 默认：false
     */
    @OptionName("pause-metadata")
    private Boolean pauseMetadata = false;

    /*============================RPC参数============================*/
    /*https://aria2.github.io/manual/en/html/aria2c.html#rpc-options*/
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-rpc-allow-origin-all
     * <p>
     * 将具有值的Access-Control-Allow-Origin标头字段添加*到RPC响应中。
     * 默认：false
     */
    @OptionName("rpc-allow-origin-all")
    private Boolean rpcAllowOriginAll = false;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-rpc-certificate
     * <p>
     * 对RPC服务器使用FILE中的证书。证书必须为PKCS12（.p12，.pfx）或PEM格式。
     * <p>
     * PKCS12文件必须包含证书，密钥以及可选的附加证书链。只能打开导入密码为空的PKCS12文件！
     * <p>
     * 使用PEM时，还必须通过指定私钥--rpc-private-key 。使用--rpc-secure选项启用加密。
     */
    @OptionName("rpc-certificate")
    private String rpcCertificate;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-rpc-listen-all
     * <p>
     * 在所有网络接口上侦听传入的JSON-RPC / XML-RPC请求。
     * 如果给出false，则仅侦听本地环回接口。
     * 默认：false
     */
    @OptionName("rpc-listen-all")
    private Boolean rpcListenAll = false;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-rpc-listen-port
     * <p>
     * 指定JSON-RPC / XML-RPC服务器要侦听的端口号。
     * 可能的值：1024- 65535
     * 默认值：6800
     */
    @OptionName("rpc-listen-port")
    private Integer rpcListenPort = 6800;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-rpc-max-request-size
     * <p>
     * 设置JSON-RPC / XML-RPC请求的最大大小。
     * 如果aria2检测到请求超过SIZE个字节，则会丢弃连接。
     * 默认：2M
     */
    @OptionName("rpc-max-request-size")
    private String rpcMaxRequestSize = "2M";
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-rpc-passwd
     * <p>
     * 设置JSON-RPC / XML-RPC密码。
     */
    @OptionName("rpc-passwd")
    private String rpcPasswd;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-rpc-private-key
     * <p>
     * 将FILE中的私钥用于RPC服务器。
     * 私钥必须解密并采用PEM格式。
     * 使用--rpc-secure选项启用加密。
     * 另请参阅--rpc-certificate选项。
     */
    @OptionName("rpc-private-key")
    private String rpcPrivateKey;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-rpc-save-upload-metadata
     * <p>
     * 将上载的torrent或metalink元数据保存在--dir option 指定的目录中。
     * 文件名由元数据的SHA-1哈希十六进制字符串加上扩展名组成。
     * 对于torrent，扩展名为“ .torrent”。
     * 对于metalink，它是'.meta4'。
     * 如果将此选项设置为false，
     * 则选项添加 aria2.addTorrent()或aria2.addMetalink()不会保存下载--save-session。
     * 默认：true
     */
    @OptionName("rpc-save-upload-metadata")
    private Boolean rpcSaveUploadMetadata = true;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-rpc-secret
     * <p>
     * 设置RPC秘密授权令牌。
     * 阅读 https://aria2.github.io/manual/en/html/aria2c.html#rpc-auth 以了解如何使用此选项值。
     */
    @OptionName("rpc-secret")
    private String rpcSecret;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-rpc-secure
     * <p>
     * RPC传输将通过SSL / TLS加密。
     * RPC客户端必须使用https方案来访问服务器。
     * 对于WebSocket客户端，请使用wss方案。
     * 使用--rpc-certificate和 --rpc-private-key选项指定服务器证书和私钥。
     */
    @OptionName("rpc-secure")
    private Boolean rpcSecure;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-rpc-user
     * <p>
     * 设置JSON-RPC / XML-RPC用户
     */
    @OptionName("rpc-user")
    private String rpcUser;

    private Aria2cRpcOptions() {
    }

    public static Aria2cRpcOptions getInstance() {
        return instance;
    }

    /**
     * 获取命令行参数
     *
     * @return 命令行参数List
     */
    public List<String> options() {
        List<String> options = new ArrayList<>();
        Field[] fields = this.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(this);
                if (value == null) {
                    continue;
                }
                OptionName optionName = field.getAnnotation(OptionName.class);
                if (optionName == null) {
                    continue;
                }
                options.add(String.format("--%s=\"%s\"", optionName.value(), value));
            }
        } catch (IllegalAccessException ignored) {
        }
        return options;
    }
}
