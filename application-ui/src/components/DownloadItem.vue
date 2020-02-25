<template>
    <!--一个下载项-->
    <section>
        <div class="d-flex">
            <!-- 下载类型图标 -->
            <div class="d-flex justify-content-center align-items-center border-right download-type">
                <font-awesome-icon class="text-danger" icon="file-video" size="3x"/>
            </div>

            <!-- 图标右边的一堆信息 -->
            <div class="d-flex flex-column flex-fill px-4 download-details"
                 style="justify-content: space-evenly;">
                <!-- 第一行信息 -->
                <section class="d-flex">
                    <span class="font-weight-bold mr-auto download-title">
                        {{item.fileName}}
                    </span>
                    <div class="text-muted download-status-lg">
                        {{item.statusFormat}}
                    </div>
                </section>
                <!-- 第二行信息 -->
                <section class="d-flex">
                    <div class="mr-auto text-muted">
                        大小: {{item.totalSize}}
                    </div>
                    <div class="text-muted download-status-sm">
                        {{item.statusFormat}}
                    </div>
                    <div class="text-muted target-path">
                        位置: {{item.targetPath.replace(/(.{8})(.*)(.{12})/, "$1……$3")}}
                    </div>
                </section>
                <!-- 第三行信息 -->
                <section class="d-flex">
                    <div class="mr-auto">
                        来源: <span class="text-danger">{{item.source}}</span>
                    </div>
                    <div class="text-muted">
                        {{item.currentSpeed}}
                    </div>
                    <div class="ml-2 text-muted">
                        {{item.progressFormat}}
                    </div>
                </section>
            </div>

            <!-- 下载控制按钮 -->
            <section class="d-flex justify-content-center align-items-center border-left download-control"
                     style="width: 8rem;">
                <div>
                    <font-awesome-icon icon="play"/>
                </div>
                <!-- <div>
                        暂停按钮
                    </div> -->
                <div class="ml-4">
                    <font-awesome-icon icon="trash"/>
                </div>
            </section>
        </div>
        <!-- 进度条 -->
        <div style="background-color: darkgrey">
            <div :class="item.status === 'DOWNLOADING' ? 'bg-danger' : item.status === 'MERGING' ? 'bg-info' : item.status === 'FINISHED' ? 'bg-success' : ''"
                 :style="`width: ${item.progress}%;`"
                 style="height: 3px;"></div>
        </div>
    </section>

</template>

<script>
    export default {
        name: "DownloadItem",
        props: {
            item: {
                type: [Object],
                required: true
            }
        }
    }
</script>

<style scoped>
    /* 下载类型图标 */
    .download-type {
        width: 6rem;
        height: 100px;
    }

    /* 下载标题 */
    .download-title {
        width: 22rem;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }

    /* 小屏下的下载状态 */
    .download-status-sm {
        display: none;
    }

    /* 小型电脑 */
    @media (max-width: 1200px) {
        .download-title {
            width: 18rem;
        }
    }

    /* 普通手机 */
    @media (max-width: 768px) {

        .download-title {
            width: 18rem;
        }

        .download-type {
            display: none !important;
        }

        .target-path {
            display: none !important;
        }

        .download-status-lg {
            display: none !important;
        }

        .download-status-sm {
            display: block !important;
        }

        .download-control {
            display: none !important;
        }

        .download-details {
            height: 100px;
            padding: 0 !important;
        }
    }
</style>