package com.example.a.book_recommender;

import android.os.AsyncTask;
import android.util.Log;

class Async_stopword extends AsyncTask<String[], Void, String[]> {
    @Override
    protected String[] doInBackground(String[]... strings) {
        String[] stopped_word = new String[200];
        String[] temp_word = strings[0];

        for (int i = 0; i < temp_word.length; i++) {
            Stopped_word3 stopword = new Stopped_word3();
            String stoppedword = stopword.main(temp_word[i]);
            stopped_word[i] = stoppedword;
        }
        Log.d(stopped_word[0],"stoppedword0");
        Log.d(stopped_word[1],"stoppedword1");
        Log.d(stopped_word[2],"stoppedword2");
        return (stopped_word);

    }
}



