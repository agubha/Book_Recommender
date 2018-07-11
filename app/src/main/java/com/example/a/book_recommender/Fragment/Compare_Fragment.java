package com.example.a.book_recommender.Fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a.book_recommender.R;

public class Compare_Fragment extends DialogFragment {


    TextView author, name, date, isbn, bookdetail, cosine;
    ImageView bookimg;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.compare_fragment_layout, container, false);
        author = rootView.findViewById(R.id.cauthor_tv);

        //init
        name = rootView.findViewById(R.id.cname_tv);
        date = rootView.findViewById(R.id.cdate_tv);
        isbn = rootView.findViewById(R.id.cisbn_tv);
        bookdetail = rootView.findViewById(R.id.cdetail_tv);
        cosine = rootView.findViewById(R.id.ccosine_tv);
        bookimg = rootView.findViewById(R.id.cbookimg_iv;
        //receive


        //bind
//        name.setText();
        return rootView;
    }
}
