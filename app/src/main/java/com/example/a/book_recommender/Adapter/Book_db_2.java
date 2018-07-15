package com.example.a.book_recommender.Adapter;

import java.util.List;

public class Book_db_2 {
    private List<String> Book_token;
    private String Book_date;
    private String Book_Author;
    private String Book_Name;
    private String Book_isbn;
    private Integer Cosine;
    private String Book_detail;

    public Book_db_2() {
        //required
    }


    public Book_db_2(String book_Name, String book_Author, List<String> book_token, String book_date, String book_isbn, Integer cosine, String book_detail) {
        this.Book_Name = book_Name;
        this.Book_Author = book_Author;
        this.Book_date = book_date;
        this.Book_token = book_token;
        this.Book_isbn = book_isbn;
        this.Cosine = cosine;
        this.Book_detail = book_detail;
    }

    public void setBook_token(List<String> book_token) {
        Book_token = book_token;
    }

    public void setBook_date(String book_date) {
        Book_date = book_date;
    }

    public void setBook_Author(String book_Author) {
        Book_Author = book_Author;
    }

    public void setBook_Name(String book_Name) {
        Book_Name = book_Name;
    }

    public void setBook_isbn(String book_isbn) {
        Book_isbn = book_isbn;
    }

    public void setCosine(Integer cosine) {
        Cosine = cosine;
    }

    public void setBook_detail(String book_detail) {
        Book_detail = book_detail;
    }

    public String getBook_detail() {

        return Book_detail;
    }

    public List<String> getBook_token() {
        return Book_token;
    }

    public String getBook_date() {
        return Book_date;
    }

    public String getBook_Author() {
        return Book_Author;
    }

    public String getBook_Name() {
        return Book_Name;
    }

    public String getBook_isbn() {
        return Book_isbn;
    }

    public Integer getCosine() {
        return Cosine;
    }
}

