package com.example.a.book_recommender;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a.book_recommender.Adapter.Book_db;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.HashMap;

public class Add_details_to_db extends AppCompatActivity {

    Button accept_changes_btn, cancel_changes_btn;
    EditText Book_name, Book_isbn, Book_author, Book_p_date, Book_detail;
    String Book_name_str, Book_author_str, Book_detail_str, Book_date_str;
    String Book_isbn_str, name = "Book Name", author = "Book Author", date = "Date of Publication";
    private DatabaseReference f2database;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details_to_db);
        accept_changes_btn = findViewById(R.id.accept_btn);
        cancel_changes_btn = findViewById(R.id.cancel_btn);
        Book_name = findViewById(R.id.db_book_name_et);
        Book_isbn = findViewById(R.id.db_book_isbn_et);
        Book_author = findViewById(R.id.db_book_author_et);
        Book_p_date = findViewById(R.id.db_book_date_et);
        Book_detail = findViewById(R.id.db_book_detail_et);

        //database reference
        accept_changes_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//add detail to db
                add_books_to_db();


            }
        });


    }

    private void add_books_to_db() {
        Book_name_str = Book_name.getText().toString();
        Book_author_str = Book_author.getText().toString();
        Book_detail_str = Book_detail.getText().toString();
        Book_isbn_str = Book_isbn.getText().toString();
        Book_date_str = Book_p_date.getText().toString();
        if (!TextUtils.isEmpty(Book_author_str) && !TextUtils.isEmpty(Book_isbn_str) && !TextUtils.isEmpty(Book_author_str) && !TextUtils.isEmpty(Book_detail_str)) {
            //creating child in root
            f2database = FirebaseDatabase.getInstance().getReference("Books_db");
            Book_db book_db = new Book_db(Book_name_str,Book_author_str, Book_detail_str, Book_date_str);
            f2database.child(Book_isbn_str).setValue(book_db).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Add_details_to_db.this, "Push Successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Add_details_to_db.this, "Push Unsuccessful", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(this, "Text Field Empty", Toast.LENGTH_SHORT).show();
        }
// HashMap<String, String> bookhashmap = new HashMap<>();
//            bookhashmap.put("Book Name", Book_name_str);
//            bookhashmap.put("Book Author", Book_author_str);
//            bookhashmap.put("Book detail", Book_detail_str);
//            f2database.setValue(bookhashmap).addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//                    if (task.isSuccessful()) {
//                        Toast.makeText(Add_details_to_db.this, "Push Successful", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(Add_details_to_db.this, "Push Unsuccessful", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });

    }
}
