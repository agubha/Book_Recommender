package com.example.a.book_recommender;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.a.book_recommender.Adapter.Book_db;
import com.example.a.book_recommender.Adapter.Book1object;
import com.example.a.book_recommender.Adapter.booklistadapter;
import com.github.clans.fab.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class HomePage extends AppCompatActivity {

    FloatingActionButton fab_suggest_books, fab_edit_db_books, fab_logout;
    ListView book_list_view;

    DatabaseReference fd2databasereference = FirebaseDatabase.getInstance().getReference().child("Books_db");
    List<Book_db> book_dblist;
    ArrayList<Book1object> books;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        book_list_view = findViewById(R.id.list_view_books);
        book_dblist = new ArrayList<>();
        books = new ArrayList<>();

        //floating action button
        fab_suggest_books = findViewById(R.id.fab_suggest_books);
        fab_suggest_books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage.this, SearchBooks.class));
            }
        });
        fab_edit_db_books = findViewById(R.id.fab_edit_books_db);
        fab_edit_db_books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage.this, AdminAdd.class));
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        fd2databasereference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                books.clear();
                for (DataSnapshot bookSnapshot : dataSnapshot.getChildren()) {
                    Book_db book_db = bookSnapshot.getValue(Book_db.class);
                    Book1object book1object = new Book1object();
                    assert book_db != null;
                    book1object.bookauthor = book_db.getBook_Author();
                    book1object.bookdate = book_db.getBook_date();
                    book1object.bookdetail = book_db.getBook_detail();
                    book1object.bookname = book_db.getBook_Name();
                    book1object.bookisbn = book_db.getBook_isbn();
//                    Log.d("data", book1object.bookauthor);

                    books.add(book1object);

//                    if (book_db != null) {
//                        Log.d("TAG"," name="+book_db.getBook_Name()+ " author="+book_db.getBook_Author()+" bookdate="+book_db.getBook_date()+" bookdetail="+book_db.getBook_token());
//                    }
                    book_list_view.setAdapter(new booklistadapter(getApplicationContext(), books));

                }
               /* Book_db_list adapter = new Book_db_list(HomePage.this, book_dblist);
                book_list_view.setAdapter(adapter);*/


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}


