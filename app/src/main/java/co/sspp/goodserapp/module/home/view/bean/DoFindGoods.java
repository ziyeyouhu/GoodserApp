package co.sspp.goodserapp.module.home.view.bean;

import java.io.Serializable;
import java.util.List;

/**
 * User: ZiYeYouHu
 * Date: 2016-07-18
 * Time: 14:49
 * Des:
 * FIXME
 */

public class DoFindGoods implements Serializable{
    /**
     * TotalRecord : 86
     * TotalPage : 5
     * RetData : [{"GoodsListId":62686,"LoadDate":"07-21","LoadAddDay":3,"GoodsType":"废钢","StartPort":"大连","EndPort":"张家港","CargoVolume":16500,"AddVolume":1500,"GoodsOwnerPhone":"18202680584","Collection":0,"CId":0,"GoodsOwnerId":3342},{"GoodsListId":62685,"LoadDate":"07-23","LoadAddDay":1,"GoodsType":"煤炭","StartPort":"秦皇岛","EndPort":"八所","CargoVolume":17000,"AddVolume":1000,"GoodsOwnerPhone":"15161664777","Collection":0,"CId":0,"GoodsOwnerId":3539},{"GoodsListId":62684,"LoadDate":"07-21","LoadAddDay":3,"GoodsType":"煤炭","StartPort":"黄骅","EndPort":"镇江","CargoVolume":33000,"AddVolume":2000,"GoodsOwnerPhone":"18202680584","Collection":0,"CId":0,"GoodsOwnerId":3342},{"GoodsListId":62682,"LoadDate":"07-20","LoadAddDay":3,"GoodsType":"沙子","StartPort":"湛江港","EndPort":"广州沙田","CargoVolume":15000,"AddVolume":2000,"GoodsOwnerPhone":"13500014177","Collection":0,"CId":0,"GoodsOwnerId":2338},{"GoodsListId":62679,"LoadDate":"07-21","LoadAddDay":2,"GoodsType":"石子","StartPort":"南通","EndPort":"鲅鱼圈","CargoVolume":10000,"AddVolume":100,"GoodsOwnerPhone":"18051389976","Collection":0,"CId":0,"GoodsOwnerId":3339},{"GoodsListId":62674,"LoadDate":"07-21","LoadAddDay":1,"GoodsType":"粉煤灰","StartPort":"大连","EndPort":"九江","CargoVolume":5100,"AddVolume":100,"GoodsOwnerPhone":"18606448111","Collection":0,"CId":0,"GoodsOwnerId":3448},{"GoodsListId":62671,"LoadDate":"07-21","LoadAddDay":1,"GoodsType":"矿粉","StartPort":"张家港","EndPort":"厦门","CargoVolume":5000,"AddVolume":0,"GoodsOwnerPhone":"18355375815","Collection":0,"CId":0,"GoodsOwnerId":3400},{"GoodsListId":62670,"LoadDate":"07-21","LoadAddDay":2,"GoodsType":"水泥熟料","StartPort":"东莞","EndPort":"汕头","CargoVolume":5000,"AddVolume":0,"GoodsOwnerPhone":"13327778880","Collection":0,"CId":0,"GoodsOwnerId":608},{"GoodsListId":62668,"LoadDate":"07-21","LoadAddDay":1,"GoodsType":"钢渣","StartPort":"宁波","EndPort":"南京","CargoVolume":3500,"AddVolume":0,"GoodsOwnerPhone":"15295598883","Collection":0,"CId":0,"GoodsOwnerId":1929},{"GoodsListId":62667,"LoadDate":"07-21","LoadAddDay":3,"GoodsType":"沙子","StartPort":"台湾海峡","EndPort":"舟山","CargoVolume":15000,"AddVolume":5000,"GoodsOwnerPhone":"18202680584","Collection":0,"CId":0,"GoodsOwnerId":3342},{"GoodsListId":62666,"LoadDate":"07-23","LoadAddDay":1,"GoodsType":"水渣粉","StartPort":"曹妃甸","EndPort":"福州","CargoVolume":17000,"AddVolume":1000,"GoodsOwnerPhone":"13370639851","Collection":0,"CId":0,"GoodsOwnerId":2323},{"GoodsListId":62665,"LoadDate":"07-20","LoadAddDay":1,"GoodsType":"煤灰","StartPort":"镇江","EndPort":"乌龙江","CargoVolume":1400,"AddVolume":0,"GoodsOwnerPhone":"18355375815","Collection":0,"CId":0,"GoodsOwnerId":3400},{"GoodsListId":62664,"LoadDate":"07-22","LoadAddDay":2,"GoodsType":"水泥","StartPort":"曹妃甸","EndPort":"福州","CargoVolume":17000,"AddVolume":1000,"GoodsOwnerPhone":"13370639851","Collection":0,"CId":0,"GoodsOwnerId":2323},{"GoodsListId":62663,"LoadDate":"07-23","LoadAddDay":1,"GoodsType":"矿粉","StartPort":"张家港","EndPort":"汕尾","CargoVolume":5000,"AddVolume":0,"GoodsOwnerPhone":"18355375815","Collection":0,"CId":0,"GoodsOwnerId":3400},{"GoodsListId":62661,"LoadDate":"07-20","LoadAddDay":1,"GoodsType":"水泥","StartPort":"强蛟","EndPort":"平潭","CargoVolume":1300,"AddVolume":0,"GoodsOwnerPhone":"18355375815","Collection":0,"CId":0,"GoodsOwnerId":3400},{"GoodsListId":62659,"LoadDate":"07-22","LoadAddDay":1,"GoodsType":"石英砂","StartPort":"北海","EndPort":"上海闸港","CargoVolume":13500,"AddVolume":500,"GoodsOwnerPhone":"13379942336","Collection":0,"CId":0,"GoodsOwnerId":3435},{"GoodsListId":62658,"LoadDate":"07-20","LoadAddDay":3,"GoodsType":"煤炭","StartPort":"天津","EndPort":"镇江","CargoVolume":12000,"AddVolume":1000,"GoodsOwnerPhone":"15102545656","Collection":0,"CId":0,"GoodsOwnerId":2068},{"GoodsListId":62657,"LoadDate":"07-22","LoadAddDay":1,"GoodsType":"PET","StartPort":"洋浦","EndPort":"深圳","CargoVolume":3000,"AddVolume":500,"GoodsOwnerPhone":"18857112275","Collection":0,"CId":0,"GoodsOwnerId":2857},{"GoodsListId":62655,"LoadDate":"07-20","LoadAddDay":3,"GoodsType":"陶土","StartPort":"湛江","EndPort":"潍坊","CargoVolume":12500,"AddVolume":500,"GoodsOwnerPhone":"13877909777","Collection":0,"CId":0,"GoodsOwnerId":2229},{"GoodsListId":62652,"LoadDate":"07-24","LoadAddDay":2,"GoodsType":"煤炭","StartPort":"天津","EndPort":"鲅鱼圈","CargoVolume":20000,"AddVolume":0,"GoodsOwnerPhone":"18840752057","Collection":0,"CId":0,"GoodsOwnerId":3510}]
     * RetCode : 0
     * RetMsg : 获取成功
     */

