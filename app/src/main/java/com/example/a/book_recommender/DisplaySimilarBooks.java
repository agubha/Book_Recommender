package com.example.a.book_recommender;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.a.book_recommender.Adapter.Book2object;
import com.example.a.book_recommender.Adapter.Book_db;
import com.example.a.book_recommender.Adapter.Book_db_2;
import com.example.a.book_recommender.Adapter.booklistcompareadapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DisplaySimilarBooks extends AppCompatActivity {

    ListView book_list_view;

    DatabaseReference fd2databasereference = FirebaseDatabase.getInstance().getReference().child("Books_db_2");
    List<Book_db_2> book_dblist;
    ArrayList<Book2object> books;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_similar_books);
        book_list_view = findViewById(R.id.similar_display_lv);
        book_dblist = new ArrayList<>();
        books = new ArrayList<>();
//        Log.i("token_tester", String.valueOf(token));

//        Log.i("TOKEN RECEIVED TEST", token.get(0));
    }


    @Override
    protected void onStart() {
        super.onStart();
        final Intent intent = getIntent();
        ArrayList<String> token = intent.getStringArrayListExtra("token");
        final String[] tokenstring = token.toArray(new String[0]);

        fd2databasereference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                books.clear();
                for (DataSnapshot bookSnapshot : dataSnapshot.getChildren()) {
                    Book_db_2 book_db_2 = bookSnapshot.getValue(Book_db_2.class);
                    Book2object book2object = new Book2object();
                    assert book_db_2 != null;
                    book2object.bookauthor = book_db_2.getBook_Author();
                    book2object.bookdate = book_db_2.getBook_date();
                    book2object.bookdetaill = book_db_2.getBook_detail();
//                    Log.d("bookdetaial",book_db_2.getBook_detail().get(0));
                    book2object.bookname = book_db_2.getBook_Name();
                    book2object.bookisbn = book_db_2.getBook_isbn();
                    String[] tokenstring2= book_db_2.getBook_detail().toArray(new String[0]);
                    double value =CosineVectorSimilarity4.consineTextSimilarity(tokenstring,tokenstring2);
                    book2object.cosine=value;
                    Log.i("Cosinevalue=", String.valueOf(value));
                    books.add(book2object);

                  book_list_view.setAdapter(new booklistcompareadapter(getApplicationContext(), books));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        book_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("information", String.valueOf(i));
            }
        });
    }
}