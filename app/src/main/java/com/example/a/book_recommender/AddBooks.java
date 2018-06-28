package com.example.a.book_recommender;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
    Button find_book_user_btn;
    String find_book_user_string;
    ArrayList<String> final_output = new ArrayList<>();
    ArrayList<Bookdetailobject> bookdetailobjects;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_books);
        bookdetailobjects = new ArrayList<>();
        find_book_user_btn = findViewById(R.id.find_book_user_btn);
        find_book_user_et = findViewById(R.id.find_book_user_et);
        find_book_user_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                find_book_user_string = find_book_user_et.getText().toString();
                Step1split(find_book_user_string);
                /*open step 1 to split words into token*/
            }
        });
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

    public String[] Step1split(String step_1_split) {
        String[] step2string = splitting(step_1_split);
        Async_Task stem = new Async_Task();
        stem.execute(step2string);
        return null;
    }

    public String[] splitting(String text_to_split) {

        return text_to_split.split("\\s+");
    }
    @SuppressLint("StaticFieldLeak")
    public class Async_Task extends AsyncTask<String[], Void, ArrayList<String>> {


        @Override
        protected ArrayList<String> doInBackground(String[]... strings) {

            String[] stemmed_list_after_stemming = new String[200];
            String[] word2stem = strings[0];

            Log.i(Arrays.toString(strings), "async_stem");

            //Stemming
            for (int i = 0; i < word2stem.length; i++) {
                PorterStemmer2 porterStemmer2 = new PorterStemmer2();
                String temp_stemmed = porterStemmer2.stemWord(word2stem[i]);
                stemmed_list_after_stemming[i] = temp_stemmed;
            }

            //step 2 async stopword
            ArrayList<String> list_after_stopword;
            //for each
            ArrayList<String> stemmed_list_before_stopword = new ArrayList<>(Arrays.asList(stemmed_list_after_stemming));
            StoppedWord3 stoppedword3 = new StoppedWord3();
            list_after_stopword = stoppedword3.main(stemmed_list_before_stopword);
            //returns array list
//            String[] converted_array = new String[list_after_stopword.size()];
//            converted_array = list_after_stopword.toArray(converted_array);
            //converted into array
            return list_after_stopword;
        }

        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            final_output = strings;
            Log.i(final_output.get(0), "returned token");
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
}