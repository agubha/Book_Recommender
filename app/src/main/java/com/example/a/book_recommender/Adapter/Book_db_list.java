package com.example.a.book_recommender.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.a.book_recommender.R;

import java.util.List;

public class Book_db_list extends ArrayAdapter<Book_db> {
    private Activity context;
    private List<Book_db> book_db_List;


    public Book_db_list(Activity context, List<Book_db> book_dbList) {
        super(context, R.layout.book_list_view_layout);
        this.context = context;
        this.book_db_List = book_dbList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listviewitem = inflater.inflate(R.layout.book_list_view_layout, null, true);

        TextView book_name_tv = listviewitem.findViewById(R.id.book_name_tv);
        TextView book_author_tv = listviewitem.findViewById(R.id.book_author_tv);
        TextView book_date_tv = listviewitem.findViewById(R.id.book_date_tv);
        TextView book_detail_tv = listviewitem.findViewById(R.id.book_detail_tv);


        Book_db book_db = book_db_List.get(position);

        book_name_tv.setText(book_db.getBook_Name());
        book_author_tv.setText(book_db.getBook_Author());
        book_date_tv.setText(book_db.getBook_date());
        book_detail_tv.setText(book_db.getBook_detail());
        return listviewitem;
    }

}
