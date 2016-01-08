package com.example.poutanenmikkomoviesdb;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MovieDetailsView extends Activity {
	
	TextView mNameTV,mYearTV,mGenreTV,mRuntimeTV;
	Button clearButton;
	Button saveButton;
	String mName, mYear, mGenre, mRuntime;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie_details);
		clearButton = (Button) findViewById(R.id.clear_button);
		saveButton = (Button) findViewById(R.id.save_movie_button);
		mNameTV = (TextView) findViewById(R.id.mNameTV);
		mYearTV = (TextView) findViewById(R.id.mYearTV);
		mGenreTV =(TextView) findViewById(R.id.mGenreTV);
		mRuntimeTV = (TextView) findViewById(R.id.mRuntimeTV);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		clearButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				 Intent intent = new Intent(MovieDetailsView.this, MainActivity.class);
				    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);   
				    startActivity(intent);
				
				finish();
			}
		});
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		if (extras != null) {
				mName = extras.getString("mName");
				mYear = extras.getString("mYear");
				mGenre = extras.getString("mGenre");
				mRuntime = extras.getString("mRuntime");
				mNameTV.setText(mName);
				mYearTV.setText(mYear);
				mGenreTV.setText(mGenre);
				mRuntimeTV.setText(mRuntime);
		} else {
			mNameTV.setText("Extras was null");
			mYearTV.setText("Extras was null");
			mGenreTV.setText("Extras was null");
			mRuntimeTV.setText("Extras was null");
	}
		

		saveButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(isDataValidated()){
					saveMovieToDataBase();
					Toast.makeText(MovieDetailsView.this, mName + " was saved!",
							Toast.LENGTH_SHORT).show();
					Intent in = new Intent();
		    		in.setClass(MovieDetailsView.this, MainActivity.class);
		    		startActivity(in);
		    		finish();
				} else {
					// Do something
				}
			}
		});
	}
		
		
		protected boolean isDataValidated() {
			
			
			// if all three fields are not empty then return true
			
			if((mNameTV != null && mNameTV.length() != 0) && (mYearTV != null && mYearTV.length() != 0) && (mGenreTV != null && mGenreTV.length() != 0) && (mRuntimeTV != null && mRuntimeTV.length() != 0)) {
				return true;
				
			} else {			
			
			// else return false
			return false;
			}

		}

		protected void saveMovieToDataBase() {
			MovieSQLiteHelper msdbh;
			SQLiteDatabase db;
			
			msdbh = new MovieSQLiteHelper(MovieDetailsView.this, 
					"DBMovie", null, 1);
			// We open the database in writer mode
			db = msdbh.getWritableDatabase();

			// Create the record using ContentValues
			ContentValues newRecord = new ContentValues();
			newRecord.put("name", mName);
			newRecord.put("year", mYear);
			newRecord.put("genre", mGenre);
			newRecord.put("runtime", mRuntime);
			// Insert the record in the database
			db.insert("moviesdb", null, newRecord);
			db.close();
		}
		

		private void readMovieFromDataBase() {
			MovieSQLiteHelper msdbh;
			SQLiteDatabase db;
			msdbh = new MovieSQLiteHelper(MovieDetailsView.this, 
					"DBMovie", null, 1);
			db = msdbh.getReadableDatabase();
			Cursor c = db.rawQuery("select * from movies", null);
			if (c.moveToFirst()) {
				// List all results
				do {
					String name = c.getString(0);
					String year = c.getString(1);
					String genre = c.getString(2);
					String runtime = c.getString(3);
					Log.d("Database Testing", name + year + 
							genre + runtime);
				} while (c.moveToNext());
			}
			db.close();

		}
	

	}

