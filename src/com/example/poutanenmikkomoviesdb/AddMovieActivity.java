package com.example.poutanenmikkomoviesdb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddMovieActivity extends Activity {
	
	EditText et_movieName, et_movieYear, et_movieGenre, et_movieRuntime;
	String movieName, movieYear, movieGenre;
	Button addButton;
	
	@Override
	protected void onStart() {
		super.onStart();
		addButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getAndSendText();
			}
		});
		}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_movie);
		et_movieName = (EditText)findViewById(R.id.ET_movieName);
		et_movieYear = (EditText)findViewById(R.id.ET_movieYear);
		et_movieGenre = (EditText)findViewById(R.id.ET_movieGenre);
		et_movieRuntime = (EditText)findViewById(R.id.ET_movieRuntime);
		addButton = (Button)findViewById(R.id.add_button);
	
	}
	
	
	protected void getAndSendText() {
	// Validate the data and look for empty fields
		movieName = et_movieName.getText().toString();
		movieYear = et_movieYear.getText().toString();
		movieGenre = et_movieGenre.getText().toString();
		
			Intent in = new Intent();
			in.setClass(AddMovieActivity.this, MovieDetailsView.class);
			in.putExtra("mName", movieName);
			in.putExtra("mYear", movieYear);
			in.putExtra("mGenre", movieGenre);
			if((movieName != null && movieName.length() != 0) && (movieYear != null && movieYear.length() != 0) && (movieGenre != null && movieGenre.length() != 0) ) {
				startActivity(in);
				finish();
			}else{
				Toast.makeText(this, "Please enter a value to every field!", Toast.LENGTH_LONG).show();
			}
	}

}
