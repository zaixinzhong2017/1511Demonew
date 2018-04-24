package com.example.rendadaibiao.item.okhttp;

import android.os.Handler;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by jiachengbing on 2017/10/13.
 */

public abstract class OnUiCallback implements Callback{
    private Handler handler=OkHttpUtils.getInstance().getHandler(); public abstract void onFailed(Call call, IOException e); public abstract void onSuccess(String result)throws IOException; @Override public void onFailure(final Call call, final IOException e) { handler.post(new Runnable() { @Override public void run() { onFailure(call,e); } }); } @Override public void onResponse(Call call, final Response response) throws IOException { final String result=response.body().string(); handler.post(new Runnable() { @Override public void run() { try { onSuccess(result); } catch (IOException e) { e.printStackTrace(); } } }); }







}
