package com.example.a.book_recommender.Adapter;

public class Book_db {
    private String Book_detail;
    private String Book_date;
    private String Book_Author;
    private String Book_Name;

    public Book_db() {
        //required
    }


    public Book_db(String book_Name, String book_Author, String book_detail, String book_date) {
        this.Book_Name = book_Name;
        this.Book_Author = book_Author;
        this.Book_date = book_date;
        this.Book_detail = book_detail;
    }

    public String getBook_detail() {
        return Book_detail;
    }

    public String getBook_date() {
        return Book_date;
    }

    public String getBook_Author() {
        return Book_Author;
    }

    public String getBook_Name() {return Book_Name;}

    public void setBook_detail(String book_detail) {
        Book_detail = book_detail;
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
}
