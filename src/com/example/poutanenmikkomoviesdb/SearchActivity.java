package com.example.poutanenmikkomoviesdb;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends Activity implements AsyncResponse {

	EditText et_Search;
	Button btn_Search;
	Button btn_Details;
	TextView tv_Result;
	Boolean isMovieFound;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		et_Search = (EditText) findViewById(R.id.et_movie_to_search);
		btn_Search = (Button) findViewById(R.id.search_movie_button);
		tv_Result = (TextView) findViewById(R.id.tv_search_result);
		btn_Details = (Button) findViewById(R.id.view_movie_details_button);
	}

	@Override
	protected void onStart() {
		super.onStart();
		btn_Search.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (et_Search.getText().toString().length() > 0) {
					searchTitle(et_Search.getText().toString());
				} else {
					Toast.makeText(SearchActivity.this, "Search Box empty",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		
		

	}

	protected void searchTitle(String title) {
		title = title.replace(" ", "%20");
		String url = "http://www.omdbapi.com/?t=" + title;
		SearchTask searchTask = new SearchTask();
		searchTask.delegate = this;
		searchTask.execute(url);
		Toast.makeText(getBaseContext(), R.string.searching, Toast.LENGTH_LONG)
				.show();

	}

	@Override
	public void processFinish(String searchResults) {

		Movie movie = createMovieObjectFromJSON(searchResults);
		displayMovieObject(movie);
	}

	private void displayMovieObject(final Movie movie) {
		
		String result; 

		if (movie.getName() != getResources().getString(R.string.name_not_found)) {
			result = "Found " + movie.getName() + "(" + movie.getYear() + ")";
			tv_Result.setText(result);
			btn_Details.setVisibility(View.VISIBLE);
			tv_Result.setVisibility(View.VISIBLE);
			btn_Details.setOnClickListener(new View.OnClickListener() {	
				@Override
				public void onClick(View v) {
					
					String name = movie.getName();
					String year = movie.getYear();
					String genre = movie.getGenre();
					String runtime = movie.getRuntime();
					
					Intent in = new Intent();
					in.setClass(SearchActivity.this, MovieDetailsView.class);
					in.putExtra("mName", name);
					in.putExtra("mYear", year);
					in.putExtra("mGenre", genre);
					in.putExtra("mRuntime", runtime);
					
					startActivity(in);
					finish();
					
				}
			});
				
		} else {
			btn_Details.setVisibility(View.GONE);
			tv_Result.setText("Movie not found. Please try again!");
			tv_Result.setVisibility(View.VISIBLE);
			
			Toast.makeText(this, "No movies found!", Toast.LENGTH_LONG).show();	
		}
	}

	private Movie createMovieObjectFromJSON(String searchResults) {
		String name, year, genre, runtime;
		Movie movie = null;
		try {
			JSONObject jObject = new JSONObject(searchResults);
	
			
			
						
			if (jObject.has("Title")) {
				name = jObject.get("Title").toString();
			} else {
				name = getResources().getString(R.string.name_not_found);
			}
			if (jObject.has("Year")) {
				year = jObject.get("Year").toString();
			} else {
				year = getResources().getString(R.string.year_not_found);
				;
			}
			if (jObject.has("Genre")) {
				genre = jObject.get("Genre").toString();
			} else {
				genre = getResources().getString(R.string.genre_not_found);
				;
			}
			if (jObject.has("Runtime")) {
				runtime = jObject.get("Runtime").toString();
			} else {
				runtime = getResources().getString(R.string.runtime_not_found);
				;
				
			}
			
			movie = new Movie(name, year, genre, runtime);
			
		} catch (JSONException e) {

			e.printStackTrace();
		}
		return movie;

	}
}
