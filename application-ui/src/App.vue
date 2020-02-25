<template>
  <div class="container py-4" id="app">

    <!--程序标题-->
    <GlobalTitle/>

    <div class="row mt-3">
      <!--左侧区域-->
      <div class="col-lg-3 col-md-0" id="left">
        <!--导航列表-->
        <NavList/>
        <!--全局下载状态-->
        <GlobalStatus class="mt-4"/>
      </div>

      <!--右侧区域-->
      <div class="col-lg-9 col-md-12" id="right">

        <!--移动端的导航栏-->
        <MobileNavList class="mb-3"/>

        <b-alert dismissible show="" variant="danger">
          <strong>Hey man!</strong> 合理合法使用下载后的资源...
        </b-alert>

        <router-view/>
      </div>
    </div>

    <footer class="mt-5 text-center">
      <a class="text-muted" href="https://halo.cyblogs.top">CY'Blogs</a>
    </footer>

  </div>
</template>

<script>
  import GlobalTitle from "./components/GlobalTitle";
  import NavList from "./components/NavList";
  import GlobalStatus from "./components/GlobalStatus";
  import MobileNavList from "./components/MobileNavList";

  import DownloadListener from "./controller/websocket/download-listener"


  export default {
    created() {
      this.renderDownloadList();
    },
    components: {
      NavList,
      GlobalTitle,
      GlobalStatus,
      MobileNavList
    },
    methods: {
      renderDownloadList() {
        DownloadListener((downloadList) => {
          this.$log.debug(downloadList);
        });
      }
    }
  }
</script>


<style>
  /* 禁止用户在界面上进行选择 */
  * {
    user-select: none;
  }

  ::-webkit-scrollbar {
    width: 0.375rem;
    height: 0.25rem;
    background-color: rgba(0, 0, 0, 0);
  }

  /* 滚动条颜色 */
  ::-webkit-scrollbar-thumb {
    background-color: #007bff !important;
  }

  ::-webkit-scrollbar-track {
    background-color: rgba(0, 0, 0, .125);
  }

  @media (max-width: 992px) {
    #left {
      display: none !important;
    }
  }
</style>
