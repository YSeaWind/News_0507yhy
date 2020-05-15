package com.example.aNews_0507yhy.utils;

import com.example.aNews_0507yhy.utils.bean.MyJson;
import com.google.gson.Gson;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {
    public String connectServer(String name,String value,String url) throws IOException, JSONException {
        //okhttp连接器
        OkHttpClient client = new OkHttpClient();
        //请求体
        RequestBody body = new FormBody.Builder().add(name, value).build();
        //获取请求
        Request request = new Request.Builder().post(body).url(url).build();
        //发送请求，获得响应（在服务器中获取的数据） 一定要放在子线程中执行
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()){
            //获取响应中的数据，并转换成String
            String data=response.body().string();
            return data;
        }else {
            //获取响应中的数据，并转换成String
            return "网络连接失败，请检查您的网络";
        }
    }
    public MyJson getNewsData(String type) throws IOException, JSONException {
        String url="http://v.juhe.cn/toutiao/index?type="+type+"&key=00035a23333f7e746a118c9ded1dfa8c";
        String topData = connectServer("","", url);
        Gson gson = new Gson();
        MyJson myJson = gson.fromJson(topData, MyJson.class);
        return myJson;
    }

}
