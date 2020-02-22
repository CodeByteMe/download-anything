package top.cyblogs;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.JsonRpcMethod;
import com.googlecode.jsonrpc4j.ProxyUtil;
import lombok.NonNull;
import top.cyblogs.data.PathData;
import top.cyblogs.input.Aria2cCall;
import top.cyblogs.output.*;
import top.cyblogs.start.Aria2cRpcOptions;
import top.cyblogs.start.Aria2cRpcUtils;
import top.cyblogs.support.Options;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.List;

/**
 * 声明了Aria2的所有的方法，该接口中的所有的方法都拥有一个重载，一个是所有参数，一个是必要参数
 * 特别注意，每个参数都不能为null
 * <p>
 * GID参数的含义：https://aria2.github.io/manual/en/html/aria2c.html#terminology
 * <p>
 * GID(或gid)是管理每个下载的密钥。
 * 每个下载将被分配一个唯一的GID。
 * GID在aria2中存储为64位二进制值。
 * 对于RPC访问，它表示为16个字符的十六进制字符串(例如，2089b05ecca3d829)。
 * 通常，aria2为每个下载生成这个GID，但是用户可以使用--gid选项手动指定GID。
 * 当通过GID查询下载时，可以只指定GID的前缀部分，只要他是唯一的就行。
 * <p>
 * TODO 未进行文档人工翻译
 *
 * @author CY
 */
public interface Aria2c {

    String ARIA2_PREFIX = "aria2.";
    String SYSTEM_PREFIX = "system.";
    String ADD_URI = ARIA2_PREFIX + "addUri";

    /*=====================Aria2的所有方法====================*/
    String ADD_TORRENT = ARIA2_PREFIX + "addTorrent";
    String ADD_META_LINK = ARIA2_PREFIX + "addMetalink";
    String REMOVE = ARIA2_PREFIX + "remove";
    String FORCE_REMOVE = ARIA2_PREFIX + "forceRemove";
    String PAUSE = ARIA2_PREFIX + "pause";
    String PAUSE_ALL = ARIA2_PREFIX + "pauseAll";
    String FORCE_PAUSE = ARIA2_PREFIX + "forcePause";
    String FORCE_PAUSE_ALL = ARIA2_PREFIX + "forcePauseAll";
    String UNPAUSE = ARIA2_PREFIX + "unpause";
    String UNPAUSE_ALL = ARIA2_PREFIX + "unpauseAll";
    String TELL_STATUS = ARIA2_PREFIX + "tellStatus";
    String GET_URIS = ARIA2_PREFIX + "getUris";
    String GET_FILES = ARIA2_PREFIX + "getFiles";
    String GET_PEERS = ARIA2_PREFIX + "getPeers";
    String GET_SERVERS = ARIA2_PREFIX + "getServers";
    String TELL_ACTIVE = ARIA2_PREFIX + "tellActive";
    String TELL_WAITING = ARIA2_PREFIX + "tellWaiting";
    String TELL_STOPPED = ARIA2_PREFIX + "tellStopped";
    String CHANGE_POSITION = ARIA2_PREFIX + "changePosition";
    String CHANGE_URI = ARIA2_PREFIX + "changeUri";
    String GET_OPTION = ARIA2_PREFIX + "getOption";
    String CHANGE_OPTION = ARIA2_PREFIX + "changeOption";
    String GET_GLOBAL_OPTION = ARIA2_PREFIX + "getGlobalOption";
    String CHANGE_GLOBAL_OPTION = ARIA2_PREFIX + "changeGlobalOption";
    String GET_GLOBAL_STAT = ARIA2_PREFIX + "getGlobalStat";
    String PURGE_DOWNLOAD_RESULT = ARIA2_PREFIX + "purgeDownloadResult";
    String REMOVE_DOWNLOAD_RESULT = ARIA2_PREFIX + "removeDownloadResult";
    String GET_VERSION = ARIA2_PREFIX + "getVersion";
    String GET_SESSION_INFO = ARIA2_PREFIX + "getSessionInfo";
    String SHUTDOWN = ARIA2_PREFIX + "shutdown";
    String FORCE_SHUTDOWN = ARIA2_PREFIX + "forceShutdown";
    String SAVE_SESSION = ARIA2_PREFIX + "saveSession";
    String MULTI_CALL = SYSTEM_PREFIX + "multicall";
    String LIST_METHODS = SYSTEM_PREFIX + "listMethods";
    String LIST_NOTIFICATIONS = SYSTEM_PREFIX + "listNotifications";

