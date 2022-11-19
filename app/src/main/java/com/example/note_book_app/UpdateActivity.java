package com.example.note_book_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    private EditText titleInput, authorInput, pagesInput;
    private Button updateButton;

    private  String id, title, author, pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        titleInput = findViewById(R.id.title_input2);
        authorInput = findViewById(R.id.author_input2);
        pagesInput = findViewById(R.id.page_input2);
        updateButton = findViewById(R.id.update_book);

        getAndSetIntentData();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper DB = new DatabaseHelper(UpdateActivity.this);
                DB.updateData(id,titleInput.getText().toString(),
                        authorInput.getText().toString(),pagesInput.getText().toString());
            }
        });
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("title") && getIntent().hasExtra("pages")) {

            //Getting intent data
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            pages = getIntent().getStringExtra("pages");

            //Setting intent data
            titleInput.setText(title);
            authorInput.setText(author);
            pagesInput.setText(pages);


        } else {
            Toast.makeText(UpdateActivity.this, "No Data to Update", Toast.LENGTH_SHORT).show();
        }
    }
}