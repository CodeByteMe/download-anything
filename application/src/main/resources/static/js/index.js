new Vue({
	el: "#app",
	created() {
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
						var loc = window.location;
						var url = loc.protocol + "//" + loc.host;
						window.history.replaceState(null, null, url);
						Swal.fire(
							'OK!',
							response.message,
							'success'
						);
					} else {
						Swal.fire({
							icon: 'error',
							title: '啊...',
							html: response.message,
							footer: '如果URL不被支持，请反馈一下...'
						})
					}
				}
			})
		}
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

	},
});