    /**
     * 将InputStream转换成Base64编码
     *
     * @param inputStream 输入流
     * @return Base64编码
     */
    static String input2Base64(InputStream inputStream) {
        try {
            return Base64.getEncoder().encodeToString(inputStream.readAllBytes());
        } catch (IOException e) {
            return "";
        }
    }

    /**
     * 获取Aria2c的实例
     *
     * @return Aria2c
     */
    static Aria2c run() {
        return run(null);
    }

    /**
     * 获取Aria2c的实例
     *
     * @return Aria2c
     */
    static Aria2c run(List<String> options) {
        Aria2cRpcUtils.start(PathData.ARIA2C, options);
        JsonRpcHttpClient client = null;
        try {
            Integer port = Aria2cRpcOptions.getInstance().getRpcListenPort();
            client = new JsonRpcHttpClient(
                    new URL(String.format("http://127.0.0.1:%s/jsonrpc", port)));
        } catch (MalformedURLException ignored) {
        }
        return ProxyUtil.createClientProxy(
                Aria2c.class.getClassLoader(),
                Aria2c.class,
                client);
    }

    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#aria2.addUri
     * <p>
     * 此方法添加一个新的下载。
     *
     * @param uris     是指向相同资源的HTTP/FTP/SFTP/BitTorrent uri(字符串)数组。
     *                 如果您将指向不同资源的uri混合在一起，那么下载可能会失败或损坏，而不会有aria2报错。
     *                 当添加BitTorrent磁力链的时候, URI必须只有一个元素，它应该是BitTorrent磁力链。
     * @param options  是一个结构体，它的成员是键值对。
     *                 有关更多细节，请参见 https://aria2.github.io/manual/en/html/aria2c.html#id3 。
     * @param position 如果给定位置，它必须是一个从0开始的整数。新的下载将被插入在等待队列的位置。
     *                 如果位置被省略或位置大于队列的当前大小，则新的下载将附加到队列的末尾。
     * @return 此方法返回新注册下载的GID。
     */
    @JsonRpcMethod(ADD_URI)
    String addUri(@NonNull String secret, @NonNull String[] uris,
                  @NonNull Options options, @NonNull Integer position);

    @JsonRpcMethod(ADD_URI)
    String addUri(@NonNull String[] uris);


    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#aria2.addTorrent
     * <p>
     * 此方法通过上传“ .torrent”文件来添加BitTorrent下载。
     * 如果要添加BitTorrent磁力链，请改用aria2.addUri() 方法。
     * 如果--rpc-save-upload-metadata为true，则将上载的数据另存为一个文件，
     * 该文件命名为SHA-1哈希数据的十六进制字符串，再加上--dir 选项 指定的目录中的“ .torrent” 。
     * 例如，文件名可能是 0a3893293e27ac0490424c06de4d09242215f0a6.torrent。
     * 如果已经存在同名文件，它将被覆盖！
     * 如果该文件不能被成功保存或--rpc-save-upload-metadata是false，
     * 通过这种方法添加的下载不会被保存--save-session。
     *
     * @param torrent  必须是base64编码的字符串，其中包含“ .torrent”文件的内容。
     * @param uris     是URI（字符串）的数组。uris用于网络播种。
     *                 对于单个文件种子，URI可以是指向资源的完整URI；
     *                 如果URI以/结尾，则会在torrent文件中添加名称。
     *                 对于多文件种子，将在种子中添加名称和路径以形成每个文件的URI。
     * @param options  是一个结构，其成员是键值对。
     *                 请参见 https://aria2.github.io/manual/en/html/aria2c.html#id3 。
     * @param position 如果位置给定，它必须是一个从0开始的整数。新的下载文件将插入等待队列中的位置。
     *                 如果 位置被省略或位置比所述队列的当前的大小更大，新下载附加到队列的末尾。
     * @return 此方法返回新注册的下载的GID。
     */
    @JsonRpcMethod(ADD_TORRENT)
    String addTorrent(@NonNull String secret, @NonNull String torrent,
                      @NonNull String[] uris, @NonNull Options options,
                      @NonNull Integer position);

