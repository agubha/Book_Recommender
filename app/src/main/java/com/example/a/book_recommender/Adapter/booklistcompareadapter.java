package com.example.a.book_recommender.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.a.book_recommender.R;

import java.util.ArrayList;

public class booklistcompareadapter extends ArrayAdapter<Book2object> {
    public booklistcompareadapter(@NonNull Context context, ArrayList<Book2object> list) {
        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.book_list_view_compare_layout, null);

        TextView bookauthor, bookname, bookdate, bookdetail, cosine;
        cosine = view.findViewById(R.id.cosine_tv);
        bookauthor = view.findViewById(R.id.book_author_tv);
        bookdate = view.findViewById(R.id.book_date_tv);
        bookname = view.findViewById(R.id.book_name_tv);
        bookdetail = view.findViewById(R.id.book_detail_tv);
        Book2object book2object = getItem(position);
        assert book2object != null;
        String booknames = book2object.bookname;
        Log.d("inadapter", booknames + "");

        bookauthor.setText(book2object.bookauthor);
        bookdate.setText(book2object.bookdate);
        bookname.setText(book2object.bookname);
        String cosinevalue = String.valueOf(book2object.cosine);
        cosine.setText(cosinevalue);
        @SuppressLint({"NewApi", "LocalSuppress"}) String concatdetail = String.join(",", book2object.bookdetaill);

        bookdetail.setText(concatdetail);


        return view;


    }
}