package com.example.a.book_recommender;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a.book_recommender.PopUp.Sign_in_pop_up;
import com.example.a.book_recommender.PopUp.Sign_up_pop_up;

public class MainPage extends AppCompatActivity {
    Button signup, signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        signup = findViewById(R.id.sign_up_btn);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainPage.this, Sign_up_pop_up.class));
            }
        });
        signin = findViewById(R.id.sign_in_btn);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainPage.this, Sign_in_pop_up.class));
            }
        });
    }
}
