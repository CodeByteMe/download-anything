<template>
    <div id="settings">

        <span class="small">设置 (设置有些是假的，暂时懒得实现了)</span>

        <b-form class="mt-3">
            <b-form-group label="下载路径"
                          label-for="downloadPath">
                <b-form-input id="downloadPath"
                              placeholder="在这里填写下载路径" required
                              type="text"/>
            </b-form-group>

            <b-form-group label="同时下载几个"
                          label-for="threadNum">
                <b-form-input id="threadNum"
                              placeholder="填写一个数字，请确保网速牛逼" required
                              type="text"/>
            </b-form-group>

            <b-button type="button" variant="primary">保存</b-button>
        </b-form>
    </div>

</template>

<script>
    export default {
        name: "Settings"
    }
</script>

<style scoped>

</style>