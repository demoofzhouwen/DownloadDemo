package com.wondersgroup.downloaddemo;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.IOException;
import java.net.URL;

import cz.msebera.android.httpclient.Header;

/**
 * android客户端下载demo
 */
public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private String url;//网络请求地址
    private TelephonyManager tManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tv);
        tManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
       // http://192.168.1.127:8080/jk/npcservice/files2.do?json={%22fileid%22:%2263908426-69ae-4972-ab8d-6a9d09aa4b52%22}
        url = "http://192.168.1.127:8080/jk/npcservice/files2.do?json={%22fileid%22:%2263908426-69ae-4972-ab8d-6a9d09aa4b52%22}";

    }



    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btn_xutils_down:
                DownloadFile.download(textView,url, Environment.getExternalStorageDirectory().getPath() + "/Desktop.zip");
                break;
            case R.id.btn_web_down:
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.btn_basehttp_down:
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        BaseHttp http=new BaseHttp();
                        try {
                            http.getUrlBytes(url);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.run();
                break;
            case R.id.btn_android_async_http_down:
                HttpUtil.get(url, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        System.out.println(new String(responseBody));
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                });
                break;
            case R.id.btn_opean:
                Intent excelFileIntent =AndroidFileUtil.openFile( Environment.getExternalStorageDirectory().getPath() + "/Desktop.zip");
                try {
                    startActivity(excelFileIntent);
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "没有能打开这种类型文件的软件！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_webType:
                int connectedType = HttpUtil.getConnectedType(MainActivity.this);
                Log.i("网络类型",connectedType+"");
                break;
        }
    }


}
