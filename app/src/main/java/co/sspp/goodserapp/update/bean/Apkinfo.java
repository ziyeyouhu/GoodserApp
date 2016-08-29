package co.sspp.goodserapp.update.bean;

/**
 * User: ZiYeYouHu
 * Date: 2016-07-04
 * Time: 12:27
 *  Des:
 * FIXME
 */
public class Apkinfo {

    private int version;
    private String introduction;
    private String url;

    public Apkinfo() {

    }

    public Apkinfo(int ver, String intro, String u) {
        this.version = ver;
        this.introduction = intro;
        this.url = u;

    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
