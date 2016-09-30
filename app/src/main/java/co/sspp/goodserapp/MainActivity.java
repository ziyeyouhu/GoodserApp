package co.sspp.goodserapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.sspp.goodserapp.base.BaseActivity;
import co.sspp.goodserapp.module.home.view.HomeActivity;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;

public class MainActivity extends BaseActivity {


    private static final String TAG = "MainActivity";
    @BindView(R.id.tv_name)
    TextView mTvName;

    @BindView(R.id.butto2n)
    Button mButto2n;
    @BindView(R.id.contener)
    LinearLayout mContener;
    @BindView(R.id.aaa)
    EditText mAaa;
    @BindView(R.id.bbb)
    EditText mBbb;
    @BindView(R.id.ccc)
    EditText mCcc;
    @BindView(R.id.qwe)
    Button mQwe;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#00c2c4"));

        ButterKnife.bind(this);



        gotoActivity(HomeActivity.class,true);

//        CheckUpdate.getInstance().startCheck(GuideActivity.this);


//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://mobile.sspp.co/v1.0.5/")
//                //增加返回值为String的支持
//                .addConverterFactory(ScalarsConverterFactory.create())
//                //增加返回值为Gson的支持(以实体类返回)
//                .addConverterFactory(GsonConverterFactory.create())
//                //增加返回值为Oservable<T>的支持
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//
//        ApiService service = retrofit.create(ApiService.class);
//
//        Call<String> ship = service.findShip("1468824444_10682_1",12,1);
//
//        ship.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//
//            }
//        });


        Observable<CharSequence> _emailChangeObservable = RxTextView.textChanges(mAaa).skip(1);
        Observable<CharSequence> _passwordChangeObservable = RxTextView.textChanges(mBbb).skip(1);
        Observable<CharSequence> _numberChangeObservable = RxTextView.textChanges(mCcc).skip(1);

        Observable.combineLatest(_emailChangeObservable, _passwordChangeObservable, _numberChangeObservable,
                (newEmail, newPassword, newNumber) -> {


                    boolean emailValid = !TextUtils.isEmpty(newEmail);


                    boolean passValid = !TextUtils.isEmpty(newPassword) && newPassword.length() > 8;


                    boolean numValid = !TextUtils.isEmpty(newNumber);


                    return emailValid && passValid && numValid;


                })
                .subscribe(aBoolean -> {

                    mQwe.setEnabled(aBoolean);
                });

//
//        Observable<ArrayList<DoFindshipInfo>> compose = Api.getDefault()
//                .findShip(12, 1)
//                .compose(RxHelper.<ArrayList<DoFindshipInfo>>handleResult());
//
//        RxRetrofitCache.load(this, "cacheKey", 1, compose, false)
//                .subscribe(new RxSubscribe<ArrayList<DoFindshipInfo>>(this) {
//
//                    @Override
//                    protected void _onNext(ArrayList<DoFindshipInfo> doFindshipInfos) {
//
//                    }
//
//                    @Override
//                    protected void _onError(String message) {
//                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
//                    }
//                });


//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.github.com")
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();


        ArrayList<String> paths = new ArrayList();

//        [/storage/emulated/0/tencent/MicroMsg/WeiXin/mmexport1470710885962.jpg, /storage/emulated/0/Tencent/QQ_Images/-a0a7890c037fca0.jpg, /storage/emulated/0/tencent/MicroMsg/WeiXin/mmexport1470631760962.jpg]

        paths.add("/storage/emulated/0/tencent/MicroMsg/WeiXin/mmexport1470710885962.jpg");
        paths.add("/storage/emulated/0/Tencent/QQ_Images/-a0a7890c037fca0.jpg");

//        Map<String, RequestBody> photos = new HashMap<>();
//
//        for (int i = 0; i < paths.size(); i++) {
//            photos.put("file" + i + "\"; filename=\"icon.png", RequestBody.create(MediaType.parse("multipart/form-data"), new File(paths.get(i))));
////            "file\"; filename=\"image.png\""
//        }


    }


    /**
     * @param paths
     * @return
     */
    public Map<String, RequestBody> uploadMany(ArrayList<String> paths) {
        Map<String, RequestBody> photos = new HashMap<>();
        if (paths.size() > 0) {
            for (int i = 0; i < paths.size(); i++) {
                photos.put("file" + i + "\"; filename=\"icon.png", RequestBody.create(MediaType.parse("multipart/form-data"), new File(paths.get(i))));
            }
        }
        return photos;

    }
}
