package com.wondersgroup.downloaddemo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;


public class HttpUtil {
    private static AsyncHttpClient client = new AsyncHttpClient(); //实例话对象

    static {
        client.setTimeout(11000);//设置链接超时，如果不设置，默认为10s
    }

    public static void get(String urlString, AsyncHttpResponseHandler res)//用一个完整url获取一个string对象
    {
        client.get(urlString, res);
    }

    public static void get(String urlString, RequestParams params, AsyncHttpResponseHandler res)//url里面带参数
    {
        client.get(urlString, params, res);
    }

    public static void get(String urlString, JsonHttpResponseHandler res)//不带参数，获取json对象或者数组
    {
        client.get(urlString, res);
    }

    public static void get(String urlString, RequestParams params, JsonHttpResponseHandler res)//带参数，获取json对象或者数组
    {
        client.get(urlString, params, res);
    }

    public static void get(String uString, BinaryHttpResponseHandler bHandler)//下载数据使用，会返回byte数据
    {
        client.get(uString, bHandler);
    }

    public static void post(String urlString, AsyncHttpResponseHandler res) //不带参数
    {
        client.post(urlString, res);
    }

    public static void post(String urlString, RequestParams params, AsyncHttpResponseHandler res)//url里面带参数
    {
        client.post(urlString, params, res);
    }

    public static AsyncHttpClient getClient() {
        return client;
    }

    /**
     * 判断用户的处于什么网络
     * @param context
     * @return 1 ：wifi 0：移动网络 -1 ：当前没有网络
     */
    public static int getConnectedType(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                return mNetworkInfo.getType();
            }
        }
        return -1;
    }
}
