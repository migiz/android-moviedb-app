package com.example.poutanenmikkomoviesdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


public class MovieSQLiteHelper extends SQLiteOpenHelper {
	String createSQL = "CREATE TABLE moviesdb (name TEXT, year TEXT, genre TEXT, runtime TEXT)";
	
	public MovieSQLiteHelper(Context context, String name,
		CursorFactory factory, int version) {
	super(context, name, factory, version);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// Execute the SQL sentence for creating the table
		db.execSQL(createSQL);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int
			newVersion) {
		db.execSQL("DROP TABLE IF EXISTS moviesdb");
		db.execSQL(createSQL);
	}
	
	
}

