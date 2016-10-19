package com.qf.okhttpdem;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private OkHttpClient okHttpClient;
    private String urlstr = "http://ikft.house.qq.com/index.php?guid=866500021200250&devua=appkft_1080_1920_XiaomiMI4LTE_1.8.3_Android19&devid=866500021200250&appname=QQHouse&mod=appkft&reqnum=10&pageflag=0&act=newslist&channel=71&buttonmore=0&cityid=1";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);

        //1.创建Okhttpclient
        okHttpClient = new OkHttpClient();
    }

    public void btnclick(View view){
        switch (view.getId()) {
            case R.id.btn1:
                //2.创建Request请求对象
                Request request = new Request.Builder()
                        .url(urlstr)
                        .build();
                //3.基于OkhttpClient对象和Request对象创建一个Call对象
                final Call call = okHttpClient.newCall(request);
                //4.发起异步get请求
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        //1.OkHttp的回调方法不在主线程中执行
                        //2.ResponseBody中的数据只能获取一次
                        String s = response.body().string();
                        Log.d("print", Thread.currentThread().getName()+"载完成------->"+s);
                        Log.d("print", Thread.currentThread().getName()+"载完成------->"+s);
                    }
                });
                break;
            case R.id.btn2:
                //2.创建Request请求对象
                Request request2 = new Request.Builder()
                        .url(urlstr)
                        .build();
                //3.基于OkhttpClient对象和Request对象创建一个Call对象
                final Call call2 = okHttpClient.newCall(request2);
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            Response response = call2.execute();
                            Log.d("print", "---->子线程:" + response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();

                break;
            case R.id.btn3:
                //post提交字符串
                //构建一个post请求对象
                MediaType mediaType = MediaType.parse("text/plain");
                RequestBody requestBody = RequestBody.create(mediaType,"post提交的字符串");
                Request request3 = new Request.Builder()
                        .url("http://10.20.153.205:8080/AndroidServer/doPostString.shtml")
                        .post(requestBody)
                        .build();
                Call call3 = okHttpClient.newCall(request3);
                call3.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.d("print", "---->获得服务器的响应体:" + response.body().string());
                    }
                });

                break;
            case R.id.btn4:
                //post提交表单
                FormBody formBody = new FormBody.Builder()
                        .add("username","admin")
                        .add("password","123456")
                        .build();
                Request request4 = new Request.Builder()
                        .url("http://10.20.153.28:8080/AndroidServer/doevent.shtml")
                        .post(formBody)
                        .build();
                Call call4 = okHttpClient.newCall(request4);
                call4.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        Log.d("print", "服务器的返回响应---->" + response.body().string());
                        Headers headers = response.headers();
                        Set<String> names = headers.names();
                        for (String name : names) {
                            Log.d("print", "-->响应头:" + name + ":" + response.header(name));
                        }
                    }
                });
                break;
            case R.id.btn5:
                File file = new File(Environment.getExternalStorageDirectory() + "/kgmusic/IMG_2740.JPG");
//                File file = new File(Environment.getExternalStorageDirectory() + "/Pictures/ic_launcher.png");
//                Log.d("print", "------>文件是否存在:" + file.exists() +Environment.getExternalStorageDirectory());

                //上传文件的头部信息
                Headers headers = Headers.of("Content-Disposition", "form-data;name=\"mFile\";filename =\"IMG_2740.JPG\"");
                RequestBody requestBody5 = RequestBody.create(MediaType.parse("multipart/form-data"), file);

                MultipartBody multipartBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("uploader", "blue")
                        .addPart(headers, requestBody5)
                        .build();

                Request request5 = new Request.Builder()
//                        .url("http://10.20.153.217:8080/AndroidServer/uploadfile.shtml")
                        .url("http://10.20.153.205:8080/AndroidServer/uploadfile.shtml")
                        .post(multipartBody)
                        .build();

                Call call5 = okHttpClient.newCall(request5);
                call5.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.d("print", "---->文件上传的响应:" + response.body().string());
                    }
                });
                break;
        }
    }
}