    @JsonRpcMethod(ADD_TORRENT)
    String addTorrent(@NonNull String torrent);

    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#aria2.addMetalink
     * <p>
     * 此方法通过上传“ .metalink”文件来添加Metalink下载。
     * 如果--rpc-save-upload-metadata是true，
     * 将上传的数据另存为--dir 选项指定的目录中名为SHA-1数据哈希的十六进制字符串加上“ .metalink”的文件。
     * 例如，文件名可能是 0a3893293e27ac0490424c06de4d09242215f0a6.metalink。
     * 如果已经存在同名文件，它将被覆盖！如果该文件不能被成功保存或--rpc-save-upload-metadata是false，
     * 通过这种方法添加的下载不会被保存 --save-session。
     *
     * @param metalink 是base64编码的字符串，其中包含“ .metalink”文件的内容。
     * @param options  是一个结构，其成员是键值对。
     * @param position 如果给定位置，则它必须是一个从0开始的整数。新下载的文件将插入等待队列中的位置。
     *                 如果 位置被省略或位置比所述队列的当前的大小更大，新下载附加到队列的末尾。
     * @return 此方法返回新注册的GID数组。
     */
    @JsonRpcMethod(ADD_META_LINK)
    String[] addMetaLink(@NonNull String secret, @NonNull String metalink,
                         @NonNull Options options, @NonNull Integer position);

    @JsonRpcMethod(ADD_META_LINK)
    String[] addMetaLink(@NonNull String metalink);

    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#aria2.remove
     * <p>
     * 此方法删除由gid（字符串）表示的下载。
     * 如果指定的下载正在进行中，则会首先停止。
     * 删除的下载的状态变为removed。
     *
     * @param gid gid
     * @return 此方法返回已删除下载的GID。
     */
    @JsonRpcMethod(REMOVE)
    String remove(@NonNull String secret, @NonNull String gid);

    @JsonRpcMethod(REMOVE)
    String remove(@NonNull String gid);

    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#aria2.forceRemove
     * <p>
     * 此方法删除了gid表示的下载。
     * 该方法的行为类似于aria2.remove()，只是aria2.remove()删除了下载文件而不执行任何耗时的操作，
     * 例如首先连接BitTorrent Trackers程序先注销下载文件。
     *
     * @param gid gid
     * @return 此方法返回已删除下载的GID。
     */
    @JsonRpcMethod(FORCE_REMOVE)
    String[] forceRemove(@NonNull String secret, @NonNull String gid);

    @JsonRpcMethod(FORCE_REMOVE)
    String[] forceRemove(@NonNull String gid);

    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#aria2.pause
     * <p>
     * 此方法暂停由gid（字符串）表示的下载。
     * 暂停下载的状态变为paused。
     * 如果下载处于活动状态，则将下载放置在等待队列的前面。状态为时paused。
     * 要将状态更改为waiting，请使用 aria2.unpause()方法。
     *
     * @param gid gid
     * @return 此方法返回已暂停下载的GID
     */
    @JsonRpcMethod(PAUSE)
    String pause(@NonNull String secret, @NonNull String gid);

    @JsonRpcMethod(PAUSE)
    String pause(@NonNull String gid);

    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#aria2.pauseAll
     * <p>
     * 此方法等于调用aria2.pause()每个活动/等待的下载
     *
     * @return 此方法返回OK。
     */
    @JsonRpcMethod(PAUSE_ALL)
    String pauseAll(@NonNull String secret);

    @JsonRpcMethod(PAUSE_ALL)
    String pauseAll();

    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#aria2.forcePause
     * <p>
     * 此方法暂停gid表示的下载。
     * 该方法的行为类似于aria2.pause()，只是在aria2.pause()不执行任何耗时的操作
     * （例如首先连接BitTorrent Trackers程序先注销下载文件）的情况下暂停下载。
     *
     * @param gid gid
     * @return gid
     */
    @JsonRpcMethod(FORCE_PAUSE)
    String forcePause(@NonNull String secret, @NonNull String gid);

    @JsonRpcMethod(FORCE_PAUSE)
    String forcePause(@NonNull String gid);

    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#aria2.forcePauseAll
     * <p>
     * 此方法等于调用aria2.forcePause()每个活动/等待的下载
     *
     * @return 此方法返回OK。
     */
    @JsonRpcMethod(FORCE_PAUSE_ALL)
    String forcePauseAll(@NonNull String secret);

