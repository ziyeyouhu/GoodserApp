package co.sspp.goodserapp.home.domain;

/**
 * *******************************************************************************************
 * <p>
 * 作者： ZiYeYouHu
 * 时间：2016-08-22 10:46                                                 *
 * <p>
 * *******************************************************************************************
 * <p>
 * 描述：
 * 修订：
 * <p>
 * *******************************************************************************************
 */

public class DoBannerInfo {

    /**
     * Id : 1
     * Url : http://www.sspp.co/upload_img/AppIndexImages/lunbo1.jpg
     */

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
