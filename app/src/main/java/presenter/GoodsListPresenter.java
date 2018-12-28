package presenter;

import bean.Result;
import core.DataCall;
import model.GoodsListModel;

public class GoodsListPresenter extends BasePresenter {

    private int page=1;
    private boolean isRefresh=true;

    public GoodsListPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Result getData(Object... args) {
        isRefresh = (boolean) args[0];//是否需要刷新
        if (isRefresh){//刷新
            page = 1;
        }else{
            page++;
        }
        //调用网络请求获取数据
        Result result = GoodsListModel.goodsList((String)args[1],page+"");
        return result;
    }

    public boolean isResresh(){
        return isRefresh;
    }
}
