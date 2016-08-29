package co.sspp.goodserapp.home;

import java.io.Serializable;

import me.yokeyword.indexablelistview.IndexEntity;

/**
 * *******************************************************************************************
 * <p>
 * 作者： ZiYeYouHu
 * 时间：2016-08-20 15:01                                                 *
 * <p>
 * *******************************************************************************************
 * <p>
 * 描述：
 * 修订：
 * <p>
 * *******************************************************************************************
 */

public class DoPortInfo extends IndexEntity implements Serializable{
    private String PortName;
    private int PortId;
    private int IsHot;

    @Override
    public String getName() {
        return PortName;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public String toString() {
        return "DoPortInfo{" +
                "PortName='" + PortName + '\'' +
                ", PortId=" + PortId +
                ", IsHot=" + IsHot +
                '}';
    }

    public String getPortName() {
        return PortName;
    }

    public void setPortName(String portName) {
        PortName = portName;
    }

    public int getIsHot() {
        return IsHot;
    }

    public void setIsHot(int isHot) {
        IsHot = isHot;
    }

    public int getPortId() {
        return PortId;
    }

    public void setPortId(int portId) {
        PortId = portId;
    }
}
