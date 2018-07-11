package com.example.a.book_recommender;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.a.book_recommender.Adapter.Bookdetailobject;

import java.util.ArrayList;
import java.util.Arrays;

public class SearchBooks extends AppCompatActivity {
    EditText find_book_user_et;
    Button find_book_user_btn, find_token_btn;
    String find_book_user_string;
    ArrayList<String> final_output;
    ArrayList<Bookdetailobject> bookdetailobjects;
    Async_Task async = new Async_Task();
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_books);
        bookdetailobjects = new ArrayList<>();
        find_book_user_btn = findViewById(R.id.find_book_user_btn);
        find_book_user_et = findViewById(R.id.find_book_user_et);
        find_token_btn = findViewById(R.id.find_token_btn);
        listView = findViewById(R.id.list_token);
        find_book_user_btn.setClickable(false);
        find_token_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                find_book_user_string = String.valueOf(find_book_user_et.getText());
                String[] splitted_string = Step1split(find_book_user_string);
                async.execute(splitted_string);
                find_token_btn.setClickable(false);
                find_book_user_btn.setClickable(true);


            }
        });


        find_book_user_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchBooks.this, DisplaySimilarBooks.class);
                intent.putStringArrayListExtra("token",final_output);
                startActivity(intent);
            }
        });
    }


    @SuppressLint("StaticFieldLeak")
    public class Async_Task extends AsyncTask<String[], Void, ArrayList<String>> {


        @Override
        protected ArrayList<String> doInBackground(String[]... strings) {

            String[] wordtostop = new String[500];
            String[] wordtostem = strings[0];
            ArrayList<String> valuetoreturn;

            /*step 1 stemming*/
            for (int i = 0; i < wordtostem.length; i++) {
                PorterStemmer2 porterStemmer2 = new PorterStemmer2();
                wordtostop[i] = porterStemmer2.stemWord(wordtostem[i]);
            }
            /*Step 2 async stopword*/
            //for each
            ArrayList<String> stemmed_list_before_stopword = new ArrayList<>(Arrays.asList(wordtostop));
            StoppedWord3 stoppedword3 = new StoppedWord3();
            valuetoreturn = stoppedword3.main(stemmed_list_before_stopword);
            return valuetoreturn;

        }

        @Override
        protected void onPostExecute(ArrayList<String> strings) {

            final_output = new ArrayList<>();
            final_output = strings;
            final_output.removeAll(Arrays.asList(null, ""));

            String[] newlist1 = new String[final_output.size()];
            newlist1 = final_output.toArray(newlist1);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(SearchBooks.this, android.R.layout.simple_list_item_1, newlist1);
            listView.setAdapter(adapter);


        }


    }


    public String[] Step1split(String step_1_split) {
        return step_1_split.split("\\s+");
    }

    @Override
    protected void onStart() {
        super.onStart();
        find_book_user_btn.setClickable(false);

    }
}