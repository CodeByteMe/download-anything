# jaria2c-rpc

`Java`版本的`aria2c-rpc`，[`aria2c`](https://github.com/aria2/aria2)是`C++`开发的一个高性能下载器，可以让`Java`通过`json-rpc`来调用`aria2c`下载器中的方法

# 当前进度

- [x] 实现官方所有的`json-rpc`方法

- [ ] 官方`RPC`文档翻译完善

使用了[`jsonrpc4j`](https://github.com/briandilley/jsonrpc4j)和[`lombok`](https://projectlombok.org/)做支持，代码极其简单

# 使用方法

当然下面的例子是一个比较复杂且完整的例子，如果你只需要简单的下载一个文件，完全不需要这么复杂。

```java
/**
 * 一个下载的例子，QQ
 */
public static void main(String[] args) {
    
    // 实例化Aria2c
    Aria2c aria2c = Aria2c.run();
    
    // --rpc-secret参数规定的token
    String token = Secret.token("这里填写token");

    // 添加下载连接
    String[] uris = {"https://qd.myapp.com/myapp/qqteam/pcqq/PCQQ2020.exe"};

    // 构造下载选项
    Options of = Options.of(Aria2cOptions.builder()
            .dir("下载目录")
            .out("文件名.exe")
            .build());

    // 添加到下载
    String gid = aria2c.addUri(token, uris, of, Integer.MAX_VALUE);

    // 监控下载情况
    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    
    scheduledExecutorService.scheduleAtFixedRate(() -> {
        Aria2cStatus downloadStatus = aria2c.tellStatus(token, gid, new String[]{});
        log.debug("当前的下载速度：{}，响应的全部数据：{}", downloadStatus.getDownloadSpeed(), downloadStatus);
    }, 0, 1, TimeUnit.SECONDS);
}
```

需要进行官方文档翻译和代码整改工作...