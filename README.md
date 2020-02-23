# download-anything

一个理论上可以下载所有网站的工具，环境`JDK11+`，不考虑向下兼容

这个项目暂时还在草稿阶段，当前仅支持`Windows`

打包方式：双击项目目录下的`package.cmd`

运行方式：打包完成在目录下执行：

```shell
java -jar ./application/target/download.jar
```

默认端口号为10086，可以[直接访问](http://localhost:10086)

将首页上面的脚本拖拽到书签栏，然后在想要下载的页面点击书签，就可以下载了。

默认的路径在用户目录下的Downloads文件夹里面，暂时不支持更换

![](http://p.qlogo.cn/qqmail_head/Q3auHgzwzM4g2cLj1J8wBePWc7IpPAic1jnZaaSEhrYuibw5AhZgdeWic8h6zTLd8cKgYiaYjEwj8KmfmE1LBR92RrKfMTe5ib5qOKd5iaRy4pcNM/0)