package com.example.a.book_recommender;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

class Async_stopword extends AsyncTask<String[], Void, ArrayList<String>> {
    @Override
    protected ArrayList<String> doInBackground(String[]... strings) {
        ArrayList<String> returnedstop;
        String[] string1 = strings[0];
        //for each
        ArrayList<String> arraystring = new ArrayList<>(Arrays.asList(string1));
        Stopped_word3 stoppedword3 = new Stopped_word3();
        returnedstop = stoppedword3.main(arraystring);
        int listSize = returnedstop.size();
        for (int i = 0; i < listSize; i++) {
            Log.i(returnedstop.get(i), "member list");
        }

        return returnedstop;

    }

    @Override
    protected void onPostExecute(ArrayList<String> arrayList) {

        cosine_vector_similarity4 cosine = new cosine_vector_similarity4();
        cosine.main(arrayList);



    }
}



