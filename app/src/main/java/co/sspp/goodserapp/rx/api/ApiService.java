package co.sspp.goodserapp.rx.api;


import java.util.ArrayList;

import co.sspp.goodserapp.module.home.DoPortInfo;
import co.sspp.goodserapp.module.home.domain.DoFindshipInfo;
import co.sspp.goodserapp.module.home.domain.DoBannerInfo;
import co.sspp.goodserapp.module.login.bean.LoginInfo;
import co.sspp.goodserapp.rx.BaseModel;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Ziye on 16-5-17
 * Description:
 */
public interface ApiService {

    @POST("api/Ship/SHI_GetShipList")
    @FormUrlEncoded
    Observable<BaseModel<ArrayList<DoFindshipInfo>>> findShip(
            @Field("PageSize") int pageSize,
            @Field("Page") int page);


    /**
     * 港口
     *
     * @return
     */
    @POST("api/PortInfo/GetPortInfo")
    Observable<BaseModel<ArrayList<DoPortInfo>>> ports();


    @POST("api/AppIndexImages/Post")
    Observable<BaseModel<ArrayList<DoBannerInfo>>> banners();


    /**
     * @param UserName
     * @param Pwd
     * @param UserType
     * @param MobileLogKey
     * @return
     */
    @POST("api/UserInfo/USE_ConsignorUserLogin")
    @FormUrlEncoded
    Observable<LoginInfo> nameLogin(
            @Field("UserName") String UserName,
            @Field("Pwd") String Pwd,
            @Field("UserType") int UserType,
            @Field("MobileLogKey") String MobileLogKey

    );

    /**
     *
     * @param UserName
     * @param Mobile
     * @param Pwd
     * @param Code
     * @param UserType
     * @param MsgId
     * @param Ip
     * @return
     */
    @POST("api/UserInfo/USE_ConsignorUserRegister")
    Observable<String> appRegister(
            @Field("UserName") String UserName,
            @Field("Mobile") String Mobile,
            @Field("Pwd") String Pwd,
            @Field("Code") String Code,
            @Field("UserType") int UserType,
            @Field("MsgId") int MsgId,
            @Field("Ip") String Ip

    );

}





