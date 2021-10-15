package com.example.libaryapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BookRVAdapter extends RecyclerView.Adapter<BookRVAdapter.ViewHolder> {
    int lastPos = -1;
    private ArrayList<BookRVModal> bookRVModalArrayList;
    private Context context;
    private BookClickInterface bookClickInterface;

    public BookRVAdapter(ArrayList<BookRVModal> bookRVModalArrayList, Context context, BookClickInterface bookClickInterface) {
        this.bookRVModalArrayList = bookRVModalArrayList;
        this.context = context;
        this.bookClickInterface = bookClickInterface;
    }

    @NonNull
    @Override
    public BookRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_rv_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        BookRVModal bookRVModal = bookRVModalArrayList.get(position);
        holder.bookNameTV.setText(bookRVModal.getBookName());
        holder.bookPriceTV.setText("$ " + bookRVModal.getBookPrice());
        Picasso.get().load(bookRVModal.getBookImg()).into(holder.bookIV);
        setAnimation(holder.itemView,position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookClickInterface.onBookClick(position);
            }
        });

    }

    private void setAnimation(View itemView, int position){
        if(position >  lastPos){
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPos = position;
        }
    }

    @Override
    public int getItemCount() {
        return bookRVModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView bookNameTV,bookPriceTV;
        private ImageView bookIV;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            bookNameTV = itemView.findViewById(R.id.idTVBookName);
            bookPriceTV = itemView.findViewById(R.id.idTVPrice);
            bookIV = itemView.findViewById(R.id.idIVBook);
        }
    }

    public interface BookClickInterface{
        void onBookClick(int position);
    }
}
