package com.example.a.book_recommender;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a.book_recommender.Adapter.Book_db;
import com.example.a.book_recommender.Adapter.Bookdetailobject;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class AddBooks extends AppCompatActivity {
    EditText find_book_user_et;
    Button find_book_user_btn, find_token_btn;
    TextView text_to_display;
    String find_book_user_string;
    ArrayList<String> final_output;
    ArrayList<Bookdetailobject> bookdetailobjects;
    Async_Task stem = new Async_Task();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_books);
        bookdetailobjects = new ArrayList<>();
        find_book_user_btn = findViewById(R.id.find_book_user_btn);
        find_book_user_et = findViewById(R.id.find_book_user_et);
        find_token_btn = findViewById(R.id.find_token_btn);
        text_to_display = findViewById(R.id.tvDisplay);
        find_token_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                find_book_user_string = find_book_user_et.getText().toString();
                String[] splitted_string = Step1split(find_book_user_string);

                stem.execute(splitted_string);
            }
        });
       /* find_book_user_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                find_book_user_string = find_book_user_et.getText().toString();
                String[] splitted_string = Step1split(find_book_user_string);
                Async_Task stem = new Async_Task();
                stem.execute(splitted_string);
                *//*open step 1 to split words into token*//*
            }
        });*/
    }

    public void Firebase_return_value() {
        DatabaseReference fdreference = FirebaseDatabase.getInstance().getReference().child("Books_db");
        fdreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bookdetailobjects.clear();
                for (DataSnapshot bookSnapshot : dataSnapshot.getChildren()) {
                    Book_db book_db = bookSnapshot.getValue(Book_db.class);
                    Bookdetailobject bookdetailobject = new Bookdetailobject();
                    bookdetailobject.bookdetail = book_db.getBook_detail();
                    bookdetailobject.bookname = book_db.getBook_Name();
                    bookdetailobjects.add(bookdetailobject);
                    //array.add(object)
                    //book_list_view.setAdapter(new booklistadapter(getApplicationContext(), books));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        /*reading and adding completelevel 3 reading and displaying*/
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
            Log.i(final_output.get(2), "returned token");
            text_to_display.setText(final_output.get(3));

        }

    }





    @Override
    protected void onStart() {
        super.onStart();
        Firebase_return_value();
        for (Bookdetailobject bookdetailobject : bookdetailobjects) {
            Step1split(bookdetailobject.bookdetail);
        }
    }

    public String[] Step1split(String step_1_split) {
        return step_1_split.split("\\s+");
    }

}