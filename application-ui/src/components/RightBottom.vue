<template>
    <!-- 右下角的图标 -->
    <div class="rb-container">
        <!-- 添加下载任务 -->
        <div class="rb-button d-flex justify-content-center align-items-center rounded-circle bg-white"
             data-placement="left"
             data-toggle="tooltip" title="添加下载任务">
            <font-awesome-icon icon="plus"/>
        </div>
        <!-- 开始所有下载 -->
        <div class="mt-3 rb-button d-flex justify-content-center align-items-center rounded-circle bg-white"
             data-placement="left"
             data-toggle="tooltip" title="开始所有下载">
            <font-awesome-icon icon="play"/>
        </div>
        <!-- 暂停所有下载 -->
        <div class="mt-3 rb-button d-flex justify-content-center align-items-center rounded-circle bg-white"
             data-placement="left"
             data-toggle="tooltip" title="暂停下载任务">
            <font-awesome-icon icon="pause"/>
        </div>
        <!-- 删除所有下载 -->
        <div class="mt-3 rb-button d-flex justify-content-center align-items-center rounded-circle bg-white"
             data-placement="left"
             data-toggle="tooltip" title="删除下载任务">
            <font-awesome-icon icon="trash"/>
        </div>
    </div>
</template>

<script>
    export default {
        name: "RightBottom"
    }
</script>

<style scoped>
    /* 右下角容器 */
    .rb-container {
        position: fixed;
        right: 1rem;
        bottom: 1rem;
    }

    /* 右下角的几个按钮 */
    .rb-button {
        width: 2.5rem;
        height: 2.5rem;
        box-shadow: 0 1px 20px 0 rgba(0, 0, 0, 0.1), inset 0 -1px 0 0 rgba(0, 0, 0, 0.1);
    }
</style>