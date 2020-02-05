package com.example.learningasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 该类主要用于展示下载功能，并且展示了UI更新的操作
 */
public class ImageActivity extends AppCompatActivity {
    private ImageView imageView;
    private ProgressBar progressBar;
    private MyAsyncTask myAsyncTask;
    private final String IMAGE_URL = "https://www.imooc.com/static/img/index/logo.png";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        imageView = (ImageView) findViewById(R.id.image);

        myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute(IMAGE_URL);
    }

    /**
     * URL，进度暂无，bitmap返回
     */
    private class MyAsyncTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            progressBar.setVisibility(View.GONE);
            imageView.setImageBitmap(bitmap);
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String url = (String) strings[0];
            URLConnection connection;
            InputStream is = null;
            Bitmap bitmap = null;
            BufferedInputStream bis = null;
            try {
                connection = new URL(url).openConnection();
                is = connection.getInputStream();
                Thread.sleep(1000);
                bis = new BufferedInputStream(is);
                bitmap = BitmapFactory.decodeStream(bis);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                try {
                    is.close();
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return bitmap;
        }
    }
}
