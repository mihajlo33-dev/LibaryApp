package com.example.libaryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EditBookActivity extends AppCompatActivity {

    private TextInputEditText bookNameEdt,bookAuthNameEdt,bookPriceEdt,bookImgEdt,bookDescEdt,book;
    private Button updateBookBtn,deleteBookBtn;
    private ProgressBar loadingPB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String bookID;
    private BookRVModal bookRVModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        firebaseDatabase = FirebaseDatabase.getInstance();
        bookNameEdt = findViewById(R.id.idEdtBookName);
        bookAuthNameEdt = findViewById(R.id.idEdtAuthorName);
        bookPriceEdt = findViewById(R.id.idEdtBookPrice);
        bookImgEdt = findViewById(R.id.idEdtBookImageLink);
        bookDescEdt = findViewById(R.id.idEdtBookDesc);
        updateBookBtn = findViewById(R.id.idBtnUpdateBook);
        deleteBookBtn = findViewById(R.id.idBtnDeleteBook);
        loadingPB = findViewById(R.id.idPBLoading);
        bookRVModal = getIntent().getParcelableExtra("book");
        if (bookRVModal != null) {
            bookNameEdt.setText(bookRVModal.getBookName());
            bookAuthNameEdt.setText(bookRVModal.getBookAuthName());
            bookPriceEdt.setText(bookRVModal.getBookPrice());
            bookImgEdt.setText(bookRVModal.getBookImg());
            bookDescEdt.setText(bookRVModal.getBookDescription());
            bookAuthNameEdt.setText(bookRVModal.getBookAuthName());
            bookID = bookRVModal.getBookID();

        }


        databaseReference = firebaseDatabase.getReference("Books").child(bookID);
        updateBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingPB.setVisibility(View.VISIBLE);
                loadingPB.setVisibility(View.VISIBLE);
                String bookName = bookNameEdt.getText().toString();
                String authorName = bookAuthNameEdt.getText().toString();
                String bookPrice = bookPriceEdt.getText().toString();
                String bookImg = bookImgEdt.getText().toString();
                String bookDesc = bookDescEdt.getText().toString();
                bookID = bookName;

                Map<String, Object> map = new HashMap<>();
                map.put("bookName", bookName);
                map.put("bookAuthName", authorName);
                map.put("bookPrice", bookPrice);
                map.put("bookImg", bookImg);
                map.put("bookDescription", bookDesc);
                map.put("bookID", bookID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        loadingPB.setVisibility(View.GONE);
                        databaseReference.updateChildren(map);
                        Toast.makeText(EditBookActivity.this, "Book Updated!", Toast.LENGTH_SHORT);
                        startActivity(new Intent(EditBookActivity.this, MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(EditBookActivity.this, "Fail to update book!", Toast.LENGTH_SHORT);

                    }
                });

            }
        });

        deleteBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteBook();
            }

        });
    }

        private void deleteBook(){
            databaseReference.removeValue();
            Toast.makeText(this, "", Toast.LENGTH_SHORT);
            startActivity(new Intent(EditBookActivity.this,MainActivity.class));
        }

}