package com.example.volleytest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.Cache;
import com.android.volley.Cache.Entry;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.volleytest.app.AppController;
import com.example.volleytest.net_utils.Const;

import java.io.UnsupportedEncodingException;

public class imgRequest extends Activity {

    private static final String TAG = imgRequest.class.getSimpleName();
    private Button btnImageReq;
    private NetworkImageView imgNetworkView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_request);

        btnImageReq = (Button) findViewById(R.id.imgRequest);
        imgNetworkView = (NetworkImageView) findViewById(R.id.imgNetwork);
        imageView = (ImageView) findViewById(R.id.imageView);

        btnImageReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeImageRequest();
            }
        });
    }

    private void makeImageRequest() {
        ImageLoader  imageLoader = AppController.getInstance().getImageLoader();

        imgNetworkView.setImageUrl(Const.URL_IMAGE, imageLoader);

        imageLoader.get(Const.URL_IMAGE, ImageLoader.getImageListener(
                imageView, R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground));

        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Entry entry = cache.get(Const.URL_IMAGE);
        if(entry != null){
            try{
                String data = new String(entry.data,"UTF-8");
            } catch(UnsupportedEncodingException e){
                e.printStackTrace();
            }
        }
        else{

        }


    }



}