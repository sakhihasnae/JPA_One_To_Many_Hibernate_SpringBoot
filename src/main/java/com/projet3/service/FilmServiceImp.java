package com.projet3.service;
/** @author Hasnae SAKHI */
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projet3.model.FilmEntity;
import com.projet3.repository.FilmRepository;

@Service("imp1")
public class FilmServiceImp implements FilmService {

	@Autowired
	private FilmRepository filmRepository ; 
	
	
	/**************************************************************************************************************************
	 * ********************************************        GET METHODS    ****************************************************
	 * ************************************************************************************************************************/
		
		
		              /*********************************** GET All Films ************************************/
		@Override
		public ResponseEntity<List<FilmEntity>> getAllFilms() {
			
			List<FilmEntity > lst = new ArrayList<FilmEntity>(); 
			filmRepository.findAll().forEach(lst::add);
			try {
			           if(lst.isEmpty())
		                  	return new ResponseEntity<>(null, HttpStatus.NO_CONTENT); 
			          else
				           return new ResponseEntity<>(lst, HttpStatus.OK); 
			   }catch(Exception e )
			      { return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);}
			}

		
		
		
		           /*********************************** GET By Title Of A film  ************************************/
	
		public ResponseEntity<List<FilmEntity>> getByTitleContaining(String title) {
			if(title.isEmpty())
				   return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); 
			
		    else {
				   try{    
					   List<FilmEntity>  film =  new ArrayList<FilmEntity>(); 
					   filmRepository.findByTitleContaining(title).forEach(film::add);
				           if(film.isEmpty())
			                  	return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
				          else
					           return new ResponseEntity<List<FilmEntity>>(film, HttpStatus.OK) ; 
							
				   }catch(Exception e )
				      { return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);}
			     }
		}
		  
		
		
		
		     /*********************************** GET a Film by it's ID  ************************************/

		@Override
		public ResponseEntity<FilmEntity> getById(int id) {
			
				   try{    
					   Optional<FilmEntity>  film =  filmRepository.findById(id); 
				           if(film.isEmpty() )
			                  	return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
				          else
					           return new ResponseEntity<FilmEntity>(film.get() , HttpStatus.OK) ; 
							
				   }catch(Exception e )
				      { return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);}
			     }
		


		 /***********************************  GET By Published Film ************************************/

		@Override
		public ResponseEntity<List<FilmEntity>> getByPublished() {
			
			try{  
					        List<FilmEntity>  film =  new ArrayList<FilmEntity>(); 
					        filmRepository.findByPublished(true).forEach(film::add);
				             if(film.isEmpty())
			                  	return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
				             else
					           return new ResponseEntity<List<FilmEntity>>(film, HttpStatus.OK) ; 
					 }catch(Exception e )
				      { return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);}
			     }
		
		
		
		/***********************************  GET By NotPublished Film ************************************/
		
		@Override
		public ResponseEntity<List<FilmEntity>> getByNotPublished() {
		
			try{  
		        List<FilmEntity>  film =  new ArrayList<FilmEntity>(); 
		        filmRepository.findByPublished(false).forEach(film::add);
	             if(film.isEmpty())
	              	return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
	             else
		           return new ResponseEntity<List<FilmEntity>>(film, HttpStatus.OK) ; 
		 }catch(Exception e )
	      { return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);}
		}

	/**************************************************************************************************************************
	*********************************************        POST METHODS    *******************************************************
	*************************************************************************************************************************/
		
		           /**************************  Save a Film : Return the film saved ***************************/
		
		@Override
		public ResponseEntity<FilmEntity> saveData1(FilmEntity film) {
			try {
			filmRepository.save(film); 
			return new ResponseEntity<FilmEntity>(film, HttpStatus.CREATED); 
			    }catch(Exception e )
			{ return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); }
			
		}
		
		          /*************************  Save a Film : Return a message of saving  *************************/

		@Override
		public ResponseEntity<String> saveData2(FilmEntity film) {
			String message=null; 
			try {
				filmRepository.save(film); 
				message="Film added with succes \n "+ film.toString(); 
				return new ResponseEntity<String>(message, HttpStatus.CREATED); 
				    }catch(Exception e )
				{ message="Server Probleme !!!!!!!!!!!!!! ";
				    	return new ResponseEntity<String>(message, HttpStatus.INTERNAL_SERVER_ERROR); }
		}

		 
	/**************************************************************************************************************************
	*********************************************        PUT METHODS    ******************************************************
	*************************************************************************************************************************/
	 
		 /*********************  Update a Film  informations  : Return the film Updated  *******************/
		                        /*** the id  of the film is given as a path variable **/
		
		
		@Override
		public ResponseEntity<FilmEntity> updateData1(FilmEntity film, int id) {
			
			try {
				 FilmEntity filmEntity =  filmRepository.findById(id).get();
				 filmEntity.setTitle(film.getTitle());
				 filmEntity.setDescription(film.getDescription());
				 filmEntity.setPublished(film.isPublished());
				 filmRepository.save(filmEntity); 
				 return new ResponseEntity<FilmEntity>(filmEntity , HttpStatus.ACCEPTED); 
		
			}catch(Exception e )
			{return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); }
		}

		
		/*********************  Update a Film  informations  : Return the film Updated  *******************/
                        /*** the id  of the film is included  in the request Body given  **/
		@Override
		public ResponseEntity<FilmEntity> updateData2(FilmEntity film) {
			try {
				int id = film.getId(); 
				 FilmEntity filmEntity =  filmRepository.findById(film.getId()).get();
				 filmEntity.setTitle(film.getTitle());
				 filmEntity.setDescription(film.getDescription());  
				 filmEntity.setPublished(film.isPublished());
				 filmRepository.save(filmEntity); 
				 return new ResponseEntity<FilmEntity>(filmEntity , HttpStatus.ACCEPTED); 
		
			}catch(Exception e )
			{return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); }
		}

		
	/**************************************************************************************************************************
	*********************************************        DELETE METHODS    ****************************************************
	*************************************************************************************************************************/

		
		/*********************  Delete  a Film  By it's id   : Return a message of deleting  *******************/
		@Override
		public ResponseEntity<String> deleteById(int id) {
			String message=null;  
			try { 
			filmRepository.deleteById(id);
			message="film deleted"; 
			return new ResponseEntity<String>(message, HttpStatus.OK); }
			catch(Exception e )
			{message="Server problem!!!!!!!!!!";
			return new ResponseEntity<String>(message, HttpStatus.INTERNAL_SERVER_ERROR); }
		}
 
		
		/********  Delete  a Film  By a request Body (All attributes)  : Return a message of deleting  ************/
		@Override
		public ResponseEntity<String> deleteFilm(FilmEntity film) {
			String message=null; 
			try { 
			filmRepository.delete(film);
			message="film deleted"; 
			return new ResponseEntity<String>(message, HttpStatus.OK); }
			catch(Exception e )
			{message="Server problem!!!!!!!!!!";
			return new ResponseEntity<String>(message, HttpStatus.INTERNAL_SERVER_ERROR); }
		} 
		
		
		/*****************************************************************************************************	
		******************************************************************************************************
		**********************************************  The END  ********************************************** 
		*************************************  Project made By Hasnae SAKHI   ********************************* 
		*******************************************************************************************************
		*******************************************************************************************************/
	
	
}
