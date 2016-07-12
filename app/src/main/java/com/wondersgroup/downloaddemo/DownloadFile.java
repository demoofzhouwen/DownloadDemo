package com.wondersgroup.downloaddemo;

import android.widget.TextView;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import java.io.File;

/**
 * Created by zhoudingwen on 2016/6/8.
 */
public class DownloadFile {
    public static void download(final TextView testTextView,String url, String target ){
        HttpUtils http = new HttpUtils();
        HttpHandler handler = http.download(
                url,//文件下载路径
                target,//文件保存路径
                true, // 如果目标文件存在，接着未完成的部分继续下载。服务器不支持RANGE时将从新下载。
                true, // 如果从请求返回信息中获取到文件名，下载完成后自动重命名。
                new RequestCallBack<File>() {

                    @Override
                    public void onStart() {
                        testTextView.setText("conn...");
                    }

                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                        testTextView.setText(((float)current/total)*100+"%");
                    }

                    @Override
                    public void onSuccess(ResponseInfo<File> responseInfo) {
                        testTextView.setText("downloaded:" + responseInfo.result.getPath());
                    }


                    @Override
                    public void onFailure(HttpException error, String msg) {
                        testTextView.setText(msg);
                    }
                });

//调用cancel()方法停止下载
        //handler.cancel();
    }
    public static void download2(final TextView testTextView,String url, String target ){
        HttpUtils http = new HttpUtils();
//调用cancel()方法停止下载
        //handler.cancel();
    }
}
