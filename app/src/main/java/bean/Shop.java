package bean;

import com.bwei.homework1228.R;

import java.util.List;

public class Shop {
    List<Goods> list;
    String sellerName;
    String sellerid;
    int textColor = 0xffffffff;
    int background = R.color.grayblack;

    boolean check;


    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public int getBackground() {
        return background;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean isCheck() {
        return check;
    }

    public List<Goods> getList() {
        return list;
    }

    public void setList(List<Goods> list) {
        this.list = list;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerid() {
        return sellerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid;
    }
}
