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
        component: () => import(/* webpackChunkName: "about" */ '../views/DownloadList.vue')
    },
    {
        path: '/settings',
        name: '下载列表',
        component: () => import(/* webpackChunkName: "about" */ '../views/Settings.vue')
    },
    {
        path: '/about',
        name: 'About',
        component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
    }
];

const router = new VueRouter({
    routes
});

export default router
