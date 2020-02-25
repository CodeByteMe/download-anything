import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter);

const routes = [
    {
        path: '/',
        name: '首页',
        component: Home
    },
    {
        path: '/list',
        name: '下载列表',
        component: () => import(/* webpackChunkName: "download-list" */ '../views/DownloadList.vue')
    },
    {
        path: '/settings',
        name: '设置',
        component: () => import(/* webpackChunkName: "settings" */ '../views/Settings.vue')
    }
];

const router = new VueRouter({
    routes
});

export default router
