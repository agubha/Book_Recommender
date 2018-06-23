package com.example.a.book_recommender;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

public class Homepage extends AppCompatActivity {

    FloatingActionButton fab_add_books, fab_edit_books, fab_logout;
    private ArrayList<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        generateListContent();
        ListView book_list_view = findViewById(R.id.list_view_books);
        book_list_view.setAdapter(new myBookListAdapter(this, R.layout.book_list_view_layout, data));
        book_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(Homepage.this, "List item was clicked at " + position, Toast.LENGTH_SHORT).show();
            }
        });
        fab_add_books = findViewById(R.id.fab_add_books);
        fab_add_books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Homepage.this, Add_Books.class));
            }
        });


    }

    private void generateListContent() {
        for (int i = 0; i < 55; i++) {
            data.add("this is row number" + 1);

        }
    }


    //adapter below

    private class myBookListAdapter extends ArrayAdapter<String> {
        private int layout;
        private Context context;


        public myBookListAdapter(@NonNull Context context, int resource, List<String> objects) {
            super(context, resource, objects);
            layout = resource;
            this.context = context;


        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            BookViewHolder mainBookViewHolder;
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);

                BookViewHolder bookviewholder = new BookViewHolder();
                bookviewholder.Book_img = convertView.findViewById(R.id.book_image_iv);
                bookviewholder.Book_author = convertView.findViewById(R.id.book_author_tv);
                bookviewholder.Book_date = convertView.findViewById(R.id.book_date_tv);
                bookviewholder.Book_detail = convertView.findViewById(R.id.book_detail_tv);
                convertView.setTag(bookviewholder);
            } else {
                mainBookViewHolder = (BookViewHolder) convertView.getTag();
                mainBookViewHolder.Book_author.setText(getItem(position));

            }
            return convertView;
        }
    }

    public class BookViewHolder {
        ImageView Book_img;
        TextView Book_detail, Book_author, Book_date;
    }


}
