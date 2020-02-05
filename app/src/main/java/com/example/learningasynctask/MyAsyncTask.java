package com.example.learningasynctask;

import android.os.AsyncTask;
import android.util.Log;

/**
 * 该类主要展示了其生命流程
 */
public class MyAsyncTask extends AsyncTask<Void, Void, Void> {
    private static final String TAG = MyAsyncTask.class.getSimpleName();

    @Override
    protected Void doInBackground(Void... voids) {
        Log.d(TAG, "doInBackground: ");
        publishProgress();
        return null;
    }

    @Override
    protected void onPreExecute() {
        Log.d(TAG, "onPreExecute: ");
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Log.d(TAG, "onPostExecute: ");
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        Log.d(TAG, "onProgressUpdate: ");
        super.onProgressUpdate(values);
    }
}
