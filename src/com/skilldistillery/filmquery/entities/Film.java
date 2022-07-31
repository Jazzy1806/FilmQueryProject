package com.skilldistillery.filmquery.entities;

import java.util.*;

public class Film {
	private List<Actor> actors = new ArrayList<Actor>();
	private List<InventoryItem> availableCopies = new ArrayList<InventoryItem>();
	private int filmId;
	private String title;
	private String description;
	private int release_year;
	private int langId;
	private String language;
	private int duration;
	private double rentalRate;
	private int length;
	private double replaceCost;
	private String rating;
	private String features;
	private String category;

	
	public Film() {	}

	
	public Film(int filmId, String title, String description, int release_year, int langId, String language, int duration, double rentalRate,
			 int length, double replaceCost, String rating, String features, String category, List<Actor> actors, List<InventoryItem> availableCopies) {
	
		this.filmId = filmId;
		this.title = title;
		this.description = description;
		this.release_year = release_year;
		this.langId = langId;
		this.language = language;
		this.duration = duration;
		this.rentalRate = rentalRate;
		this.length = length;
		this.replaceCost = replaceCost;
		this.rating = rating;
		this.features = features;
		this.category = category;
		this.actors = actors;
		this.availableCopies = availableCopies;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getFilmId() {
		return filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRelease_year() {
		return release_year;
	}

	public void setRelease_year(int release_year) {
		this.release_year = release_year;
	}

	public int getLangId() {
		return langId;
	}

	public void setLangId(int langId) {
		this.langId = langId;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public double getReplaceCost() {
		return replaceCost;
	}

	public void setReplaceCost(double replaceCost) {
		this.replaceCost = replaceCost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	@Override
	public int hashCode() {
		return Objects.hash(actors, availableCopies, category, description, duration, features, filmId, langId,
				language, length, rating, release_year, rentalRate, replaceCost, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(actors, other.actors) && Objects.equals(availableCopies, other.availableCopies)
				&& Objects.equals(category, other.category) && Objects.equals(description, other.description)
				&& duration == other.duration && Objects.equals(features, other.features) && filmId == other.filmId
				&& langId == other.langId && Objects.equals(language, other.language) && length == other.length
				&& Objects.equals(rating, other.rating) && release_year == other.release_year
				&& Double.doubleToLongBits(rentalRate) == Double.doubleToLongBits(other.rentalRate)
				&& Double.doubleToLongBits(replaceCost) == Double.doubleToLongBits(other.replaceCost)
				&& Objects.equals(title, other.title);
	}
	
	public String printActors() {
		String actorList = "";
		for (int i = 0; i < actors.size(); i++) {
			if (i == actors.size() -1) {
				actorList += actors.get(i);
				break;
			}
			actorList += actors.get(i) + ", ";
		}
		return actorList;
	}
	
	public String printInventory() {
		String inventory = "";
		int counter = 1;
		for (InventoryItem copy : availableCopies) {
			inventory += counter + ". " + copy + "\n";
			counter++;
			}
		return inventory;
	}

	public String fullPrint() {
		return "Title: " + title + "\nRelease Year: " + release_year + "\nDescription: " + description + 
				"\nLanguage ID: " + langId + "\t\tLanguage: " + language +"\t\tDuration: " + duration + "\t\tLength: " + length + "\nRental Rate: $" + rentalRate
				+ "\t\tReplacement Cost: $" + replaceCost + "\nRating: " + rating + "\t\tCategory: " + category + "\t\tFilm ID: " + filmId + "\nFeatures: "
				+ features + "\nActors in film: " + printActors() + "\n\nList of available rentals and their condition:\n" + printInventory();
	}

	@Override
	public String toString() {
		return "Title: " + title + "\nRelease Year: " + release_year + "\tRating: " + rating + "\tLanguage: " + language + "\tFilm ID: " + filmId +
				"\nDescription: " + description + "\nActors in film: " + printActors();		
	}
}