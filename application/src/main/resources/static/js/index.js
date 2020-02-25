new Vue({
    el: "#app",
    created() {
        this.initialDownload();
        this.connectWebSocket();
    },
    data: {
        downloadList: [{
            "downloadId": "40e6358a7ccab1b1de61506cea9de561",
            "status": "FINISHED",
            "downloadType": "VIDEO",
            "fileName": "《唐人街探案3》国产巅峰喜剧",
            "statusFormat": "文件已存在!",
            "totalSize": "84.54MB",
            "targetPath": "C:\\Users\\dnydi\\Downloads\\《唐人街探案3》国产巅峰喜剧.mp4",
            "source": "哔哩哔哩",
            "currentSpeed": "100M/S",
            "progressFormat": "100%",
            "progress": 90.0
        }],
        currentPage: "index",
        carouselList: [{
            imageUrl: "http://p.qlogo.cn/qqmail_head/Q3auHgzwzM4g2cLj1J8wBePWc7IpPAic1zf6BFE19PfAw4iczE5vMANoGicPMwcDTwMt70J6IcTPJvDY7T18QIefzC7aMBbKibRQiaCED65jCgNY/0",
            imageDes: "慕课网下载器",
            title: "Imooc Downloader",
            des: "Imooc video downloader, not only video"
        }],
        downloadingCount: 0,
        finishedCount: 0,
        trashCount: 0
    },
    methods: {
        changePage(currentPage) {
            this.currentPage = currentPage;
            $('[data-toggle="tooltip"]').tooltip();
        },
        connectWebSocket() {
            var socket = new SockJS('/socketConnect');
            stompClient = Stomp.over(socket);
            stompClient.debug = null; // 禁用Debug
            stompClient.connect({}, (frame) => {
                console.log('Connected: ' + frame);
                stompClient.subscribe('/downloadStatus/notice', (response) => {
                    this.downloadList = JSON.parse(response.body);
                    this.downloadingCount = this.downloadList.length;
                    console.dir(response.body);
                });
            });
        },
        initialDownload() {
            var search = new URLSearchParams(window.location.search);
            var url = search.get("url");
            var cookie = search.get("cookie");
            if (url && cookie) {
                $.post({
                    url: "/download/addDownload",
                    data: {
                        url: atob(url),
                        cookie: atob(cookie)
                    },
                    success: (response) => {
                        if (response.flag) {
                            Swal.fire(
                                'OK!',
                                response.message,
                                'success'
                            );
                        } else {
                            Swal.fire({
                                icon: 'error',
                                title: 'No!',
                                html: response.message,
                                footer: '如果URL不被支持，请反馈一下...'
                            })
                        }
                        this.currentPage = "downloading";
                        var loc = window.location;
                        var url = loc.protocol + "//" + loc.host;
                        window.history.replaceState(null, null, url);
                    }
                })
            }
        }

    },
});
