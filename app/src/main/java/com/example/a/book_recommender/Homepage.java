package com.example.a.book_recommender;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.a.book_recommender.Adapter.Book_db;
import com.example.a.book_recommender.Adapter.Book_db_list;
import com.example.a.book_recommender.Adapter.Bookobject;
import com.example.a.book_recommender.Adapter.booklistadapter;
import com.github.clans.fab.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Homepage extends AppCompatActivity {

    FloatingActionButton fab_suggest_books, fab_edit_db_books, fab_logout;
    ListView book_list_view;

    DatabaseReference fd2databasereference = FirebaseDatabase.getInstance().getReference().child("Books_db");
    List<Book_db> book_dblist;
    ArrayList<Bookobject> books;


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
                startActivity(new Intent(Homepage.this, Add_Books.class));
            }
        });
        fab_edit_db_books = findViewById(R.id.fab_edit_books_db);
        fab_edit_db_books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Homepage.this, Add_details_to_db.class));
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        fd2databasereference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot bookSnapshot : dataSnapshot.getChildren()) {
                    Book_db book_db = bookSnapshot.getValue(Book_db.class);
                    Bookobject bookobject = new Bookobject();
                    bookobject.bookauthor = book_db.getBook_Author();
                    bookobject.bookdate = book_db.getBook_date();
                    bookobject.bookdetail = book_db.getBook_detail();
                    bookobject.bookname = book_db.getBook_Name();

                    Log.d("data", bookobject.bookauthor);

                    books.add(bookobject);

                    if (book_db != null) {
                        Log.d("TAG"," name="+book_db.getBook_Name()+ " author="+book_db.getBook_Author()+" bookdate="+book_db.getBook_date()+" bookdetail="+book_db.getBook_detail());
                    }
                    book_list_view.setAdapter(new booklistadapter(getApplicationContext(),books));

                }
               /* Book_db_list adapter = new Book_db_list(Homepage.this, book_dblist);
                book_list_view.setAdapter(adapter);*/


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
//        addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                book_dblist.clear();
//                for (DataSnapshot bookSnapshot : dataSnapshot.getChildren()) {
////                    Book_db book_db = bookSnapshot.getValue(Book_db.class);
//                    Book_db book_db = new Book_db();
//                    String key = bookSnapshot.getKey();
//                    String text = bookSnapshot.child(key).getValue(String.class);
//                    Log.d("TEST1",text+"");
////                    book_db.setBook_Author(); = bookSnapshot
//
//                    book_dblist.add(book_db);
//
//                }
//                Book_db_list adapter = new Book_db_list(Homepage.this, book_dblist);
//                book_list_view.setAdapter(adapter);
//
//
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
//}
//
//

/**
 * //        //FIre base new
 * //        DatabaseReference fdreference = FirebaseDatabase.getInstance().getReference("Book_db");
 * ////reading and adding root-child of book_db
 * //
 * //        ValueEventListener eventListener = new ValueEventListener() {
 * //            // ArrayList<String> Book_isbn_list=null;
 * //            @Override
 * //            public void onDataChange(DataSnapshot dataSnapshot) {
 * //                for (DataSnapshot ds : dataSnapshot.getChildren()) {
 * //
 * //                    String Book_isbn_string = ds.getKey();
 * //                    Log.i(Book_isbn_string, "VALUE YO HO");
 * //                    Book_isbn_list.add(Book_isbn_string);
 * ////                    Log.i(Book_isbn_list.get(0),"message 2");
 * ////                    Log.i(String.valueOf(Book_isbn_list.size()), "Message");
 * //                    Log.d("inside listener", "isbnlist: " + Book_isbn_list.size());
 * //                }
 * //            }
 * //
 * //            @Override
 * //            public void onCancelled(@NonNull DatabaseError databaseError) {
 * //            }
 * //        };
 * //
 * //        fdreference.addListenerForSingleValueEvent(eventListener);
 * ////        for (int i = 0; i < Book_isbn_list.size(); i++) {
 * ////        Log.i(Book_isbn_list.get(0),"message 2");
 * ////        Log.i(String.valueOf(Book_isbn_list.size()), "Message");
 * //        Log.d("Outside listener", "isbnlist: " + Book_isbn_list.size());
 * ////        }
 * //reading and adding complete
 * //level 3 reading and displaying
 * <p>
 * <p>
 * //        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, book_data);
 * <p>
 * <p>
 * //        book_list_view.setAdapter(arrayAdapter);
 * //        for (int i = 0; i < Book_isbn_list.size(); i++) {
 * //            String temp = Book_isbn_list.get(i);
 * //            DatabaseReference fd2reference = FirebaseDatabase.getInstance().getReference().child("Books_db").child(temp);
 * //            Log.i(temp, "Book isbn list item no" + i);
 * //            fd2reference.addChildEventListener(new ChildEventListener() {
 * //                @Override
 * //                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
 * //
 * //
 * //                    String value = dataSnapshot.getValue(String.class);
 * //                    Log.i(value, "value message 1");
 * //                    book_data.add(value);
 * ////                    String value2 = dataSnapshot.getValue(String.class);
 * ////                    Log.i(value, "value message 2");
 * ////                    book_data.add(value2);
 * //                    arrayAdapter.notifyDataSetChanged();
 * //                }
 * //
 * //                @Override
 * //                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
 * //                }
 * //
 * //                @Override
 * //                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
 * //                }
 * //
 * //                @Override
 * //                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
 * //                }
 * //
 * //                @Override
 * //                public void onCancelled(@NonNull DatabaseError databaseError) {
 * //                }
 * //            });
 * //        }
 **/


