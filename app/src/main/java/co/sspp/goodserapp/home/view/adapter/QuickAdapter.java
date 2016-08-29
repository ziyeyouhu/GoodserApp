package co.sspp.goodserapp.home.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import co.sspp.goodserapp.R;

public class QuickAdapter extends BaseQuickAdapter<String> {
    public QuickAdapter(List a) {
        super(R.layout.tweet, a);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
//        helper.setText(R.id.tweetName, item.getUserName())
//                .setText(R.id.tweetText, item.getText())
//                .setText(R.id.tweetDate, item.getCreatedAt())
//                .setVisible(R.id.tweetRT, item.isRetweet())
//                .setOnClickListener(R.id.tweetAvatar, new OnItemChildClickListener())
//                .setOnClickListener(R.id.tweetName, new OnItemChildClickListener())
//                .linkify(R.id.tweetText);

    }

}