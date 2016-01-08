package com.example.poutanenmikkomoviesdb;


public class Movie {
	public String name, year, genre, runtime;

	public Movie(String name, String year, String genre, String runtime) {
		super();
		this.name = name;
		this.year = year;
		this.genre = genre;
		this.runtime = runtime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}
	

}
