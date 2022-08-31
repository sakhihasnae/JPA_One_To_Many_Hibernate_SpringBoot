package com.projet3.controller;
/** @author Hasnae SAKHI */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projet3.model.FilmEntity;
import com.projet3.service.FilmService;

@RestController
@RequestMapping("/film")

public class FilmController {

	
	@Autowired
	@Qualifier("imp1")
	private FilmService filmService ; 
	 

	           /************************************** GET  Mapping *************************************/
	    @GetMapping("/getAll")
		public ResponseEntity<List<FilmEntity>>     getAllFilms ()
	    {  return filmService.getAllFilms();  }
	    
	   
	    @GetMapping("/getbytitleC")
		public ResponseEntity<List<FilmEntity>>    getByTitleContaining (@RequestParam (required = false) String title)
		{return filmService.getByTitleContaining(title) ;}
	    
	    @GetMapping("/getbyid/{id}")
		public ResponseEntity<FilmEntity>          getById (  @PathVariable("id") int id )
		{ return filmService.getById(id) ; }
	    
	    @GetMapping("/getbypulished")
		public ResponseEntity<List<FilmEntity>>    getByPublished ()
		{return filmService.getByPublished(); }
	    
	    @GetMapping("/getbyNotpulished")
		public ResponseEntity<List<FilmEntity>>    getByNotPublished ()
		{ return filmService.getByNotPublished(); }
	    
	    
		
	           /************************************** POST  Mapping *************************************/
	    
	    @PostMapping("/savedata1")
		public  ResponseEntity<FilmEntity>        saveData1 ( @RequestBody   FilmEntity film )
		{ return filmService.saveData1(film); } 
	    
	    @PostMapping("/savedata2")
		public  ResponseEntity<String>            saveData2 (@RequestBody   FilmEntity film)
	    { return filmService.saveData2(film); } 
	    
	    
	    
	    /**************************************   PUT    Mapping  *************************************/
	    @PutMapping("/update1/{id}")
		public  ResponseEntity<FilmEntity>        updateData1 (@RequestBody   FilmEntity film ,  @PathVariable("id")  int id)
		{ return filmService.updateData1(film, id); }
	    
	    @PutMapping("/update2")
		public  ResponseEntity<FilmEntity>        updateData2 (@RequestBody   FilmEntity film)
		{return filmService.updateData2(film);}
		
	    /**************************************  DELETE  Mapping   *************************************/  
	    @DeleteMapping("/delete1/{id}")
		public ResponseEntity<String>             deleteById(@PathVariable("id")    int id )
		{return filmService.deleteById(id);  }
	    
	    @DeleteMapping("/delete2")
		public   ResponseEntity<String>           deleteFilm( @RequestBody   FilmEntity film )
		{return  filmService.deleteFilm(film);  	}
		
	
	     
	
}
