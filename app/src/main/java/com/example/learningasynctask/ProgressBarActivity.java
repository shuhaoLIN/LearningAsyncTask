package com.example.learningasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;

/**
 * 主要展示进度的显示与UI的更新，以及cancel的操作
 */
public class ProgressBarActivity extends AppCompatActivity {
    ProgressBar progressBar;
    MyAsyncTask myAsyncTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute();
    }

    /**
     * 推出页面的时候，将其cancel掉
     */
    @Override
    protected void onPause() {
        super.onPause();
        if(myAsyncTask != null){
            if(myAsyncTask.getStatus() == AsyncTask.Status.RUNNING){
                myAsyncTask.cancel(true);
            }
        }
    }

    private class MyAsyncTask extends AsyncTask<Void, Integer, Void>{

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if(!isCancelled()){ //通过是否为cancel状态，决定是否进行下一步
                progressBar.setProgress(values[0]);
            }
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for(int i=0;i<100;i++){
                if(!isCancelled()){ //通过是否为cancel状态，决定是否进行下一步
                    publishProgress(i);
                    try {
                        Thread.sleep(300);//模拟耗时操作
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    break;
                }
            }
            return null;
        }
    }
}
