package com.example.a.book_recommender;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class Add_Books extends AppCompatActivity {
    EditText find_book_user_et;
    Button find_book_user_btn;
    String find_book_user_string;
    String[] text123;

    private String[] book_comparator = null;
    private String[] book_comparator2 = null;

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
                String[] book_user_processed;
                book_user_processed = Step1split(find_book_user_string);
            }
        });

    }


    public String[] Step1split(String step_1_split) {

        String[] step2string = splitting(step_1_split);
        Async_Stemming stem = new Async_Stemming();
        stem.execute(step2string);

        return null;
    }

    public String[] splitting(String text_to_split) {

        String[] splitted_word = text_to_split.split("\\s+");
//        Log.i(Arrays.toString(splitted_word),"TESTINGMESAGE");
//LOG OUTPUT BELOW
//for(int i = 0 ; i<splitted_word.length;i++)
//
//{
//    Log.i(splitted_word[i],"member name");
//
//}
        return splitted_word;
    }
}