    @JsonRpcMethod(FORCE_PAUSE_ALL)
    String forcePauseAll();

    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#aria2.unpause
     * <p>
     * 此方法将gid（字符串）表示的下载状态从 paused更改为waiting
     * 使下载符合重新启动的条件。
     *
     * @param gid gid
     * @return 此方法返回取消暂停的GID
     */
    @JsonRpcMethod(UNPAUSE)
    String unpause(@NonNull String secret, @NonNull String gid);

    @JsonRpcMethod(UNPAUSE)
    String unpause(@NonNull String gid);

    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#aria2.unpauseAll
     * <p>
     * 此方法等于调用aria2.unpause()每个暂停的下载
     *
     * @return 此方法返回OK。
     */
    @JsonRpcMethod(UNPAUSE_ALL)
    String unpauseAll(@NonNull String secret);

    @JsonRpcMethod(UNPAUSE_ALL)
    String unpauseAll();

    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#aria2.tellStatus
     * <p>
     * 此方法返回以gid（字符串）表示的下载进度。
     *
     * @param gid  gid
     * @param keys 是一个字符串数组。如果指定，则响应仅在keys数组中包含键。
     *             如果键为空或省略，则响应包含所有键。
     *             当您只需要特定的键并避免不必要的转移时，这很有用。
     *             例如，仅返回gid和状态键aria2.tellStatus("2089b05ecca3d829", ["gid", "status"])。
     *             响应是一个键值对结构。
     * @return DownloadStatus
     */
    @JsonRpcMethod(TELL_STATUS)
    Aria2cStatus tellStatus(@NonNull String secret, @NonNull String gid,
                            @NonNull String[] keys);

    @JsonRpcMethod(TELL_STATUS)
    Aria2cStatus tellStatus(@NonNull String gid);

    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#aria2.getUris
     * <p>
     * 此方法返回由gid（字符串）表示的下载中使用的URI 。
     *
     * @param gid gid
     * @return 响应是一个UriItem数组
     */
    @JsonRpcMethod(GET_URIS)
    Aria2cUri[] getUris(@NonNull String secret, @NonNull String gid);

    @JsonRpcMethod(GET_URIS)
    Aria2cUri[] getUris(@NonNull String gid);

    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#aria2.getFiles
     * <p>
     * 此方法返回以gid（字符串）表示的下载文件列表
     *
     * @param gid gid
     * @return 响应是一个FileItem数组
     */
    @JsonRpcMethod(GET_FILES)
    Aria2cFile[] getFiles(@NonNull String secret, @NonNull String gid);

    @JsonRpcMethod(GET_FILES)
    Aria2cFile[] getFiles(@NonNull String gid);

    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#aria2.getPeers
     * <p>
     * 此方法返回由gid（字符串）表示的下载列表的同级对象。
     * 此方法仅适用于BitTorrent。
     *
     * @param gid gid
     * @return 响应是一个PeerItem数组
     */
    @JsonRpcMethod(GET_PEERS)
    Aria2cPeer[] getPeers(@NonNull String secret, @NonNull String gid);

    @JsonRpcMethod(GET_PEERS)
    Aria2cPeer[] getPeers(@NonNull String gid);

    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#aria2.getServers
     * <p>
     * 此方法返回以gid（字符串）表示的下载的当前连接的HTTP（S）/ FTP / SFTP服务器。
     *
     * @param gid gid
     * @return 响应是一个ServerItem数组
     */
    @JsonRpcMethod(GET_SERVERS)
    Aria2cServer[] getServers(@NonNull String secret, @NonNull String gid);

    @JsonRpcMethod(GET_SERVERS)
    Aria2cServer[] getServers(@NonNull String gid);

    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#aria2.tellActive
     * <p>
     * 此方法返回活动下载列表。
     * 响应是与aria2.tellStatus()方法返回的结构相同的数组。
     * 对于keys参数，请参考该aria2.tellStatus()方法。
     *
     * @return DownloadStatus[]
     */
    @JsonRpcMethod(TELL_ACTIVE)
    Aria2cStatus[] tellActive(@NonNull String secret, @NonNull String[] keys);

