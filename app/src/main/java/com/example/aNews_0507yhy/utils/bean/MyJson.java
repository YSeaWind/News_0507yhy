package com.example.aNews_0507yhy.utils.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

public class MyJson  implements MultiItemEntity, Serializable {
    private String reason;
    private Result result;
    private int itemType;

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public MyJson(int itemType) {
        this.itemType = itemType;
    }

    public MyJson() {
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "MyJson{" +
                "reason='" + reason + '\'' +
                ", result=" + result +
                '}';
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
