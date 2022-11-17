package com.example.note_book_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    private EditText titleInput, authorInput, pagesInput;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        titleInput = findViewById(R.id.title_input);
        authorInput = findViewById(R.id.author_input);
        pagesInput = findViewById(R.id.page_input);
        addButton = findViewById(R.id.add_book);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper DB = new DatabaseHelper(AddActivity.this);
                  DB.addBook(titleInput.getText().toString().trim(),
                        authorInput.getText().toString().trim(),
                        Integer.valueOf(pagesInput.getText().toString().trim()));
            }
        });
    }
}