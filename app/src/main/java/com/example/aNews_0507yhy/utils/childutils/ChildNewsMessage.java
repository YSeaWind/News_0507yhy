package com.example.aNews_0507yhy.utils.childutils;



public class ChildNewsMessage {
    //定义要加载的item控件的属性   数据少的话可以直接使用内部类的形式
    private int img;
    private String username;
    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(    String username) {
        this.username = username;
    }



    public ChildNewsMessage(int img, String username) {
        this.img = img;
        this.username = username;
    }
}
