<template>
    <div id="downloadList">
        <section :key="index" v-for="(item, index) in downloadList">
            <DownloadItem :class="index === downloadList.length - 1 ? 'border-bottom' : ''"
                          :item="item"
                          class="border-top"/>
        </section>

        <RightBottom/>

    </div>
</template>

<script>

    import DownloadItem from "../components/DownloadItem";
    import RightBottom from "../components/RightBottom";

    export default {
        name: "DownloadList",
        data() {
            return {
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
            };
        },
        components: {
            DownloadItem,
            RightBottom
        }
    }
</script>

<style scoped>

</style>