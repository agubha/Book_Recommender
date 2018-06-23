package com.example.a.book_recommender;

import android.os.AsyncTask;

class Async_Stemming extends AsyncTask<String[], Void, String[]> {

    @Override
    protected String[] doInBackground(String[]... strings) {
        String[] stemmed_list = new String[200];
        String[] word2 = strings[0];

        // max length of paragraph is 200 fixed by String[] stemmed_list = new String[200];
        //        Log.i(Arrays.toString(strings),"async_stem");
        for (int i = 0; i < word2.length; i++) {
            PorterStemmer2 porterStemmer2 = new PorterStemmer2();
            //calling stemer method
            String stemmed = porterStemmer2.stemWord(word2[i]);
            stemmed_list[i] = stemmed;


        }
        return stemmed_list;
    }

    @Override
    protected void onPostExecute(String[] strings) {
        super.onPostExecute(strings);
        String[] word3 = strings;
//        Log.i(word3[0],"onPostExecute123");
//        Log.i(word3[1],"onPostExecute2345");
//        Log.i(word3[2],"onPostExecute3456");

        Async_stopword async_stopword = new Async_stopword();
        async_stopword.execute(strings);

    }

}

