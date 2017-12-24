package com.zou.fund.util;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 邹远君 on 2017/12/19.
 */

public class Network {

    OkHttpClient client;
    ExecutorService executor;

    public Network() {
        client = new OkHttpClient();

        executor= Executors.newCachedThreadPool();



    }

    public void Loadhtpp(final Handler handler, final String url, final int what){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String s = http(url);
                    Message message = Message.obtain();
                    message.what=what;
                    message.obj = s;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    String http(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();


    }
}
