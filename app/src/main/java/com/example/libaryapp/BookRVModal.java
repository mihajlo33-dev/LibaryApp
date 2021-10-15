package com.example.libaryapp;

import android.os.Parcel;
import android.os.Parcelable;

public class BookRVModal implements Parcelable {
    private String bookName;
    private String bookAuthName;
    private String bookDescription;
    private String bookPrice;
    private String bookImg;
    private String bookID;
    private String bookLink;

    public BookRVModal(){

    }

    public BookRVModal(String bookName, String bookAuthName, String bookDescription, String bookPrice, String bookImg, String bookID,String bookLink) {
        this.bookName = bookName;
        this.bookAuthName = bookAuthName;
        this.bookDescription = bookDescription;
        this.bookPrice = bookPrice;
        this.bookImg = bookImg;
        this.bookID = bookID;
        this.bookLink = bookLink;
    }

    protected BookRVModal(Parcel in) {
        bookName = in.readString();
        bookAuthName = in.readString();
        bookDescription = in.readString();
        bookPrice = in.readString();
        bookImg = in.readString();
        bookID = in.readString();
    }

    public static final Creator<BookRVModal> CREATOR = new Creator<BookRVModal>() {
        @Override
        public BookRVModal createFromParcel(Parcel in) {
            return new BookRVModal(in);
        }

        @Override
        public BookRVModal[] newArray(int size) {
            return new BookRVModal[size];
        }
    };

    public String getBookLink() {
        return bookLink;
    }

    public void setBookLink(String bookLink) {
        this.bookLink = bookLink;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthName() {
        return bookAuthName;
    }

    public void setBookAuthName(String bookAuthName) {
        this.bookAuthName = bookAuthName;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookImg() {
        return bookImg;
    }

    public void setBookImg(String bookImg) {
        this.bookImg = bookImg;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bookName);
        dest.writeString(bookAuthName);
        dest.writeString(bookDescription);
        dest.writeString(bookPrice);
        dest.writeString(bookImg);
        dest.writeString(bookID);
    }
}
