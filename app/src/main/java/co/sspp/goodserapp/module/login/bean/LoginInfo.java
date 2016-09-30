package co.sspp.goodserapp.module.login.bean;

/**
 * *******************************************************************************************
 * <p>
 * 作者： ZiYeYouHu
 * 时间：2016-09-29 10:30                                                 *
 * <p>
 * *******************************************************************************************
 * <p>
 * 描述：
 * 修订：
 * <p>
 * *******************************************************************************************
 */

public class LoginInfo {


    /**
     * SkipUrl :
     * UserId : 10938
     * UserType : 2
     * IdentityId : 3345
     * UserName : shn000
     * UserInfoName : shn
     * NickName :
     * MobileLogKey : 1475152351_10938_2
     */

    private RetDataEntity RetData;
    /**
     * RetData : {"SkipUrl":"","UserId":10938,"UserType":2,"IdentityId":3345,"UserName":"shn000","UserInfoName":"shn","NickName":"","MobileLogKey":"1475152351_10938_2"}
     * RetCode : 0
     * RetMsg : 登录成功
     */

    private int RetCode;
    private String RetMsg;

    public RetDataEntity getRetData() {
        return RetData;
    }

    public void setRetData(RetDataEntity RetData) {
        this.RetData = RetData;
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

    public static class RetDataEntity {
        private String SkipUrl;
        private int UserId;
        private int UserType;
        private int IdentityId;
        private String UserName;
        private String UserInfoName;
        private String NickName;
        private String MobileLogKey;

        public String getSkipUrl() {
            return SkipUrl;
        }

        public void setSkipUrl(String SkipUrl) {
            this.SkipUrl = SkipUrl;
        }

        public int getUserId() {
            return UserId;
        }

        public void setUserId(int UserId) {
            this.UserId = UserId;
        }

        public int getUserType() {
            return UserType;
        }

        public void setUserType(int UserType) {
            this.UserType = UserType;
        }

        public int getIdentityId() {
            return IdentityId;
        }

        public void setIdentityId(int IdentityId) {
            this.IdentityId = IdentityId;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getUserInfoName() {
            return UserInfoName;
        }

        public void setUserInfoName(String UserInfoName) {
            this.UserInfoName = UserInfoName;
        }

        public String getNickName() {
            return NickName;
        }

        public void setNickName(String NickName) {
            this.NickName = NickName;
        }

        public String getMobileLogKey() {
            return MobileLogKey;
        }

        public void setMobileLogKey(String MobileLogKey) {
            this.MobileLogKey = MobileLogKey;
        }
    }
}
