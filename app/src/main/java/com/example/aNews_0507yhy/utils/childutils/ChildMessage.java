package com.example.aNews_0507yhy.utils.childutils;

public class ChildMessage {
    //定义要加载的item控件的属性   数据少的话可以直接使用内部类的形式

    private int img;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getChlidmsgname() {
        return chlidmsgname;
    }

    public void setChlidmsgname(String chlidmsgname) {
        this.chlidmsgname = chlidmsgname;
    }

    private String chlidmsgname;
    public ChildMessage(int img, String chlidmsgname) {
        this.img = img;
        this.chlidmsgname = chlidmsgname;
    }


}
