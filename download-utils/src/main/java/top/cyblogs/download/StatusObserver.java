package top.cyblogs.download;


import top.cyblogs.output.Aria2cStatus;

import java.beans.PropertyChangeSupport;
import java.util.List;

/**
 * 状态观察者
 */
public class StatusObserver {

    /**
     * 状态列表
     */
    private List<Aria2cStatus> statusList;

    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public List<Aria2cStatus> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Aria2cStatus> statusList) {
        propertyChangeSupport.firePropertyChange(
                "status", this.statusList, statusList);
        this.statusList = statusList;
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }
}
