package com.projet3.service;
/** @author Hasnae SAKHI */
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.projet3.model.FilmEntity;

public interface FilmService  {

	// get methods
		public ResponseEntity<List<FilmEntity>>    getAllFilms (); 
		public ResponseEntity<List<FilmEntity>>    getByTitleContaining (String title); 
		public ResponseEntity<FilmEntity>          getById (int id ); 
		public ResponseEntity<List<FilmEntity>>    getByPublished ();  
		public ResponseEntity<List<FilmEntity>>    getByNotPublished (); 
		
	// post  methods
		public  ResponseEntity<FilmEntity>        saveData1 (FilmEntity film ); 
		public  ResponseEntity<String>            saveData2 (FilmEntity film ); 
		
	//put methods
		public  ResponseEntity<FilmEntity>        updateData1 (FilmEntity film , int id); 
		public  ResponseEntity<FilmEntity>        updateData2 (FilmEntity film ); 
		
	// delete methods  
		public ResponseEntity<String>             deleteById(int id ); 
		public   ResponseEntity<String>           deleteFilm( FilmEntity film ); 
	
	
}
