package com.projet3.service;
/** @author Hasnae SAKHI */
import java.util.ArrayList; 
import java.util.List;
import java.util.Optional;

import javax.persistence.Lob;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projet3.model.CommentsEntity;
import com.projet3.model.FilmEntity;
import com.projet3.repository.CommentRepository;
import com.projet3.repository.FilmRepository;

import ch.qos.logback.core.net.SocketConnector.ExceptionHandler;

@Service("impl2")
public class CommentsServiceImpl implements CommentsService {

	        /*********************************   Spring Data     ****************************/
	@Autowired
	private CommentRepository commentRepository ;
	@Autowired
	private FilmRepository    filmRepository;  // because the comments depends on the film id . 
	@Autowired
	private ModelMapper mapper ; 

	
    /**************************************************************************************************************************
    * *******************************************        GET METHODS    ****************************************************
    * * ************************************************************************************************************************/
	
	                 /*************************** GET All Comments By filmID *****************************/
	
			@Override
			public ResponseEntity<List<CommentsEntity>> getAllCommentsByFilmId(int id) {
			 
				try {
			if( filmRepository.existsById(id)==true)
				
			{    
			    List<CommentsEntity> comments= new ArrayList<CommentsEntity>(); 
			    commentRepository.findByFilmId(id).forEach(comments::add);
			    
				if(comments.isEmpty())
					return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
				else
				return new ResponseEntity<List<CommentsEntity>>(comments, HttpStatus.OK); 
			}
			else  
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}catch(Exception e )
				{return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); }
			}

				
			         /*********************** GET a comment by it's ID  ************************/
			
			
			@Override
			public ResponseEntity<CommentsEntity> getCommentsById(int id) {
				try { 
					
			Optional<CommentsEntity> comment = commentRepository.findById(id); 
			    if(comment.isEmpty())
				             return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			    else 
				             return new ResponseEntity<CommentsEntity>(comment.get(), HttpStatus.OK); 
				
				}catch(Exception e )
				           {return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); }
				
			                                                               }

			       /**************************   GET All comments  ***************************/
			
				@Override
			public ResponseEntity<List<CommentsEntity>> getAllComments() {
				
				try {
					List<CommentsEntity> lst = new ArrayList<CommentsEntity>(); 
				    commentRepository.findAll().forEach(lst::add);
				            if(lst.isEmpty())
					                  return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
				            else {
				                      return new ResponseEntity<>(lst, HttpStatus.OK);  }}
				catch(Exception e )
				{return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);    }
			}

			
				
				/************************** GET a comment by a word in it ***************************/
				
			@Override
			public  ResponseEntity<List<CommentsEntity>>  getCommentsByCommentContaining(String comment) {
			
				try {
					List<CommentsEntity> lst = new ArrayList<CommentsEntity>(); 
					commentRepository.findByCommentContaining(comment).forEach(lst::add);
					    if(lst.isEmpty())
						          return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
					    else
						          return new ResponseEntity<List<CommentsEntity>>(lst, HttpStatus.OK);
					
				}catch(Exception e )
				{return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); }
				
				
				
			}

		/**************************************************************************************************************************
		*********************************************        POST METHODS    *******************************************************
		*************************************************************************************************************************/
			
			            /********************* Add a comment for a film  **********************/
                                  /*********** Returns the comment added **********/
			
			@Override
			public ResponseEntity<CommentsEntity> createComment1(int filmId, CommentsEntity commentRequest) {
				
				try {
				    Optional<FilmEntity>  film = filmRepository.findById(filmId);
				    if(film.isEmpty())
					{return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);}
				    else 
				    	{ commentRequest.setFilm(film.get());
				     	  commentRepository.save(commentRequest);  
				    	 return new ResponseEntity<CommentsEntity>(commentRequest, HttpStatus.CREATED); }
				    
				}catch(Exception e)
				{ return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); }
				
			}
			         /********************* Add a comment for a film  **********************/
                                   /*********** Returns a message **********/
			@Override
			public ResponseEntity<String> createComment2(int filmId, CommentsEntity commentRequest) {
				String message=null;
				try {
					
					if( commentRequest.getComment().isEmpty())
					{message="Please make sure you write a comment of the film "; 
			    	return new ResponseEntity<String>(message, HttpStatus.NO_CONTENT); }
					else {
					
						Optional<FilmEntity>  film = filmRepository.findById(filmId);
				        if(film.isEmpty())
					           {message="The Film does not exist"; 
				    	       return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);}
				        else 
				    	{ 
				        commentRequest.setFilm(film.get());
				    	commentRepository.save(commentRequest); 
				    	 return new ResponseEntity< >(null, HttpStatus.CREATED); }}
				    
				}catch(Exception e)
				{ message= "Server Error!!!!!!!!!!!!!!!!!!"; 
					return new ResponseEntity<String >(message, HttpStatus.INTERNAL_SERVER_ERROR); } 
			}

			
			          /********************* Add a comment for a film  **********************/
                       /******* Returns the comment : with the film's informations  *******/
			@Override
			public ResponseEntity<String> createComment3(int filmId, CommentsEntity commentRequest) {
				
			String message=null; 
				      filmRepository.findById(filmId).map(tutorial -> {
				      commentRequest.setFilm(tutorial);
				      return  commentRepository.save(commentRequest); }) ;
				    
				      String comment = commentRequest.getComment(); 
				      FilmEntity film = commentRequest.getFilm(); 
				      
				      message="Comment Added with succes \n  "+"Your comment :"  +comment+
				      "\n"+ " \t \t For the film \n "
				      +"Title : "+ " "+film.getTitle() +" \n  About the movie "+" "+ film.getDescription() ;
				      return new ResponseEntity<String>(message, HttpStatus.CREATED);
			} 
			
			
			             /********************* Add a comment for a film  **********************/
                                             /*******   Usin MAP  *******/
			@Override
			public ResponseEntity<CommentsEntity> createComment4(int filmId, CommentsEntity commentRequest) {
				filmRepository.findById(filmId).map(tutorial -> {
				      commentRequest.setFilm(tutorial);
				      return  commentRepository.save(commentRequest); }) ;
				      return new ResponseEntity<>(null, HttpStatus.CREATED);
			} 
			 
	/**************************************************************************************************************************
	*********************************************        PUT METHODS    ******************************************************
	*************************************************************************************************************************/
			  
			          /********************* Update a comment by it's ID   **********************/
                              /*********** Returns the comment updated **********/
			
			@Override
			public ResponseEntity<CommentsEntity> updateComment1(int id, CommentsEntity commentRequest) {
				
				try {
					
				   Optional<CommentsEntity> comment = commentRepository.findById(id);
				      if(comment.isEmpty())
			 		            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
				      else {
					            comment.get().setComment(commentRequest.getComment());
					            commentRepository.save(comment.get()); 
					           return new ResponseEntity<CommentsEntity>(comment.get(), HttpStatus.CREATED); 
				          }
				    }catch(Exception e )
				{ return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);  }	
			}

			
			
		/**************************************************************************************************************************
		*********************************************        DELETE METHODS    ****************************************************
		*************************************************************************************************************************/	
			
			         /********************* delete a comment by it's ID   **********************/
                               /*********** Returns the status of the response **********/
			
			@Override
			public ResponseEntity<HttpStatus> deleteComment1(int id) {
			
				try {
					Optional<CommentsEntity>  comment=  commentRepository.findById(id);
					     if(comment.isEmpty())
						        return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
					     else 
					            {commentRepository.deleteById(id);
						        return new ResponseEntity<HttpStatus>(HttpStatus.OK); }
					     
				}catch(Exception e )
				{return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);}
				
			}
			          /********************* delete a comment by it's ID   **********************/
                                     /*********** Returns a message  **********/
			
			@Override
			public ResponseEntity<String> deleteComment2(int id) {
				String message=null;
				try {
					Optional<CommentsEntity>  comment=  commentRepository.findById(id);
					     if(comment.isEmpty())
					     {message="the comment you wwonna delete does not existe by this id , please chek again "; 
						        return new ResponseEntity<String >(message,HttpStatus.NOT_FOUND);}
					     else 
					            {commentRepository.deleteById(id);
					            message= "comment deleted with succes"; 
						        return new ResponseEntity<String >(message, HttpStatus.OK); }
					     
				}catch(Exception e )
				{return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);}
			}

			
			/********************* delete a comment by the film id   **********************/
                              
			@Override
			public ResponseEntity<List<CommentsEntity>> deleteAllCommentsOfFilm(int id) {
				
				try {
					         if(filmRepository.existsById(id)==false)
					                  return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
					         else 
					                  {commentRepository.deleteByFilmId(id);
					                  return new ResponseEntity< >(null, HttpStatus.OK); }
					     
				}catch(Exception e )
				{return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);}
				
			} 
	
			/*****************************************************************************************************	
			******************************************************************************************************
			**********************************************  The END  ********************************************** 
			*************************************  Project made By Hasnae SAKHI   ********************************* 
			*******************************************************************************************************
			*******************************************************************************************************/
	
	
	
	
}
