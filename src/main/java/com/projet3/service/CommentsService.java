package com.projet3.service;
/** @author Hasnae SAKHI */
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.projet3.model.CommentsEntity;

public interface CommentsService {
	
		 
	// get methods
	public ResponseEntity<List<CommentsEntity>>    getAllCommentsByFilmId        ( int filmId);
	public ResponseEntity<CommentsEntity>          getCommentsById               ( int id);
	public ResponseEntity<List<CommentsEntity>>    getAllComments                ( );
	public ResponseEntity<List<CommentsEntity>>    getCommentsByCommentContaining( String comment);
	
	
	//post method
	public ResponseEntity<CommentsEntity>         createComment1(int filmId, CommentsEntity  commentRequest);
	public ResponseEntity<String >                createComment2(int filmId, CommentsEntity  commentRequest);
	public ResponseEntity<String >                createComment3(int filmId, CommentsEntity  commentRequest);
	public ResponseEntity<CommentsEntity>         createComment4(int filmId, CommentsEntity  commentRequest);
	
	// put method
	public ResponseEntity<CommentsEntity>         updateComment1( int id,  CommentsEntity commentRequest);
    
	
	// delete method
	public ResponseEntity<HttpStatus>             deleteComment1            ( int id); 
	public ResponseEntity<String>                 deleteComment2            ( int id); 
	public ResponseEntity<List<CommentsEntity>>   deleteAllCommentsOfFilm   (int filmId); 

}
