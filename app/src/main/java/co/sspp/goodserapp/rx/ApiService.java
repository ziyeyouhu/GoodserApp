package co.sspp.goodserapp.rx;


import java.util.ArrayList;

import co.sspp.goodserapp.home.DoPortInfo;
import co.sspp.goodserapp.home.domain.DoFindshipInfo;
import co.sspp.goodserapp.home.domain.DoBannerInfo;
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
}





