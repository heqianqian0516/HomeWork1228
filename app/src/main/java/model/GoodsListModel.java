package model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import bean.Goods;
import bean.Result;
import utils.HttpUtils;

public class GoodsListModel {

    public static Result goodsList(String keywords, final String page) {
        String resultString = HttpUtils.postForm("http://www.zhaoapi.cn/product/searchProducts",
                new String[]{"keywords", "page"}, new String[]{keywords, page});

        try {
            Gson gson = new Gson();

            Type type = new TypeToken<Result<List<Goods>>>() {

            }.getType();

            Result result = gson.fromJson(resultString, type);

            return result;
        } catch (Exception e) {

        }
        Result result = new Result();
        result.setCode(-1);
        result.setMsg("数据解析异常");
        return result;
    }

}