new Vue({
    el: "#app",
    created() {
        this.initialDownload();
        this.connectWebSocket();
    },
    data: {
        downloadList: [1, 2, 3, 4, 5, 6, 7, 8],
        currentPage: "index",
        carouselList: [{
            imageUrl: "http://p.qlogo.cn/qqmail_head/Q3auHgzwzM4g2cLj1J8wBePWc7IpPAic1zf6BFE19PfAw4iczE5vMANoGicPMwcDTwMt70J6IcTPJvDY7T18QIefzC7aMBbKibRQiaCED65jCgNY/0",
            imageDes: "慕课网下载器",
            title: "Imooc Downloader",
            des: "Imooc video downloader, not only video"
        }],
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
            stompClient.connect({}, function (frame) {
                console.log('Connected: ' + frame);
                stompClient.subscribe('/downloadStatus/notice', function (response) {
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
                            this.currentPage = "downloading";
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
                        var loc = window.location;
                        var url = loc.protocol + "//" + loc.host;
                        window.history.replaceState(null, null, url);
                    }
                })
            }
        }

    },
});
