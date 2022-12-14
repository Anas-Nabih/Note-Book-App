package com.example.note_book_app;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "BookLibrary.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_library";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "book_title";
    private static final String COLUMN_AUTHOR = "book_author";
    private static final String COLUMN_PAGES = "book_pages";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_AUTHOR + " TEXT, " +
                COLUMN_PAGES + " INTEGER);";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addBook(String title, String author, int pagesNum) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_TITLE, title);
        contentValues.put(COLUMN_AUTHOR, author);
        contentValues.put(COLUMN_PAGES, pagesNum);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    public Cursor readAllData() {
        String getDataQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;

        if (db != null) {
            cursor = db.rawQuery(getDataQuery, null);
        }

        return cursor;
    }

    public void updateData(String rowId, String title, String author, String pages) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_TITLE, title);
        contentValues.put(COLUMN_AUTHOR, author);
        contentValues.put(COLUMN_PAGES, pages);
        System.out.print("from update button: " + title + author + pages);
        long result = db.update(TABLE_NAME, contentValues, "_id=?", new String[]{rowId});

        if (result == -1) {
            Toast.makeText(context, "Failed to update data!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Updated Successfully.", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteRow(String rowId){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME,"_id=?",new String[]{rowId});

        if(result == -1){
            Toast.makeText(context,"Failed To Delete!",Toast.LENGTH_SHORT).show();;
        }else{
            Toast.makeText(context,"Successfully Deleted.",Toast.LENGTH_SHORT).show();;
        }
    }

    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
