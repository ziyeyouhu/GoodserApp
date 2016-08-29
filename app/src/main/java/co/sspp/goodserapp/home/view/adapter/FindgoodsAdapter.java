//package co.sspp.goodserapp.home.view.adapter;
//
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.chad.library.adapter.base.BaseViewHolder;
//
//import java.util.ArrayList;
//
//import co.sspp.goodserapp.R;
//import co.sspp.goodserapp.rx.DoFindshipInfo;
//import co.sspp.goodserapp.weight.BBox;
//
//public  class FindgoodsAdapter extends BaseQuickAdapter<DoFindshipInfo> {
//
//    public FindgoodsAdapter(ArrayList<DoFindshipInfo> a) {
//        super( R.layout.g_item_findship_listview,  a);
//    }
//
//
//
//    @Override
//    protected void convert(BaseViewHolder helper, DoFindshipInfo item) {
//        //        helper.setText(R.id.tweetName, item.getUserName())
////                .setText(R.id.tweetText, item.getText())
////                .setText(R.id.tweetDate, item.getCreatedAt())
////                .setVisible(R.id.tweetRT, item.isRetweet())
////                .setOnClickListener(R.id.tweetAvatar, new OnItemChildClickListener())
////                .setOnClickListener(R.id.tweetName, new OnItemChildClickListener())
////                .linkify(R.id.tweetText);
//
////        helper.setText(R.id.tv_findgoods_loaddate, item.getLoadDate())
////                .setText(R.id.tv_findgoods_startport,item.getStartPort());
//
//        BBox cb = helper.getView(R.id.cb);
//
//
//
//        cb.setOnCheckedChangeListener(new BBox.OnCheckedChangeListener() {
//            @Override
//            public void onChange(boolean checked) {
//               item.setChecked(checked);
//
//            }
//        });
//
//        cb.setChecked(item.isChecked());
//
//    }
//
//
//}