package co.sspp.goodserapp.home;

import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.sspp.goodserapp.BaseActivity;
import co.sspp.goodserapp.R;
import co.sspp.goodserapp.rx.Api;
import co.sspp.goodserapp.rx.CacheManager;
import co.sspp.goodserapp.rx.RxHelper;
import co.sspp.goodserapp.rx.RxRetrofitCache;
import co.sspp.goodserapp.rx.RxSubscribe;
import me.yokeyword.indexablelistview.IndexHeaderEntity;
import me.yokeyword.indexablelistview.IndexableStickyListView;
import rx.Observable;

public class PickPortActivity extends BaseActivity {

    @BindView(R.id.searchview)
    SearchView mSearchview;
    @BindView(R.id.indexListView)
    IndexableStickyListView mIndexListView;


    private CityAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_port);
        ButterKnife.bind(this);


        mAdapter = new CityAdapter(this);
        mIndexListView.setAdapter(mAdapter);


        // 初始化数据
        initPorts();


        mIndexListView.setOnItemContentClickListener((v, indexEntity) -> {
            DoPortInfo cityEntity = (DoPortInfo) indexEntity;
            Toast.makeText(PickPortActivity.this, "选择了" + cityEntity.getName(), Toast.LENGTH_SHORT).show();
        });
    }


    private void initPorts() {
        Observable<ArrayList<DoPortInfo>> compose = Api.getDefault().ports()
                .compose(RxHelper.<ArrayList<DoPortInfo>>handleResult());

        RxRetrofitCache.load(this, "pick", CacheManager.ONE_DAY, compose, false)
                .subscribe(new RxSubscribe<ArrayList<DoPortInfo>>(this) {
                    @Override
                    protected void _onNext(ArrayList<DoPortInfo> doPortInfos) {

                        addHot(doPortInfos);

                    }

                    @Override
                    protected void _onError(String message) {
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void addHot(ArrayList<DoPortInfo> doPortInfos) {


        // 添加热门城市Header
        IndexHeaderEntity<DoPortInfo> hotHeader = new IndexHeaderEntity<>();
        hotHeader.setHeaderTitle("热门港口");
        hotHeader.setIndex("热");

//        ArrayList<DoPortInfo> hotIndexEntityList = new ArrayList<>();
//
//        ArrayList<DoPortInfo> notHotIndexEntityList = new ArrayList<>();
//
//        for (DoPortInfo hots : doPortInfos) {
//            if (0 != hots.getIsHot()) {
//                hotIndexEntityList.add(hots);
//            } else {
//                notHotIndexEntityList.add(hots);
//            }
//        }


        Observable.from(doPortInfos)
                .filter(doPortInfo -> doPortInfo.getIsHot() != 0)
                .toList()
                .subscribe(hotHeader::setHeaderList);


        Observable.from(doPortInfos)
                .filter(doPortInfo -> doPortInfo.getIsHot() == 0)
                .toList().subscribe(doPortInfos1 -> {

            mIndexListView.bindDatas(doPortInfos1, hotHeader);
        });


        // 搜索
        mSearchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // 委托处理搜索
                mIndexListView.searchTextChange(newText);
                return true;
            }
        });

    }


}
