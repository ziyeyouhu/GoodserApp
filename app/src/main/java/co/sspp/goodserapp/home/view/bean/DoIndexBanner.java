package co.sspp.goodserapp.home.view.bean;

import java.io.Serializable;
import java.util.List;

/**
 * User: ZiYeYouHu
 * Date: 2016-07-18
 * Time: 12:26
 * Des:
 * FIXME
 */

public class DoIndexBanner implements Serializable{

    /**
     * RetData : [{"Id":1,"Url":"http://www.sspp.co/upload_img/AppIndexImages/lunbo1.jpg"},{"Id":2,"Url":"http://www.sspp.co/upload_img/AppIndexImages/lunbo2.jpg"},{"Id":3,"Url":"http://www.sspp.co/upload_img/AppIndexImages/lunbo3.jpg"}]
     * RetCode : 0
     * RetMsg : 获取数据成功!
     */

    private int RetCode;
    private String RetMsg;
    /**
     * Id : 1
     * Url : http://www.sspp.co/upload_img/AppIndexImages/lunbo1.jpg
     */

    private List<RetDataEntity> RetData;

    public int getRetCode() {
        return RetCode;
    }

    public void setRetCode(int RetCode) {
        this.RetCode = RetCode;
    }

    public String getRetMsg() {
        return RetMsg;
    }

    public void setRetMsg(String RetMsg) {
        this.RetMsg = RetMsg;
    }

    public List<RetDataEntity> getRetData() {
        return RetData;
    }

    public void setRetData(List<RetDataEntity> RetData) {
        this.RetData = RetData;
    }

    public static class RetDataEntity {
        private int Id;
        private String Url;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getUrl() {
            return Url;
        }

        public void setUrl(String Url) {
            this.Url = Url;
        }
    }
}
