package com.skilldistillery.filmquery.database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.filmquery.entities.Film;
import com.skilldistillery.filmquery.entities.InventoryItem;
import com.skilldistillery.filmquery.entities.Actor;

class DatabaseAccessTests {
  private DatabaseAccessor db;

  @BeforeEach
  void setUp() throws Exception {
    db = new DatabaseAccessorObject();
  }

  @AfterEach
  void tearDown() throws Exception {
    db = null;
  }
  
  @Test
  void test_getFilmById_returns_film_with_id() {
    Film f = db.findFilmById(1);
    assertNotNull(f);
    assertEquals("ACADEMY DINOSAUR", f.getTitle());
  }

  @Test
  void test_getFilmById_with_invalid_id_returns_null() {
    Film f = db.findFilmById(-42);
    assertNull(f);
  }
  
  @Test
  void test_findActorById_returns_actor_with_id() {
    Actor actor = db.findActorById(1);
    assertNotNull(actor);
    assertEquals("Penelope Guiness", actor.getFirstName() + " " + actor.getLastName());
  }

  @Test
  void test_findActorById_with_invalid_id_returns_null() {
    Actor actor = db.findActorById(-42);
    assertNull(actor);
  }
  
  @Test
  void test_findActorsByFilmId_returns_actors_with_film_id() {
    List<Actor> actors = db.findActorsByFilmId(1);
    assertNotNull(actors);
    assertEquals("[Penelope Guiness, Christian Gable, Lucille Tracy, Sandra Peck, Johnny Cage, Mena Temple, Warren Nolte, Oprah Kilmer, Rock Dukakis, Mary Keitel]", actors.toString());
  }

  @Test
  void test_findActorsByFilmId_with_invalid_id_returns_empty_list() {
    List<Actor> actors = db.findActorsByFilmId(-42);
    assertEquals("[]", actors.toString());
  }
  
  @Test
  void test_findFilmByKeyword_returns__list_of_films_with_keyword() {
    List<Film> films = db.findFilmByKeyword("towers");
    assertNotNull(films);
    assertEquals("[Title: TOWERS HURRICANE\n"
    		+ "Release Year: 2015	Rating: NC17	Language: English	Film ID: 899\n"
    		+ "Description: A Fateful Display of a Monkey And a Car who must Sink a Husband in A MySQL Convention\n"
    		+ "Actors in film: Cate McQueen, Adam Hopper, Russell Temple, Cuba Birch]", films.toString());
  }

  @Test
  void test_findFilmByKeyword_with_invalid_id_returns_empty_list() {
	List<Film> films = db.findFilmByKeyword(null);
    assertEquals("[]", films.toString());
  }
  
  @Test
  void test_findInventoryByFilmId_returns_inventory_with_filmId() {
    List<InventoryItem> items = db.findInventoryByFilmId(3);
    assertNotNull(items);
    assertEquals("[ADAPTATION HOLES	Item #: 12	Used	Store Location:   242 North Milwaukee St, Denver, Colorado, "
    			+ "ADAPTATION HOLES	Item #: 13	Used	Store Location:   242 North Milwaukee St, Denver, Colorado, "
    			+ "ADAPTATION HOLES	Item #: 14	Used	Store Location:   242 North Milwaukee St, Denver, Colorado, "
    			+ "ADAPTATION HOLES	Item #: 15	Used	Store Location:   242 North Milwaukee St, Denver, Colorado, "
    			+ "ADAPTATION HOLES	Item #: 4615	Used	Store Location:   123 Sunset St, Reno, Nevada, "
    			+ "ADAPTATION HOLES	Item #: 4616	Used	Store Location:   123 Sunset St, Reno, Nevada, "
    			+ "ADAPTATION HOLES	Item #: 4617	Used	Store Location:   123 Sunset St, Reno, Nevada, "
    			+ "ADAPTATION HOLES	Item #: 4618	Used	Store Location:   123 Sunset St, Reno, Nevada, "
    			+ "ADAPTATION HOLES	Item #: 4619	Used	Store Location:   123 Sunset St, Reno, Nevada, "
    			+ "ADAPTATION HOLES	Item #: 4620	Lost	Store Location:   123 Sunset St, Reno, Nevada, "
    			+ "ADAPTATION HOLES	Item #: 4621	Used	Store Location:   27340 El Camino Real, Los Angeles, California, "
    			+ "ADAPTATION HOLES	Item #: 4622	Used	Store Location:   873 Brigham Young Blvd, Salt Lake City, Utah]", items.toString());
  }

  @Test
  void test_findInventoryByFilmId_with_invalid_id_returns_empty_list() {
	List<InventoryItem> items = db.findInventoryByFilmId(-42);
    assertEquals("[]", items.toString());
  }
}