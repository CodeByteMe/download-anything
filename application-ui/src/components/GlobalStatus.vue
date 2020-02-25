<template>

    <div id="globalStatus">
        <b-card no-body>
            <b-card-header header-tag="h5">
                <font-awesome-icon class="mr-3" icon="arrow-alt-circle-down"/>
                <span>下载详情</span>
            </b-card-header>
            <b-card-body>
                <b-card-text>
                    <p>
                        <font-awesome-icon class="mr-3" icon="greater-than-equal"/>
                        <span>速度: 100MB/S</span>
                    </p>
                    <p>
                        <font-awesome-icon class="mr-3" icon="spinner"/>
                        <span>进度: 16%</span>
                    </p>
                    <p>
                        <font-awesome-icon class="mr-3" icon="hourglass-start"/>
                        <span>剩余10分钟</span>
                    </p>
                </b-card-text>
            </b-card-body>
        </b-card>
    </div>


</template>

<script>
    export default {
        name: "GlobalStatus"
    }
</script>

<style scoped>
    /* 总详情信息动态固定在界面上 */
    #globalStatus {
        position: sticky;
        top: 1rem;
    }
</style>