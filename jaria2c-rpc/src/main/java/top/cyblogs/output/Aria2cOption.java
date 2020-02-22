package top.cyblogs.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

/**
 * [Entity]
 * aria2.getOption()和aria2.getGlobalOption()方法的返回值
 * ====================================================
 * https://aria2.github.io/manual/en/html/aria2c.html#aria2.getOption
 * https://aria2.github.io/manual/en/html/aria2c.html#aria2.getGlobalOption
 * <p>
 * <p>
 * TODO 未进行文档人工翻译
 */
@Getter
@ToString
public class Aria2cOption {

    @JsonProperty("allow-overwrite")
    private Boolean allowOverwrite;

    @JsonProperty("allow-piece-length-change")
    private Boolean allowPieceLengthChange;

    @JsonProperty("always-resume")
    private Boolean alwaysResume;

    @JsonProperty("async-dns")
    private Boolean asyncDns;

    @JsonProperty("auto-file-renaming")
    private Boolean autoFileRenaming;

    @JsonProperty("bt-enable-hook-after-hash-check")
    private Boolean btEnableHookAfterHashCheck;

    @JsonProperty("bt-enable-lpd")
    private Boolean btEnableLpd;

    @JsonProperty("bt-force-encryption")
    private Boolean btForceEncryption;

    @JsonProperty("bt-hash-check-seed")
    private Boolean btHashCheckSeed;

    @JsonProperty("bt-load-saved-metadata")
    private Boolean btLoadSavedMetadata;

    @JsonProperty("bt-max-peers")
    private Long btMaxPeers;

    @JsonProperty("bt-metadata-only")
    private Boolean btMetadataOnly;

    @JsonProperty("bt-min-crypto-level")
    private String btMinCryptoLevel;

    @JsonProperty("bt-remove-unselected-file")
    private Boolean btRemoveUnselectedFile;

    @JsonProperty("bt-request-peer-speed-limit")
    private Long btRequestPeerSpeedLimit;

    @JsonProperty("bt-require-crypto")
    private Boolean btRequireCrypto;

    @JsonProperty("bt-save-metadata")
    private Boolean btSaveMetadata;

    @JsonProperty("bt-seed-unverified")
    private Boolean btSeedUnverified;

    @JsonProperty("bt-stop-timeout")
    private Long btStopTimeout;

    @JsonProperty("bt-tracker-connect-timeout")
    private Long btTrackerConnectTimeout;

    @JsonProperty("bt-tracker-interval")
    private Long btTrackerInterval;

    @JsonProperty("bt-tracker-timeout")
    private Long btTrackerTimeout;

    @JsonProperty("check-integrity")
    private Boolean checkIntegrity;

    @JsonProperty("conditional-get")
    private Boolean conditionalGet;

    @JsonProperty("connect-timeout")
    private Long connectTimeout;

    @JsonProperty("content-disposition-default-utf8")
    private Boolean contentDispositionDefaultUtf8;

    @JsonProperty("continue")
    private Boolean continueX;

    @JsonProperty("dir")
    private String dir;

    @JsonProperty("dry-run")
    private Boolean dryRun;

    @JsonProperty("enable-http-keep-alive")
    private Boolean enableHttpKeepAlive;

    @JsonProperty("enable-http-pipelining")
    private Boolean enableHttpPipelining;

    @JsonProperty("enable-mmap")
    private Boolean enableMmap;

    @JsonProperty("enable-peer-exchange")
    private Boolean enablePeerExchange;

    @JsonProperty("file-allocation")
    private String fileAllocation;

    @JsonProperty("follow-metalink")
    private Boolean followMetalink;

    @JsonProperty("follow-torrent")
    private Boolean followTorrent;

    @JsonProperty("force-save")
    private Boolean forceSave;

    @JsonProperty("ftp-pasv")
    private Boolean ftpPasv;

    @JsonProperty("ftp-reuse-connection")
    private Boolean ftpReuseConnection;

    @JsonProperty("ftp-type")
    private String ftpType;

    @JsonProperty("hash-check-only")
    private Boolean hashCheckOnly;

    @JsonProperty("http-accept-gzip")
    private Boolean httpAcceptGzip;

    @JsonProperty("http-auth-challenge")
    private Boolean httpAuthChallenge;

    @JsonProperty("http-no-cache")
    private Boolean httpNoCache;

    @JsonProperty("lowest-speed-limit")
    private Long lowestSpeedLimit;

    @JsonProperty("max-connection-per-server")
    private Long maxConnectionPerServer;

    @JsonProperty("max-download-limit")
    private Long maxDownloadLimit;

    @JsonProperty("max-file-not-found")
    private Long maxFileNotFound;

    @JsonProperty("max-mmap-limit")
    private Long maxMmapLimit;

    @JsonProperty("max-resume-failure-tries")
    private Long maxResumeFailureTries;

    @JsonProperty("max-tries")
    private Long maxTries;

    @JsonProperty("max-upload-limit")
    private Long maxUploadLimit;

    @JsonProperty("metalink-enable-unique-protocol")
    private Boolean metalinkEnableUniqueProtocol;

    @JsonProperty("metalink-preferred-protocol")
    private String metalinkPreferredProtocol;

    @JsonProperty("min-split-size")
    private Long minSplitSize;

    @JsonProperty("no-file-allocation-limit")
    private Long noFileAllocationLimit;

