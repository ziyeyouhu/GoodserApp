package co.sspp.goodserapp.home.domain;

import java.io.Serializable;

/**
 * Created by Jam on 16-8-12
 * Description:
 */
public class DoFindshipInfo implements Serializable{

        private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    private String EditDate;
    private String EmptyPort;
    private String LoadDate;
    private String LoadTon;
    private Integer ShipListId;
    private String ShipName;

    public String getEditDate() {
        return EditDate;
    }

    public void setEditDate(String editDate) {
        EditDate = editDate;
    }

    public String getEmptyPort() {
        return EmptyPort;
    }

    public void setEmptyPort(String emptyPort) {
        EmptyPort = emptyPort;
    }

    public String getLoadDate() {
        return LoadDate;
    }

    public void setLoadDate(String loadDate) {
        LoadDate = loadDate;
    }

    public String getLoadTon() {
        return LoadTon;
    }

    public void setLoadTon(String loadTon) {
        LoadTon = loadTon;
    }

    public Integer getShipListId() {
        return ShipListId;
    }

    public void setShipListId(Integer shipListId) {
        ShipListId = shipListId;
    }

    public String getShipName() {
        return ShipName;
    }

    public void setShipName(String shipName) {
        ShipName = shipName;
    }
}