    @JsonRpcMethod(TELL_ACTIVE)
    Aria2cStatus[] tellActive();

    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#aria2.tellWaiting
     * <p>
     * 此方法返回等待下载的列表，包括暂停的下载。
     * 如果offset是一个正整数，则此方法返回在[ offset，offset + num）范围内的下载。
     * offset可以为负整数。
     * offset == -1指向等待队列中的最后一次下载，
     * offset == -2指向最后一次下载前的下载，依此类推。
     * 然后，响应中的下载​​将以相反的顺序进行。
     * 例如，假设三个下载“ A”，“ B”和“ C”按此顺序等待。
     * aria2.tellWaiting(0, 1)返回["A"]
     * aria2.tellWaiting(1, 2)返回["B", "C"]
     * aria2.tellWaiting(-1, 2)返回["C", "B"]
     *
     * @param offset 是一个整数，它指定从前面等待下载的偏移量。
     * @param num    是整数，并指定最大值。返回的下载数量。
     * @param keys   对于keys参数，请参考该aria2.tellStatus()方法。
     * @return 响应是与aria2.tellStatus()方法返回的结构相同的数组 。
     */
    @JsonRpcMethod(TELL_WAITING)
    Aria2cStatus[] tellWaiting(@NonNull String secret, @NonNull Integer offset,
                               @NonNull Integer num, @NonNull String[] keys);

    @JsonRpcMethod(TELL_WAITING)
    Aria2cStatus[] tellWaiting(@NonNull Integer offset, @NonNull Integer num);

    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#aria2.tellStopped
     * <p>
     * 此方法返回已停止下载的列表。
     * offset和num与aria2.tellWaiting()方法中描述的语义相同 。
     *
     * @param offset 是一个整数，指定距最近停止下载的位置的偏移量。
     * @param num    是整数，并指定最大值。返回的下载数量。
     * @param keys   对于keys参数，请参考该aria2.tellStatus()方法。
     * @return 响应是与aria2.tellStatus()方法返回的结构相同的数组 。
     */
    @JsonRpcMethod(TELL_STOPPED)
    Aria2cStatus[] tellStopped(@NonNull String secret, @NonNull Integer offset,
                               @NonNull Integer num, @NonNull String[] keys);

    @JsonRpcMethod(TELL_STOPPED)
    Aria2cStatus[] tellStopped(@NonNull Integer offset, @NonNull Integer num);

    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#aria2.changePosition
     * <p>
     * 此方法更改队列中gid表示的下载位置 ，注意移动的是等待队列
     * <p>
     * 例如，如果GID＃2089b05ecca3d829当前位于位置3， 则将其位置更改为2。
     * aria2.changePosition('2089b05ecca3d829', -1, 'POS_CUR')
     * 然后，将其位置更改为0（队列的开始）。
     * aria2.changePosition('2089b05ecca3d829', 0, 'POS_SET')
     *
     * @param pos 是一个整数,
     *            如果目标位置小于0或超出队列的末尾，则它将下载分别移至队列的开始或末尾。
     * @param how 如果how是POS_SET，它会将下载移动到相对于下载队列的开始位置。
     *            如果how是POS_CUR，它将移动下载到相对于当前位置规定的步数。
     *            如果how是POS_END，它将移动下载到相对于队列末尾的位置。
     * @return 响应是表示结果位置的整数
     */
    @JsonRpcMethod(CHANGE_POSITION)
    Integer changePosition(@NonNull String secret, @NonNull String gid,
                           @NonNull Integer pos, @NonNull String how);

    @JsonRpcMethod(CHANGE_POSITION)
    Integer changePosition(@NonNull String gid, @NonNull Integer pos,
                           @NonNull String how);

    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#aria2.changeUri
     * <p>
     * 此方法从delUris中删除uri，并将addUris中的uri附加到以gid表示的下载中。
     * delUris和addUris是字符串列表。
     * 下载可以包含多个文件，每个文件都附加了uri
     *
     * @param fileIndex 用于选择要删除/附加哪个文件。fileIndex起始下标为1。
     * @param position  用于指定在现有的等待URI列表中插入URI的位置。起始下标为0。
     *                  当position被省略时，uri被附加到列表的后面。
     *                  这个方法首先执行删除，然后执行添加。
     *                  position是删除uri后的位置，而不是调用此方法时的位置。
     *                  在删除URI时，如果下载中存在相同的URI，
     *                  则对于deluri中的每个URI只删除一个URI。
     *                  换句话说，如果有三个uri http://example.org/aria2，
     *                  并且您希望将它们全部删除，
     *                  那么您必须在delUris中指定(至少)3个http://example.org/aria2。
     * @return 这个方法返回一个包含两个整数的列表。第一个整数是删除uri的数目。第二个整数是添加的uri的数量。
     */
    @JsonRpcMethod(CHANGE_URI)
    Integer[] changeUri(@NonNull String secret, @NonNull String gid, @NonNull Integer fileIndex,
                        @NonNull String[] delUris, @NonNull String[] addUris, @NonNull Integer position);

    @JsonRpcMethod(CHANGE_URI)
    Integer[] changeUri(@NonNull String gid, @NonNull Integer fileIndex,
                        @NonNull String[] delUris, @NonNull String[] addUris);

    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#aria2.getOption
     * <p>
     * 此方法返回以gid表示的下载选项。
     * 响应是一个结构，其中键是选项的名称。值是字符串。
     * 请注意，此方法不会在配置文件或RPC方法中返回没有默认值且未在命令行上设置的选项。
     *
     * @return OptionItem
     */
    @JsonRpcMethod(GET_OPTION)
    Aria2cOption getOption(@NonNull String gid);

    @JsonRpcMethod(GET_OPTION)
    Aria2cOption getOption(@NonNull String secret, @NonNull String gid);

    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#aria2.changeOption
     * <p>
     * 此方法动态更改由gid（字符串）表示的下载选项。
     *
     * @param gid     gid
     * @param options Options类中列出的选项可用
     *                但以下选项除外：
     *                dry-run
     *                metalink-base-uri
     *                parameterized-uri
     *                pause
     *                piece-length
     *                rpc-save-upload-metadata
     *                bt-max-peers
     *                bt-request-peer-speed-limit
     *                bt-remove-unselected-file
     *                force-save
     *                max-download-limit
     *                max-upload-limit
     * @return OK
     */
    @JsonRpcMethod(CHANGE_OPTION)
    String changeOption(@NonNull String secret, @NonNull String gid,
                        @NonNull Options options);

    @JsonRpcMethod(CHANGE_OPTION)
    String changeOption(@NonNull String gid, @NonNull Options options);

    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#aria2.getGlobalOption
     * 此方法返回全局选项。
     * 响应是一个结构。它的键是选项的名称。值是字符串。
     * 请注意，此方法不会在配置文件或RPC方法中返回没有默认值且未在命令行上设置的选项。
     * 因为全局选项用作新添加的下载选项的模板，所以响应包含该aria2.getOption()方法返回的键。
     *
     * @return OptionItem
     */
    @JsonRpcMethod(GET_GLOBAL_OPTION)
    Aria2cOption getGlobalOption(@NonNull String secret);

    @JsonRpcMethod(GET_GLOBAL_OPTION)
    Aria2cOption getGlobalOption();

    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#aria2.changeGlobalOption
     * <p>
     * 此方法动态更改全局选项。
     *
     * @param options 是一个结构，可以使用GlobalOptions构建
     *                bt-max-open-files
     *                download-result
     *                keep-unfinished-download-result
     *                log
     *                log-level
     *                max-concurrent-downloads
     *                max-download-result
     *                max-overall-download-limit
     *                max-overall-upload-limit
     *                optimize-concurrent-downloads
     *                save-cookies
     *                save-session
     *                server-stat-of
     *                <p>
     *                此外，在列出的选项中输入文件款可供选择
     *                除了用于下列选项：
     *                checksum
     *                index-out
     *                out
     *                pause
     *                select-file。
     *                使用该log选项，您可以动态启动日志记录或更改日志文件。
     *                要停止记录，请指定一个空字符串（“”）作为参数值。
     *                请注意，日志文件始终以附加模式打开。此方法返回OK成功。
     * @return OK
     */
    @JsonRpcMethod(CHANGE_GLOBAL_OPTION)
    String changeGlobalOption(@NonNull String secret, @NonNull Options options);

    @JsonRpcMethod(CHANGE_GLOBAL_OPTION)
    String changeGlobalOption(@NonNull Options options);

    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#aria2.getGlobalStat
     * 此方法返回全局统计信息，例如总体下载和上传速度。
     *
     * @return GlobalStat
     */
    @JsonRpcMethod(GET_GLOBAL_STAT)
    Aria2cGlobalStat getGlobalStat(@NonNull String secret);

    @JsonRpcMethod(GET_GLOBAL_STAT)
    Aria2cGlobalStat getGlobalStat();

    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#aria2.purgeDownloadResult
     * <p>
     * 此方法将completed/error/removed的下载清除到可用内存中。此方法返回OK。
     *
     * @return OK
     */
    @JsonRpcMethod(PURGE_DOWNLOAD_RESULT)
    String purgeDownloadResult(@NonNull String secret);

    @JsonRpcMethod(PURGE_DOWNLOAD_RESULT)
    String purgeDownloadResult();

    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#aria2.removeDownloadResult
     * <p>
     * 此方法 从内存中删除由gid表示的completed/error/removed下载。此方法返回OK成功。
     *
     * @return OK
     */
    @JsonRpcMethod(REMOVE_DOWNLOAD_RESULT)
    String removeDownloadResult(@NonNull String secret, @NonNull String gid);

    @JsonRpcMethod(REMOVE_DOWNLOAD_RESULT)
    String removeDownloadResult(@NonNull String gid);

    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#aria2.getVersion
     * 此方法返回aria2的版本和已启用功能的列表
     *
     * @return Aria2cVersion
     */
    @JsonRpcMethod(GET_VERSION)
    Aria2cVersion getVersion(@NonNull String secret);

    @JsonRpcMethod(GET_VERSION)
    Aria2cVersion getVersion();

    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#aria2.getSessionInfo
     *
     * @return Aria2cSession
     */
    @JsonRpcMethod(GET_SESSION_INFO)
    Aria2cSession getSessionInfo(@NonNull String secret);

    @JsonRpcMethod(GET_SESSION_INFO)
    Aria2cSession getSessionInfo();

    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#aria2.shutdown
     * <p>
     * 此方法关闭aria2。此方法返回OK。
     *
     * @return OK
     */
    @JsonRpcMethod(SHUTDOWN)
    String shutdown(@NonNull String secret);

    @JsonRpcMethod(SHUTDOWN)
    String shutdown();

    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#aria2.forceShutdown
     * <p>
     * 此方法将关闭aria2()。
     * 此方法的行为类似于方法：'aria2.shutdown`，而无需执行任何耗时的操作，
     * 例如首先连接BitTorrent Trackers程序先注销下载文件。
     *
     * @return OK
     */
    @JsonRpcMethod(FORCE_SHUTDOWN)
    String forceShutdown(@NonNull String secret);

    @JsonRpcMethod(FORCE_SHUTDOWN)
    String forceShutdown();

    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#aria2.saveSession
     * <p>
     * 此方法将当前会话保存到该--save-session选项指定的文件中
     *
     * @return OK
     */
    @JsonRpcMethod(SAVE_SESSION)
    String saveSession(@NonNull String secret);

    @JsonRpcMethod(SAVE_SESSION)
    String saveSession();

    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#system.multicall
     * <p>
     * 此方法将多个方法调用封装在单个请求中
     * 方法是一个结构数组。
     * 该结构包含两个键： methodName和params。
     * methodName是要调用的方法名称，
     * params是包含方法调用参数的数组。
     *
     * @return 此方法返回响应数组。
     * 如果封装的方法调用失败，则元素将是包含方法调用的返回值的单项数组，或者是故障元素的结构。
     */
    @JsonRpcMethod(MULTI_CALL)
    Object[] multicall(Aria2cCall... methods);

    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#system.listMethods
     * <p>
     * 此方法以字符串数组形式返回所有可用的RPC方法。
     * 与其他方法不同，此方法不需要秘密令牌。
     * 这是安全的，因为此方法仅返回可用的方法名称。
     *
     * @return 方法名称数组
     */
    @JsonRpcMethod(LIST_METHODS)
    String[] listMethods();

    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#system.listNotifications
     * <p>
     * 此方法以字符串数组形式返回所有可用的RPC通知。
     * 与其他方法不同，此方法不需要秘密令牌。
     * 这是安全的，因为此方法仅返回可用的通知名称
     *
     * @return 通知名称数组
     */
    @JsonRpcMethod(LIST_NOTIFICATIONS)
    String[] listNotifications();
}
