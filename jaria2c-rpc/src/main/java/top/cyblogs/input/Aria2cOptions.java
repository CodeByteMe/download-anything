package top.cyblogs.input;

import lombok.Builder;
import top.cyblogs.support.OptionName;

/**
 * [Entity]
 * 用作大部分方法的options参数
 * ========================
 * https://aria2.github.io/manual/en/html/aria2c.html#input-file
 * header和index-out 选项允许在命令行多次。
 * 由于key在Map中应该是唯一的（许多XML-RPC库实现对结构使用哈希或dict），因此仅一个字符串是不够的。
 * 为了克服此限制，您可以使用数组作为值以及字符串。
 * 可选参数有110个
 * <p>
 * TODO 未进行文档人工翻译
 */
@Builder
public class Aria2cOptions {

    /*======================Input File部分=========================*/
    /*https://aria2.github.io/manual/en/html/aria2c.html#input-file*/

    /**
     * 对所有协议使用代理服务器。
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-all-proxy
     */
    @OptionName(value = "all-proxy")
    private String allProxy;

    /**
     * 设置all-proxy选项密码
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-all-proxy-passwd
     */
    @OptionName(value = "all-proxy-passwd")
    private String allProxyPasswd;

    /**
     * 设置all-proxy选项的用户
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-all-proxy-user
     */
    @OptionName(value = "all-proxy-user")
    private String allProxyUser;

    /**
     * 如果相应的控制文件不存在，请从头开始重新下载
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-allow-overwrite
     */
    @OptionName(value = "allow-overwrite")
    private Boolean allowOverwrite;

    /**
     * 如果给出false，则当片段长度与控制文件中的片段长度不同时，aria2将中止下载。
     * 如果给出true，则可以继续进行，但是某些下载进度将丢失。默认：false
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-allow-piece-length-change
     */
    @OptionName(value = "allow-piece-length-change")
    private Boolean allowPieceLengthChange;

    /**
     * 是否总是恢复下载
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-always-resume
     */
    @OptionName(value = "always-resume")
    private Boolean alwaysResume;

    /**
     * 启用异步DNS。默认：true
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-async-dns
     */
    @OptionName(value = "async-dns")
    private Boolean asyncDns;

