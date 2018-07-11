package com.example.a.book_recommender;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a.book_recommender.Adapter.Book_db_2;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdminAdd extends AppCompatActivity implements Runnable {

    Button accept_changes_btn, cancel_changes_btn;
    EditText Book_name, Book_isbn, Book_author, Book_p_date, Book_detail;
    String Book_name_str, Book_author_str, Book_detail_str, Book_date_str;
    String Book_isbn_str, name = "Book Name", author = "Book Author", date = "Date of Publication";
    private DatabaseReference f2database;
    private StorageReference mStorageRef;
    //StorageReference storageRef = storage.getReference();*/

    Async_Task async = new Async_Task();
    ArrayList<String> final_output = new ArrayList<>();
    List<String> Book_detail_list = new ArrayList<>();


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
            String[] splitted_string = Step1split(Book_detail_str);
            async.execute(splitted_string);


        } else {
            Toast.makeText(this, "Text Field Empty", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void run() {

    }

    @SuppressLint("StaticFieldLeak")
    public class Async_Task extends AsyncTask<String[], Void, ArrayList<String>> {


        @Override
        protected ArrayList<String> doInBackground(String[]... strings) {

            String[] wordtostop = new String[500];
            String[] wordtostem = strings[0];
            ArrayList<String> valuetoreturn;

            /*step 1 stemming*/
            for (int i = 0; i < wordtostem.length; i++) {
                PorterStemmer2 porterStemmer2 = new PorterStemmer2();
                wordtostop[i] = porterStemmer2.stemWord(wordtostem[i]);
            }
            /*Step 2 async stopword*/
            //for each
            ArrayList<String> stemmed_list_before_stopword = new ArrayList<>(Arrays.asList(wordtostop));
            StoppedWord3 stoppedword3 = new StoppedWord3();
            valuetoreturn = stoppedword3.main(stemmed_list_before_stopword);
            return valuetoreturn;

        }

        @Override
        protected void onPostExecute(ArrayList<String> strings) {

            final_output = strings;
            final_output.removeAll(Arrays.asList(null, ""));
            //creating child in root
            f2database = FirebaseDatabase.getInstance().getReference("Books_db_2");

            Book_db_2 book_db_2 = new Book_db_2(Book_name_str, Book_author_str, final_output, Book_date_str, Book_isbn_str,0);
            f2database.child(Book_isbn_str).setValue(book_db_2).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(AdminAdd.this, "Push Successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AdminAdd.this, "Push Unsuccessful", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }


    public String[] Step1split(String step_1_split) {
        return step_1_split.split("\\s+");
    }

}