    private int TotalRecord;
    private int TotalPage;
    private int RetCode;
    private String RetMsg;
    /**
     * GoodsListId : 62686
     * LoadDate : 07-21
     * LoadAddDay : 3
     * GoodsType : 废钢
     * StartPort : 大连
     * EndPort : 张家港
     * CargoVolume : 16500
     * AddVolume : 1500
     * GoodsOwnerPhone : 18202680584
     * Collection : 0
     * CId : 0
     * GoodsOwnerId : 3342
     */

    private List<RetDataEntity> RetData;

    public int getTotalRecord() {
        return TotalRecord;
    }

    public void setTotalRecord(int TotalRecord) {
        this.TotalRecord = TotalRecord;
    }

    public int getTotalPage() {
        return TotalPage;
    }

    public void setTotalPage(int TotalPage) {
        this.TotalPage = TotalPage;
    }

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
        private int GoodsListId;
        private String LoadDate;
        private int LoadAddDay;
        private String GoodsType;
        private String StartPort;
        private String EndPort;
        private int CargoVolume;
        private int AddVolume;
        private String GoodsOwnerPhone;
        private int Collection;
        private int CId;
        private int GoodsOwnerId;

        public int getGoodsListId() {
            return GoodsListId;
        }

        public void setGoodsListId(int GoodsListId) {
            this.GoodsListId = GoodsListId;
        }

        public String getLoadDate() {
            return LoadDate;
        }

        public void setLoadDate(String LoadDate) {
            this.LoadDate = LoadDate;
        }

        public int getLoadAddDay() {
            return LoadAddDay;
        }

        public void setLoadAddDay(int LoadAddDay) {
            this.LoadAddDay = LoadAddDay;
        }

        public String getGoodsType() {
            return GoodsType;
        }

        public void setGoodsType(String GoodsType) {
            this.GoodsType = GoodsType;
        }

        public String getStartPort() {
            return StartPort;
        }

        public void setStartPort(String StartPort) {
            this.StartPort = StartPort;
        }

        public String getEndPort() {
            return EndPort;
        }

        public void setEndPort(String EndPort) {
            this.EndPort = EndPort;
        }

        public int getCargoVolume() {
            return CargoVolume;
        }

        public void setCargoVolume(int CargoVolume) {
            this.CargoVolume = CargoVolume;
        }

        public int getAddVolume() {
            return AddVolume;
        }

        public void setAddVolume(int AddVolume) {
            this.AddVolume = AddVolume;
        }

        public String getGoodsOwnerPhone() {
            return GoodsOwnerPhone;
        }

        public void setGoodsOwnerPhone(String GoodsOwnerPhone) {
            this.GoodsOwnerPhone = GoodsOwnerPhone;
        }

        public int getCollection() {
            return Collection;
        }

        public void setCollection(int Collection) {
            this.Collection = Collection;
        }

        public int getCId() {
            return CId;
        }

        public void setCId(int CId) {
            this.CId = CId;
        }

        public int getGoodsOwnerId() {
            return GoodsOwnerId;
        }

        public void setGoodsOwnerId(int GoodsOwnerId) {
            this.GoodsOwnerId = GoodsOwnerId;
        }
    }
}
