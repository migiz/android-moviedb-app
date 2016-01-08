package com.example.poutanenmikkomoviesdb;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SavedMoviesActivity extends Activity {

	ArrayList<Movie> moviesList;
	ListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.saved_movies_activity);
		mListView = (ListView) findViewById(R.id.listview);
		moviesList = new ArrayList<Movie>();

	}

	@Override
	protected void onStart() {
		super.onStart();

		readMovieFromDataBase();
		showList();

		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				showDialogue(moviesList.get(position));
			}
		});

	}

	private void readMovieFromDataBase() {
		MovieSQLiteHelper msdbh;
		SQLiteDatabase db;
		msdbh = new MovieSQLiteHelper(SavedMoviesActivity.this, "DBMovie",
				null, 1);
		db = msdbh.getReadableDatabase();
		Cursor c = db.rawQuery("Select * from moviesdb", null);
		if (c.moveToFirst()) {
			// List all results
			do {
				String name = c.getString(0);
				String year = c.getString(1);
				String genre = c.getString(2);
				String runtime = c.getString(3);
				Movie movie = new Movie(name, year, genre, runtime);
				moviesList.add(movie);
				Log.d("Database Testing", name + year + genre + runtime);
			} while (c.moveToNext());
		}
		db.close();

	}

	private void showList() {
		ArrayAdapter<Movie> adapter = new MoviesArrayAdapter(this, moviesList);
		mListView.setAdapter(adapter);

	}

	protected void showDialogue(final Movie movie) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setPositiveButton(android.R.string.ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// User clicked OK button
					}
				});
		builder.setNegativeButton("Delete",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						deleteMovieFromDBandRefreshList(movie);
					}
				});
		builder.setMessage(movie.getName() + " " + movie.getYear());
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	protected void deleteMovieFromDBandRefreshList(Movie movie) {
		try {

			// User clicked Delete button
			MovieSQLiteHelper msdbh;
			SQLiteDatabase db;
			msdbh = new MovieSQLiteHelper(SavedMoviesActivity.this, "DBmovie",
					null, 1);

			db = msdbh.getWritableDatabase();
			// String[] args = { movie.getName() };
			// String deleteQuery =("DELETE FROM movies WHERE name ='"+ args
			// +"'");
			// db.execSQL(deleteQuery);
			Log.d("deleteButton", movie.getName());

			String[] selectionArgs = { movie.getName() };
			db.delete("moviesdb", "name =?", selectionArgs);

			// Log.d("deleteButton", String.valueOf(a));
			db.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		moviesList = new ArrayList<Movie>();
		readMovieFromDataBase();
		showList();
	}
}
