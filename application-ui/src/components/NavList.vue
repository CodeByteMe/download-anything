<template>
    <div id="navList">
        <b-list-group>

            <!--首页-->
            <router-link class="d-flex align-items-center" exact
                         exact-active-class="list-group-item-secondary"
                         replace
                         tag="b-list-group-item"
                         to="/">
                <font-awesome-icon class="mr-3" icon="home"/>
                <span>首页</span>
            </router-link>

            <!--下载中-->
            <router-link class="d-flex align-items-center" exact
                         exact-active-class="list-group-item-secondary"
                         replace
                         tag="b-list-group-item"
                         to="/list?active=downloading">
                <font-awesome-icon class="mr-3" icon="download"/>
                <span class="mr-auto">下载中</span>
                <b-badge pill variant="primary">2</b-badge>
            </router-link>

            <!--已完成-->
            <router-link class="d-flex align-items-center" exact
                         exact-active-class="list-group-item-secondary"
                         replace
                         tag="b-list-group-item"
                         to="/list?active=finished">
                <font-awesome-icon class="mr-3" icon="check-circle"/>
                <span class="mr-auto">已完成</span>
                <b-badge pill variant="primary">2</b-badge>
            </router-link>

            <!--垃圾箱-->
            <router-link class="d-flex align-items-center" exact
                         exact-active-class="list-group-item-secondary"
                         replace
                         tag="b-list-group-item"
                         to="/list?active=trash">
                <font-awesome-icon class="mr-3" icon="trash"/>
                <span class="mr-auto">垃圾箱</span>
                <b-badge pill variant="primary">2</b-badge>
            </router-link>

            <!--设置-->
            <router-link class="d-flex align-items-center" exact
                         exact-active-class="list-group-item-secondary"
                         replace
                         tag="b-list-group-item"
                         to="/settings">
                <font-awesome-icon class="mr-3" icon="cog"/>
                <span>设置</span>
            </router-link>
        </b-list-group>
    </div>
</template>

<script>
    export default {
        name: "NavList"
    }
</script>

<style scoped>

</style>