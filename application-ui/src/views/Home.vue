<template>
    <div id="home">

        <!-- 轮播图 -->
        <CarouselImage/>

        <div class="my-3">粘贴链接到下面, 然后点击下载按钮</div>

        <b-input-group>

            <b-form-input placeholder="粘贴URL到这里" type="text"/>
            <b-input-group-append>
                <b-button variant="outline-secondary">下载</b-button>
            </b-input-group-append>
        </b-input-group>

        <div class="text-center mt-3">
            <a class="d-inline"
               href="javascript:void((function() {var href = btoa(window.location.href);var cookie = btoa(document.cookie);window.open(`http://127.0.0.1:10086/index.html?url=${href}&cookie=${cookie}`)})())"
               id="bookMark">
                把这个拖到书签栏
            </a>
        </div>

    </div>
</template>

<script>

    import CarouselImage from "../components/CarouselImages";

    export default {
        name: 'Home',
        components: {
            CarouselImage
        }
    }
</script>

<style scoped>
    @media (max-width: 768px) {
        #bookMark {
            display: none !important;
        }
    }
</style>