    /**
     * 如果相同的文件已经存在，请重命名文件名。
     * 此选项仅在HTTP（S）/ FTP下载中有效。
     * 新文件名在名称之后-在文件扩展名之前（如果有的话）带有一个点和一个数字（1-9999）。默认：true
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-auto-file-renaming
     */
    @OptionName(value = "auto-file-renaming")
    private Boolean autoFileRenaming;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-bt-enable-hook-after-hash-check
     */
    @OptionName(value = "bt-enable-hook-after-hash-check")
    private Boolean btEnableHookAfterHashCheck;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-bt-enable-lpd
     */
    @OptionName(value = "bt-enable-lpd")
    private Boolean btEnableLpd;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-bt-exclude-tracker
     */
    @OptionName(value = "bt-exclude-tracker")
    private String btExcludeTracker;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-bt-external-ip
     */
    @OptionName(value = "bt-external-ip")
    private String btExternalIp;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-bt-force-encryption
     */
    @OptionName(value = "bt-force-encryption")
    private Boolean btForceEncryption;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-bt-hash-check-seed
     */
    @OptionName(value = "bt-hash-check-seed")
    private Boolean btHashCheckSeed;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-bt-load-saved-metadata
     */
    @OptionName(value = "bt-load-saved-metadata")
    private Boolean btLoadSavedMetadata;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-bt-max-peers
     */
    @OptionName(value = "bt-max-peers")
    private int btMaxPeers;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-bt-metadata-only
     */
    @OptionName(value = "bt-metadata-only")
    private Boolean btMetadataOnly;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-bt-min-crypto-level
     */
    @OptionName(value = "bt-min-crypto-level")
    private String btMinCryptoLevel;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-bt-prioritize-piece
     */
    @OptionName(value = "bt-prioritize-piece")
    private String btPrioritizePiece;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-bt-remove-unselected-file
     */
    @OptionName(value = "bt-remove-unselected-file")
    private Boolean btRemoveUnselectedFile;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-bt-request-peer-speed-limit
     */
    @OptionName(value = "bt-request-peer-speed-limit")
    private String btRequestPeerSpeedLimit;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-bt-require-crypto
     */
    @OptionName(value = "bt-require-crypto")
    private Boolean btRequireCrypto;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-bt-save-metadata
     */
    @OptionName(value = "bt-save-metadata")
    private Boolean btSaveMetadata;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-bt-seed-unverified
     */
    @OptionName(value = "bt-seed-unverified")
    private Boolean btSeedUnverified;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-bt-stop-timeout
     */
    @OptionName(value = "bt-stop-timeout")
    private long btStopTimeout;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-bt-tracker
     */
    @OptionName(value = "bt-tracker")
    private String btTracker;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-bt-tracker-connect-timeout
     */
    @OptionName(value = "bt-tracker-connect-timeout")
    private long btTrackerConnectTimeout;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-bt-tracker-interval
     */
    @OptionName(value = "bt-tracker-interval")
    private long btTrackerInterval;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-bt-tracker-timeout
     */
    @OptionName(value = "bt-tracker-timeout")
    private long btTrackerTimeout;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-v
     */
    @OptionName(value = "check-integrity")
    private Boolean checkIntegrity;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-checksum
     */
    @OptionName(value = "checksum")
    private String checksum;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-conditional-get
     */
    @OptionName(value = "conditional-get")
    private Boolean conditionalGet;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-connect-timeout
     */
    @OptionName(value = "connect-timeout")
    private long connectTimeout;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-content-disposition-default-utf8
     */
    @OptionName(value = "content-disposition-default-utf8")
    private Boolean contentDispositionDefaultUtf8;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-c
     */
    @OptionName(value = "continue")
    private Boolean continueX;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-d
     */
    @OptionName(value = "dir")
    private String dir;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-dry-run
     */
    @OptionName(value = "dry-run")
    private Boolean dryRun;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-enable-http-keep-alive
     */
    @OptionName(value = "enable-http-keep-alive")
    private Boolean enableHttpKeepAlive;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-enable-http-pipelining
     */
    @OptionName(value = "enable-http-pipelining")
    private Boolean enableHttpPipelining;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-enable-mmap
     */
    @OptionName(value = "enable-mmap")
    private Boolean enableMmap;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-enable-peer-exchange
     */
    @OptionName(value = "enable-peer-exchange")
    private Boolean enablePeerExchange;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-file-allocation
     */
    @OptionName(value = "file-allocation")
    private String fileAllocation;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-follow-metalink
     */
    @OptionName(value = "follow-metalink")
    private Boolean followMetalink;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-follow-torrent
     */
    @OptionName(value = "follow-torrent")
    private Boolean followTorrent;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-force-save
     */
    @OptionName(value = "force-save")
    private Boolean forceSave;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-ftp-passwd
     */
    @OptionName(value = "ftp-passwd")
    private String ftpPasswd;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-p
     */
    @OptionName(value = "ftp-pasv")
    private Boolean ftpPasv;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-ftp-proxy
     */
    @OptionName(value = "ftp-proxy")
    private String ftpProxy;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-ftp-proxy-passwd
     */
    @OptionName(value = "ftp-proxy-passwd")
    private String ftpProxyPasswd;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-ftp-proxy-user
     */
    @OptionName(value = "ftp-proxy-user")
    private String ftpProxyUser;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-ftp-reuse-connection
     */
    @OptionName(value = "ftp-reuse-connection")
    private Boolean ftpReuseConnection;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-ftp-type
     */
    @OptionName(value = "ftp-type")
    private String ftpType;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-ftp-user
     */
    @OptionName(value = "ftp-user")
    private String ftpUser;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-gid
     */
    @OptionName(value = "gid")
    private String gid;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-hash-check-only
     */
    @OptionName(value = "hash-check-only")
    private Boolean hashCheckOnly;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-header
     */
    @OptionName(value = "header")
    private String[] header;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-http-accept-gzip
     */
    @OptionName(value = "http-accept-gzip")
    private Boolean httpAcceptGzip;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-http-auth-challenge
     */
    @OptionName(value = "http-auth-challenge")
    private Boolean httpAuthChallenge;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-http-no-cache
     */
    @OptionName(value = "http-no-cache")
    private Boolean httpNoCache;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-http-passwd
     */
    @OptionName(value = "http-passwd")
    private String httpPasswd;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-http-proxy
     */
    @OptionName(value = "http-proxy")
    private String httpProxy;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-http-proxy-passwd
     */
    @OptionName(value = "http-proxy-passwd")
    private String httpProxyPasswd;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-http-proxy-user
     */
    @OptionName(value = "http-proxy-user")
    private String httpProxyUser;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-http-user
     */
    @OptionName(value = "http-user")
    private String httpUser;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-https-proxy
     */
    @OptionName(value = "https-proxy")
    private String httpsProxy;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-https-proxy-passwd
     */
    @OptionName(value = "https-proxy-passwd")
    private String httpsProxyPasswd;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-https-proxy-user
     */
    @OptionName(value = "https-proxy-user")
    private String httpsProxyUser;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-index-out
     */
    @OptionName(value = "index-out")
    private String[] indexOut;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-lowest-speed-limit
     */
    @OptionName(value = "lowest-speed-limit")
    private String lowestSpeedLimit;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-x
     */
    @OptionName(value = "max-connection-per-server")
    private long maxConnectionPerServer;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-max-download-limit
     */
    @OptionName(value = "max-download-limit")
    private String maxDownloadLimit;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-max-file-not-found
     */
    @OptionName(value = "max-file-not-found")
    private long maxFileNotFound;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-max-mmap-limit
     */
    @OptionName(value = "max-mmap-limit")
    private long maxMmapLimit;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-max-resume-failure-tries
     */
    @OptionName(value = "max-resume-failure-tries")
    private int maxResumeFailureTries;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-m
     */
    @OptionName(value = "max-tries")
    private int maxTries;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-max-upload-limit
     */
    @OptionName(value = "max-upload-limit")
    private String maxUploadLimit;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-metalink-base-uri
     */
    @OptionName(value = "metalink-base-uri")
    private String metalinkBaseUri;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-metalink-enable-unique-protocol
     */
    @OptionName(value = "metalink-enable-unique-protocol")
    private Boolean metalinkEnableUniqueProtocol;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-metalink-language
     */
    @OptionName(value = "metalink-language")
    private String metalinkLanguage;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-metalink-location
     */
    @OptionName(value = "metalink-location")
    private String metalinkLocation;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-metalink-os
     */
    @OptionName(value = "metalink-os")
    private String metalinkOs;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-metalink-preferred-protocol
     */
    @OptionName(value = "metalink-preferred-protocol")
    private String metalinkPreferredProtocol;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-metalink-version
     */
    @OptionName(value = "metalink-version")
    private String metalinkVersion;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-k
     */
    @OptionName(value = "min-split-size")
    private String minSplitSize;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-no-file-allocation-limit
     */
    @OptionName(value = "no-file-allocation-limit")
    private String noFileAllocationLimit;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-n
     */
    @OptionName(value = "no-netrc")
    private Boolean noNetrc;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-no-proxy
     */
    @OptionName(value = "no-proxy")
    private String noProxy;
    /**
     * 下载文件的文件名。它始终相对于--dir选项中给定的目录。
     * 当 --force-sequential使用选项，这个选项被忽略。
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-o
     */
    @OptionName(value = "out")
    private String out;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-parameterized-uri
     */
    @OptionName(value = "parameterized-uri")
    private Boolean parameterizedUri;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-pause
     */
    @OptionName(value = "pause")
    private Boolean pause;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-pause-metadata
     */
    @OptionName(value = "pause-metadata")
    private Boolean pauseMetadata;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-piece-length
     */
    @OptionName(value = "piece-length")
    private String pieceLength;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-proxy-method
     */
    @OptionName(value = "proxy-method")
    private String proxyMethod;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-realtime-chunk-checksum
     */
    @OptionName(value = "realtime-chunk-checksum")
    private Boolean realtimeChunkChecksum;
    /**
     * 设置一个http Referrer（Referer）。
     * <p>
     * 这会影响所有http / https下载。如果*给出，则下载URI会用作referer。
     * 与--parameterized-uri选件一起使用时，这可能很有用 。
     * <p>
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-referer
     */
    @OptionName(value = "referer")
    private String referer;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-r
     */
    @OptionName(value = "remote-time")
    private Boolean remoteTime;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-remove-control-file
     */
    @OptionName(value = "remove-control-file")
    private Boolean removeControlFile;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-retry-wait
     */
    @OptionName(value = "retry-wait")
    private long retryWait;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-reuse-uri
     */
    @OptionName(value = "reuse-uri")
    private Boolean reuseUri;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-rpc-save-upload-metadata
     */
    @OptionName(value = "rpc-save-upload-metadata")
    private Boolean rpcSaveUploadMetadata;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-seed-ratio
     */
    @OptionName(value = "seed-ratio")
    private double seedRatio;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-seed-time
     */
    @OptionName(value = "seed-time")
    private long seedTime;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-select-file
     */
    @OptionName(value = "select-file")
    private String selectFile;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-s
     */
    @OptionName(value = "split")
    private int split;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-ssh-host-key-md
     */
    @OptionName(value = "ssh-host-key-md")
    private String sshHostKeyMd;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-stream-piece-selector
     */
    @OptionName(value = "stream-piece-selector")
    private String streamPieceSelector;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-t
     */
    @OptionName(value = "timeout")
    private long timeout;
    /**
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-uri-selector
     */
    @OptionName(value = "uri-selector")
    private String uriSelector;
    /**
     * 对HTTP服务器的第一个请求使用HEAD方法。默认：false
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-use-head
     */
    @OptionName(value = "use-head")
    private Boolean useHead;
    /**
     * 设置HTTP（S）下载的User-Agent。
     * 默认值：aria2/$VERSION $VERSION替换为软件包版本。
     * https://aria2.github.io/manual/en/html/aria2c.html#cmdoption-u
     */
    @OptionName(value = "user-agent")
    private String userAgent;


    /*================TODO 没有添加Option解释==========*/
    /*https://aria2.github.io/manual/en/html/aria2c.html#aria2.changeGlobalOption*/
    /*=================Global Option=================*/

    @OptionName("bt-max-open-files")
    private long btMaxOpenFiles;

    @OptionName("download-result")
    private String downloadResult;

    @OptionName("keep-unfinished-download-result")
    private Boolean keepUnfinishedDownloadResult;

    @OptionName("log")
    private String log;

    @OptionName("log-level")
    private String logLevel;

    @OptionName("max-concurrent-downloads")
    private long maxConcurrentDownloads;

    @OptionName("max-download-result")
    private long maxDownloadResult;

    @OptionName("max-overall-download-limit")
    private long maxOverallDownloadLimit;

    @OptionName("max-overall-upload-limit")
    private long maxOverallUploadLimit;

    @OptionName("optimize-concurrent-downloads")
    private Boolean optimizeConcurrentDownloads;

    @OptionName("save-cookies")
    private String saveCookies;

    @OptionName("save-session")
    private String saveSession;

    @OptionName("server-stat-of")
    private String serverStatOf;
}
