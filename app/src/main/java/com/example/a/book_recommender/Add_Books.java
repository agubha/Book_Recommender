package com.example.a.book_recommender;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;

public class Add_Books extends AppCompatActivity {
    EditText find_book_user_et;
    Button find_book_user_btn;
    String find_book_user_string;
    ArrayList<String> final_output = new ArrayList<>();
    final ArrayList<String> Book_isbn_list = new ArrayList<>();
    ArrayList<String> book_data = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_books);

        find_book_user_btn = findViewById(R.id.find_book_user_btn);
        find_book_user_et = findViewById(R.id.find_book_user_et);
        find_book_user_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                find_book_user_string = find_book_user_et.getText().toString();
                Log.d(find_book_user_string, "onClick: " + find_book_user_string);
                Step1split(find_book_user_string);
                //open step 1 to split words into token
            }
        });
//        for(int i=0 ; i <final_output.size();i++){
        Log.i(String.valueOf(final_output), "FINAL RETURNED VALUE BEFORE COSINE");// RETURN NULL AND INDEX OUT OF BOUND
//        }

    }

    public String[] Step1split(String step_1_split) {
        String[] step2string = splitting(step_1_split);
        //run public class splitting that returns splitted text in String[]

        Async_Task stem = new Async_Task();

        stem.execute(step2string);

//        do{
//            Async_Task cosine_vector_similarity = new Async_Task();
//            cosine_vector_similarity.execute();
//        }while(returned_token!=null);
        return null;

    }

    public String[] splitting(String text_to_split) {

        return text_to_split.split("\\s+");
    }

    //ASYNC
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
            Stopped_word3 stoppedword3 = new Stopped_word3();
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

    //    COPIED FROM HOMEPAGE.JAVA
    public void Firebase_return_value() {
        //FIre base new
        DatabaseReference fdreference = FirebaseDatabase.getInstance().getReference().child("Books_db");
//reading and adding root-child of book_db
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String Book_isbn_string = ds.getKey();
                    Log.i(Book_isbn_string, "VALUE YO HO");
                    Book_isbn_list.add(Book_isbn_string);
                    Log.i(String.valueOf(Book_isbn_list.size()), "Message");

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        fdreference.addListenerForSingleValueEvent(eventListener);
//        for (int i = 0; i < Book_isbn_list.size(); i++) {
        Log.i(String.valueOf(Book_isbn_list.size()), "Message");
//        }
//reading and adding complete
//level 3 reading and displaying

        ListView book_list_view = findViewById(R.id.list_view_books);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, book_data);

        book_list_view.setAdapter(arrayAdapter);
        for (int i = 0; i < Book_isbn_list.size(); i++) {
            String temp = Book_isbn_list.get(i);
            DatabaseReference fd2reference = FirebaseDatabase.getInstance().getReference().child("Books_db").child(temp);
            Log.i(temp, "Book isbn list item no" + i);
            fd2reference.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


                    String value = dataSnapshot.getValue(String.class);
                    Log.i(value, "value message 1");
                    book_data.add(value);
//                    String value2 = dataSnapshot.getValue(String.class);
//                    Log.i(value, "value message 2");
//                    book_data.add(value2);
                    arrayAdapter.notifyDataSetChanged();
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        }
    }
}