    @JsonProperty("no-netrc")
    private Boolean noNetrc;

    @JsonProperty("parameterized-uri")
    private Boolean parameterizedUri;

    @JsonProperty("pause-metadata")
    private Boolean pauseMetadata;

    @JsonProperty("piece-length")
    private Long pieceLength;

    @JsonProperty("proxy-method")
    private String proxyMethod;

    @JsonProperty("realtime-chunk-checksum")
    private Boolean realtimeChunkChecksum;

    @JsonProperty("referer")
    private String referer;

    @JsonProperty("remote-time")
    private Boolean remoteTime;

    @JsonProperty("remove-control-file")
    private Boolean removeControlFile;

    @JsonProperty("retry-wait")
    private Long retryWait;

    @JsonProperty("reuse-uri")
    private Boolean reuseUri;

    @JsonProperty("rpc-save-upload-metadata")
    private Boolean rpcSaveUploadMetadata;

    @JsonProperty("save-not-found")
    private Boolean saveNotFound;

    @JsonProperty("seed-ratio")
    private Double seedRatio;

    @JsonProperty("split")
    private Long split;

    @JsonProperty("stream-piece-selector")
    private String streamPieceSelector;

    @JsonProperty("timeout")
    private Long timeout;

    @JsonProperty("uri-selector")
    private String uriSelector;

    @JsonProperty("use-head")
    private Boolean useHead;

    @JsonProperty("user-agent")
    private String userAgent;

    @JsonProperty("auto-save-interval")
    private Long autoSaveInterval;

    @JsonProperty("bt-detach-seed-only")
    private Boolean btDetachSeedOnly;

    @JsonProperty("bt-max-open-files")
    private Long btMaxOpenFiles;

    @JsonProperty("check-certificate")
    private Boolean checkCertificate;

    @JsonProperty("conf-path")
    private String confPath;

    @JsonProperty("console-log-level")
    private String consoleLogLevel;

    @JsonProperty("daemon")
    private Boolean daemon;

    @JsonProperty("deferred-input")
    private Boolean deferredInput;

    @JsonProperty("dht-file-path")
    private String dhtFilePath;

    @JsonProperty("dht-file-path6")
    private String dhtFilePath6;

    @JsonProperty("dht-listen-port")
    private String dhtListenPort;

    @JsonProperty("dht-message-timeout")
    private Long dhtMessageTimeout;

    @JsonProperty("disable-ipv6")
    private Boolean disableIpv6;

    @JsonProperty("disk-cache")
    private Long diskCache;

    @JsonProperty("download-result")
    private String downloadResult;

    @JsonProperty("dscp")
    private Long dscp;

    @JsonProperty("enable-color")
    private Boolean enableColor;

    @JsonProperty("enable-dht")
    private Boolean enableDht;

    @JsonProperty("enable-dht6")
    private Boolean enableDht6;

    @JsonProperty("enable-rpc")
    private Boolean enableRpc;

    @JsonProperty("event-poll")
    private String eventPoll;

    @JsonProperty("help")
    private String help;

    @JsonProperty("human-readable")
    private Boolean humanReadable;

    @JsonProperty("keep-unfinished-download-result")
    private Boolean keepUnfinishedDownloadResult;

    @JsonProperty("listen-port")
    private String listenPort;

    @JsonProperty("log-level")
    private String logLevel;

    @JsonProperty("max-concurrent-downloads")
    private Long maxConcurrentDownloads;

    @JsonProperty("max-download-result")
    private Long maxDownloadResult;

    @JsonProperty("max-overall-download-limit")
    private Long maxOverallDownloadLimit;

    @JsonProperty("max-overall-upload-limit")
    private Long maxOverallUploadLimit;

    @JsonProperty("min-tls-version")
    private String minTlsVersion;

    @JsonProperty("netrc-path")
    private String netrcPath;

    @JsonProperty("no-conf")
    private Boolean noConf;

    @JsonProperty("optimize-concurrent-downloads")
    private Boolean optimizeConcurrentDownloads;

    @JsonProperty("peer-agent")
    private String peerAgent;

    @JsonProperty("peer-id-prefix")
    private String peerIdPrefix;

    @JsonProperty("quiet")
    private Boolean quiet;

    @JsonProperty("rpc-allow-origin-all")
    private Boolean rpcAllowOriginAll;

    @JsonProperty("rpc-listen-all")
    private Boolean rpcListenAll;

    @JsonProperty("rpc-listen-port")
    private Long rpcListenPort;

    @JsonProperty("rpc-max-request-size")
    private Long rpcMaxRequestSize;

    @JsonProperty("rpc-secure")
    private Boolean rpcSecure;

    @JsonProperty("save-session-interval")
    private Long saveSessionInterval;

    @JsonProperty("server-stat-timeout")
    private Long serverStatTimeout;

    @JsonProperty("show-console-readout")
    private Boolean showConsoleReadout;

    @JsonProperty("show-files")
    private Boolean showFiles;

    @JsonProperty("socket-recv-buffer-size")
    private Long socketRecvBufferSize;

    @JsonProperty("stderr")
    private Boolean stderr;

    @JsonProperty("stop")
    private Long stop;

    @JsonProperty("summary-interval")
    private Long summaryInterval;

    @JsonProperty("truncate-console-readout")
    private Boolean truncateConsoleReadout;
}
