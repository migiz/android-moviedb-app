package com.example.poutanenmikkomoviesdb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	Button addButton;
	Button searchButton;
	Button savedButton;
	Button exitButton;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Activity Life Cycle", "onCreate Method called");
        setContentView(R.layout.activity_main);
        addButton = (Button) findViewById(R.id.add_movie_button);
        searchButton = (Button) findViewById(R.id.search_movies_button);
        savedButton = (Button) findViewById(R.id.saved_movies_button);
        exitButton = (Button) findViewById(R.id.exit_button);
    }
    
    @Override
    protected void onStart() {
    super.onStart();
    Log.d("Activity Life Cycle", "onStart Method called");
    addButton.setOnClickListener(new View.OnClickListener() {
    	@Override
    	public void onClick(View v) {
    		// This is where we define what happens when button is clicked.
    		Intent in = new Intent();
    		in.setClass(MainActivity.this, AddMovieActivity.class);
    		startActivity(in);
    		finish();
    		//showButtonClickedToastMessage(getResources().getString(R.string.add_movie_button));
    		}
    	}
    );  
   
    searchButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        // This is where we define what happens when button is clicked.
        	//showButtonClickedToastMessage(getResources().getString(R.string.search_movies_button));
        	Intent in = new Intent();
    		in.setClass(MainActivity.this, SearchActivity.class);
    		startActivity(in);
    		finish();
        	}
        	}
     );
    
    savedButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        	Intent in = new Intent();
    		in.setClass(MainActivity.this, SavedMoviesActivity.class);
    		startActivity(in);
    		finish();

        		}
        	}
        );
    exitButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        	android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);

        		}
        	}
        );
   
    }
    
    public void showButtonClickedToastMessage(String buttonName) {
    	Toast.makeText(this, buttonName + " Clicked", Toast.LENGTH_SHORT).show();
    	
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	Log.d("Activity Life Cycle", "onPause Method called");
	} 
    @Override
    protected void onResume() {
    	super.onResume();
    	Log.d("Activity Life Cycle", "onResume Method called");
	}
}


