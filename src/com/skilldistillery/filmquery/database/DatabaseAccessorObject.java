package com.skilldistillery.filmquery.database;

import java.sql.*;
import java.util.*;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;
import com.skilldistillery.filmquery.entities.InventoryItem;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static String user = "student";
	private static String pass = "student";
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println(e);
		}
	}

	public DatabaseAccessorObject() {
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT * FROM film f JOIN film_category fc ON f.id = fc.film_id JOIN category c ON fc.category_id = c.id JOIN language l ON f.language_id = l.id WHERE f.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);

			ResultSet filmResult = stmt.executeQuery();

			if (filmResult.next()) {
				film = new Film(filmResult.getInt("f.id"), filmResult.getString("title"),
						filmResult.getString("description"), filmResult.getInt("release_year"),
						filmResult.getInt("language_id"), filmResult.getString("l.name"),
						filmResult.getInt("rental_duration"), filmResult.getDouble("rental_rate"),
						filmResult.getInt("length"), filmResult.getDouble("replacement_cost"),
						filmResult.getString("rating"), filmResult.getString("special_features"),
						filmResult.getString("c.name"), findActorsByFilmId(filmId), findInventoryByFilmId(filmId));
			}

			filmResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		String sql = "SELECT * FROM actor WHERE id = ?";

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			
			ResultSet actorResult = stmt.executeQuery();

			if (actorResult.next()) {
				actor = new Actor(actorResult.getInt("id"), actorResult.getString("first_name"), actorResult.getString("last_name"));
			}
			actorResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<Actor>();
		String sql = "SELECT * FROM actor JOIN film_actor ON actor.id = film_actor.actor_id WHERE film_id = ?";

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			
			ResultSet filmActors = stmt.executeQuery();

			while (filmActors.next()) {
				actors.add(new Actor(filmActors.getInt("id"), filmActors.getString("first_name"), filmActors.getString("last_name")));
			}
			filmActors.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;
	}

	@Override
	public List<Film> findFilmByKeyword(String keyword) {
		keyword = "%" + keyword + "%";
		List<Film> films = new ArrayList<Film>();

		String sql = "SELECT * FROM film f JOIN film_category fc ON f.id = fc.film_id JOIN category c ON fc.category_id = c.id JOIN language l ON f.language_id = l.id WHERE title LIKE ? OR description LIKE ?";

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, keyword);
			stmt.setString(2, keyword);
			
			ResultSet filmRS = stmt.executeQuery();

			while (filmRS.next()) {
				films.add(new Film(filmRS.getInt("f.id"), filmRS.getString("title"), filmRS.getString("description"),
						filmRS.getInt("release_year"), filmRS.getInt("language_id"), filmRS.getString("l.name"),
						filmRS.getInt("rental_duration"), filmRS.getDouble("rental_rate"), filmRS.getInt("length"),
						filmRS.getDouble("replacement_cost"), filmRS.getString("rating"),
						filmRS.getString("special_features"), filmRS.getString("c.name"),
						findActorsByFilmId(filmRS.getInt("f.id")), findInventoryByFilmId(filmRS.getInt("f.id"))));
			}
			filmRS.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	@Override
	public List<InventoryItem> findInventoryByFilmId(int filmId) {
		List<InventoryItem> copies = new ArrayList<InventoryItem>();
		String sql = "SELECT * FROM film f JOIN inventory_item i ON f.id = i.film_id JOIN store s ON i.store_id = s.id JOIN address a ON s.address_id = a.id WHERE f.id = ?";

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			
			ResultSet filmCopies = stmt.executeQuery();

			while (filmCopies.next()) {
				copies.add(new InventoryItem(filmCopies.getString("f.title"), filmCopies.getInt("i.id"),
						filmCopies.getString("media_condition"), filmCopies.getString("address") + ", "
								+ filmCopies.getString("city") + ", " + filmCopies.getString("state_province")));
			}
			filmCopies.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return copies;
	}
}