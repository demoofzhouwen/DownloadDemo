package com.wondersgroup.downloaddemo;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 */
public class HttpDown extends Thread {
    private String mUrl;
    public HttpDown(String mUrl) {
        this.mUrl = mUrl;
    }
    @Override
    public void run() {
        URL url;
        try {
            url = new URL(mUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            InputStream in = conn.getInputStream();
            File downloadFile;
            File sdFile;
            FileOutputStream out = null;
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_UNMOUNTED)){
                downloadFile = Environment.getExternalStorageDirectory();
                sdFile = new File(downloadFile, "1.txt");
                out = new FileOutputStream(sdFile);
            }
            //buffer 4k
            byte[] buffer = new byte[1024 * 4];
            int len = 0;
            while((len = in.read(buffer)) != -1){
                if(out != null)
                    out.write(buffer, 0, len);
            }
            //close resource
            if(out != null)
                out.close();
            if(in != null){
                in.close();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

