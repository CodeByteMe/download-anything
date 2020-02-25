<template>
    <div>
        <b-carousel :interval="4000" controls indicators
                    style="text-shadow: 1px 1px 2px #333;">
            <b-carousel-slide :caption="item.title"
                              :img-alt="item.imageDes"
                              :img-src="item.imageUrl"
                              :key="index"
                              :text="item.des"
                              caption-tag="h5"
                              v-for="(item, index) in carouselList"/>
        </b-carousel>
    </div>
</template>

<script>
    export default {
        name: "CarouselImages",
        data() {
            return {
                carouselList: [{
                    imageUrl: "http://p.qlogo.cn/qqmail_head/Q3auHgzwzM4g2cLj1J8wBePWc7IpPAic1zf6BFE19PfAw4iczE5vMANoGicPMwcDTwMt70J6IcTPJvDY7T18QIefzC7aMBbKibRQiaCED65jCgNY/0",
                    imageDes: "慕课网下载器",
                    title: "Imooc Downloader",
                    des: "Imooc video downloader, not only video"
                },
                    {
                        imageUrl: "http://p.qlogo.cn/qqmail_head/Q3auHgzwzM4g2cLj1J8wBePWc7IpPAic1zf6BFE19PfAw4iczE5vMANoGicPMwcDTwMt70J6IcTPJvDY7T18QIefzC7aMBbKibRQiaCED65jCgNY/0",
                        imageDes: "慕课网下载器",
                        title: "Imooc Downloader",
                        des: "Imooc video downloader, not only video"
                    }, {
                        imageUrl: "http://p.qlogo.cn/qqmail_head/Q3auHgzwzM4g2cLj1J8wBePWc7IpPAic1zf6BFE19PfAw4iczE5vMANoGicPMwcDTwMt70J6IcTPJvDY7T18QIefzC7aMBbKibRQiaCED65jCgNY/0",
                        imageDes: "慕课网下载器",
                        title: "Imooc Downloader",
                        des: "Imooc video downloader, not only video"
                    }],
            }
        }
    }
</script>

<style scoped>

</style>