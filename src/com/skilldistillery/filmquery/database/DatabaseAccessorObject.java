package com.skilldistillery.filmquery.database;

import java.sql.*;
import java.util.*;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println(e);
		}
		// connection to sql goodies all goes in this class

	}
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	Actor actor = null;
	String user = "student";
	String pass = "student";
	String sql;

	public DatabaseAccessorObject() {
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		String user = "student";
		String pass = "student";

		try (Connection conn = DriverManager.getConnection(URL, user, pass);
			 PreparedStatement stmt = conn.prepareStatement(sql);
		     ResultSet filmResult = stmt.executeQuery();) {

			String sql = "SELECT * FROM actor JOIN film_actor ON actor_id = film_actor.actor_id WHERE film.id = ?";

			stmt.setInt(1, filmId);

			if (filmResult.next()) {
				film = new Film(filmResult.getInt("id"), filmResult.getString("title"),
						filmResult.getString("description"), filmResult.getInt("release_year"),
						filmResult.getInt("language_id"), filmResult.getInt("rental_duration"),
						filmResult.getDouble("rental_rate"), filmResult.getInt("length"),
						filmResult.getDouble("replacement_cost"), filmResult.getString("rating"),
						filmResult.getString("special_features"));
			}

		} catch (SQLException e) {
			System.err.println(e);
		}
		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		String user = "student";
		String pass = "student";

		try (Connection conn = DriverManager.getConnection(URL, user, pass);
			 PreparedStatement stmt = conn.prepareStatement(sql);
		     ResultSet actorResult = stmt.executeQuery();) {

			String sql = "SELECT * FROM actor WHERE id = ?";
			stmt.setInt(1, actorId);

			if (actorResult.next()) {
				actor = new Actor(actorResult.getInt("id"), actorResult.getString("first_name"), actorResult.getString("last_name"));
			}

		} catch (SQLException e) {
			System.err.println(e);
		}
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<Actor>();
		String user = "student";
		String pass = "student";

		try (Connection conn = DriverManager.getConnection(URL, user, pass);
			 PreparedStatement stmt = conn.prepareStatement(sql);
		     ResultSet filmActors = stmt.executeQuery();) {

			String sql = "SELECT * FROM film WHERE id = ?";
			stmt.setInt(1, filmId);

			while (filmActors.next()) {
				actors.add(new Actor(filmActors.getInt("id"), filmActors.getString("first_name"),
						filmActors.getString("last_name")));
			}

		} catch (SQLException e) {
			System.err.println(e);
		}
		return actors;
	}

}
