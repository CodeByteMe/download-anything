package top.cyblogs.download;

import top.cyblogs.output.Aria2cStatus;

/**
 * 下载工具类的回调，每个回调对应一个状态，这些状态全部来源于aria2c-rpc中获取的状态
 *
 * @author CY
 */
public abstract class BaseDownloadListener {

    /**
     * 当前下载中/种子下载中
     */
    public void active(Aria2cStatus status) {
    }

    /**
     * 用于队列中的下载；下载未开始
     */
    public void waiting(Aria2cStatus status) {
    }

    /**
     * 暂停下载
     */
    public void paused(Aria2cStatus status) {
    }

    /**
     * 对于由于错误而停止的下载
     */
    public void error(Aria2cStatus status) {
    }

    /**
     * 停止和完成下载
     */
    public void complete(Aria2cStatus status) {
    }

    /**
     * 用户删除的下载
     */
    public void removed(Aria2cStatus status) {
    }

    /**
     * URI被使用
     */
    public void used(Aria2cStatus status) {
    }
}
