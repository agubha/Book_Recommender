package com.example.a.book_recommender.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a.book_recommender.R;

import java.util.ArrayList;

public class booklistadapter extends ArrayAdapter<Bookobject> {
    public booklistadapter(@NonNull Context context, ArrayList<Bookobject> list) {
        super(context, 0,list);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.book_list_view_layout, null);

        TextView bookauthor,bookname,bookdetail,bookdate;


        bookauthor = view.findViewById(R.id.book_author_tv);
        bookdate = view.findViewById(R.id.book_date_tv);
        bookname = view.findViewById(R.id.book_name_tv);
        bookdetail = view.findViewById(R.id.book_detail_tv);

        Bookobject books =getItem(position);
        String booknamess = books.bookname;

        bookauthor.setText(books.bookauthor);
        Log.d("inadapter",booknamess+"");
        bookdate.setText(books.bookdetail);
        bookdetail.setText(books.bookdetail);
        bookname.setText(books.bookname);



        return view;


    }
}
