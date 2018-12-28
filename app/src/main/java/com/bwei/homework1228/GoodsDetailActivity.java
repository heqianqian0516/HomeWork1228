package com.bwei.homework1228;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import bean.Goods;
import butterknife.BindView;
import butterknife.ButterKnife;
import utils.GlideImageLoader;

public class GoodsDetailActivity extends AppCompatActivity {

    @BindView(R.id.text_title)
    TextView mTextTitle;
    @BindView(R.id.text_price)
    TextView mTextPrice;
    private Goods mGoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        mGoods = (Goods) bundle.getSerializable("goods");
        mTextTitle.setText(mGoods.getTitle());
        mTextPrice.setText(mGoods.getPrice()+"");
        initBanner();
    }

    private void initBanner() {
        List<String> imageList = new ArrayList<>();
        String[] imageurls = mGoods.getImages().split("https");
        for (int i = 0; i < imageurls.length; i++) {
            if (!TextUtils.isEmpty(imageurls[i])) {
                String url = "https" + imageurls[i];
                url = url.substring(0, url.lastIndexOf(".jpg") + ".jpg".length());
                imageList.add(url);

            }
        }
        Banner banner = findViewById(R.id.goods_banner);
        banner.setImageLoader(new GlideImageLoader());
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        banner.setImages(imageList);
        banner.isAutoPlay(true);
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        banner.start();
    }
}
