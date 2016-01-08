package com.example.poutanenmikkomoviesdb;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MoviesArrayAdapter extends ArrayAdapter<Movie> {
	

	Activity context;
	ArrayList<Movie> moviesList;
	
	public MoviesArrayAdapter(Activity context, ArrayList<Movie> moviesList) {
		
		super(context, R.layout.row_layout, moviesList);
		this.context = context;
		this.moviesList = moviesList;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = (LayoutInflater) 
		context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.row_layout, parent, false);
		

		TextView nameView = (TextView) rowView.findViewById(R.id.row_name);
    	TextView yearView = (TextView) 
    	rowView.findViewById(R.id.row_year);
    	TextView genreView = (TextView) 
    	rowView.findViewById(R.id.row_genre);
    
    	nameView.setText(moviesList.get(position).getName());
    
    	yearView.setText(moviesList.get(position).getYear());
    
    	genreView.setText(moviesList.get(position).getGenre());
		
		
		
		return rowView;
		}
	
	


}
