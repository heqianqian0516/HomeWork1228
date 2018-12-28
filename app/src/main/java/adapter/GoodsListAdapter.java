package adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwei.homework1228.GoodsDetailActivity;
import com.bwei.homework1228.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import bean.Goods;

public class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.GoodsHodler> {

    private List<Goods> mList = new ArrayList<>();//数据集合
    private Context context;


    public final static int LINEAR_TYPE = 0;//线性
    public final static int GRID_TYPE = 1;//网格

    private int viewType = LINEAR_TYPE;

    private OnItemClickListener onItemClickListener;

    private OnItemLongClickListener onItemLongClickListener;

    public GoodsListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return viewType;
    }

    //设置item的视图类型
    public void setViewType(int viewType) {
        this.viewType = viewType;
    }


    @NonNull
    @Override
    public GoodsHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = null;
        if (viewType == LINEAR_TYPE) {//通过第二个参数viewType判断选用的视图
            view = View.inflate(viewGroup.getContext(), R.layout.goods_linear_item, null);//加载item布局
        } else {
            view = View.inflate(viewGroup.getContext(), R.layout.goods_grid_item, null);//加载item布局
        }
        GoodsHodler goodsHodler = new GoodsHodler(view);

        return goodsHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull final GoodsHodler goodsHodler, final int position) {
        final Goods goods = mList.get(position);//拿到商品，开始赋值

        goodsHodler.itemView.setTag(mList.get(position));

        //增加点击事件
        goodsHodler.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //————————跳转自定义的详情页面
                Intent intent = new Intent(context,GoodsDetailActivity.class);
                //使用bundle传递引用数据类型的对象
                Bundle bundle = new Bundle();
                bundle.putSerializable("goods",goods);
                //一定要把值放入了。
                intent.putExtras(bundle);
                context.startActivity(intent);

//                if (onItemClickListener!=null) {
//                onItemClickListener.onItemClick(goods);
//                }
            }
        });
        goodsHodler.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //*****************方案1*************
//                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(
//                        goodsHodler.itemView,"alpha",1,0
//                );
//                objectAnimator.setDuration(1000);
//                objectAnimator.setInterpolator(new LinearInterpolator());
//                objectAnimator.addListener(new Animator.AnimatorListener() {
//                    @Override
//                    public void onAnimationStart(Animator animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        mList.remove(position);//动画执行结束，移除
//                        notifyDataSetChanged();//刷新列表
//                        goodsHodler.itemView.setAlpha(1);//由于复用机制，需要重新改成不透明
//                    }
//
//                    @Override
//                    public void onAnimationCancel(Animator animation) {
//                        mList.remove(position);//动画执行结束，移除
//                        notifyDataSetChanged();//刷新列表
//                        goodsHodler.itemView.setAlpha(1);//由于复用机制，需要重新改成不透明
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animator animation) {
//
//                    }
//                });
//                objectAnimator.start();

                //***********方案二**************
                if (onItemLongClickListener!=null) {
                    onItemLongClickListener.onItemLongClick(position);
                }
                return true;
            }
        });

        goodsHodler.text.setText(goods.getTitle());
        //由于我们的数据图片提供的不标准，所以我们需要切割得到图片
        String imageurl = "https" + goods.getImages().split("https")[1];
        Log.i("dt", "imageUrl: " + imageurl);
        imageurl = imageurl.substring(0, imageurl.lastIndexOf(".jpg") + ".jpg".length());
        Uri uri=Uri.parse(imageurl);
        goodsHodler.sdv.setImageURI(uri);
       /* Glide.with(context).load(imageurl).into(goodsHodler.imageView);//加载图片*/
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 添加集合数据
     */
    public void addAll(List<Goods> data) {
        if (data != null) {
            mList.addAll(data);
        }
    }

    /**
     * 清空数据
     */
    public void clearList() {
        mList.clear();
    }

    /**
     * 设置点击方法
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void remove(int position) {
        mList.remove(position);
    }

    public class GoodsHodler extends RecyclerView.ViewHolder {
        TextView text;
       /* ImageView imageView;*/
        private  SimpleDraweeView sdv;

        public GoodsHodler(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
          /*  imageView = itemView.findViewById(R.id.image);*/
            sdv = itemView.findViewById(R.id.sdv);
        }
    }


    public interface OnItemClickListener {
        void onItemClick(Goods goods);
    }


    public interface OnItemLongClickListener {
        void onItemLongClick(int position);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }
}
