package com.projet3.controller;
/** @author Hasnae SAKHI */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
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

import com.projet3.model.CommentsEntity;
import com.projet3.repository.CommentRepository;
import com.projet3.repository.FilmRepository;
import com.projet3.service.CommentsService;

@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	@Qualifier("impl2")
	private CommentsService commentsService ; 
	
	
	
	
                /************************************** GET  Mapping *************************************/
	
    @GetMapping("/getAll")
	public ResponseEntity<List<CommentsEntity>> getAllCommentsC()
	  {  return commentsService.getAllComments();   }  
	
	@GetMapping("/getByfilm/{id}/comments")
	public ResponseEntity<List<CommentsEntity>> getAllCommentsByFilmIdC(@PathVariable( value ="id") int id)
	  {return commentsService.getAllCommentsByFilmId(id); }
	
	@GetMapping("/getByid/{id}")
	public ResponseEntity<CommentsEntity> getCommentsByIdC( @PathVariable( value ="id")  int id)
	  {return commentsService.getCommentsById(id);  }
	
	@GetMapping("/getByCommentContaining") 
	public  ResponseEntity<List<CommentsEntity>>  getCommentsByCommentContainingC( @RequestParam  String comment)
	  { return commentsService.getCommentsByCommentContaining(comment);    }
	
	
	
	
	
             /************************************** POST  Mapping *************************************/
 
   @PostMapping("/saveData1/{id}")// return the status of the response
   public ResponseEntity<CommentsEntity> createComment1C ( @PathVariable("id") int filmId, @RequestBody  CommentsEntity commentRequest)
       {  return commentsService.createComment1(filmId, commentRequest) ;   }
   
   @PostMapping("/saveData2/{id}") // return a message 
   public ResponseEntity<String> createComment2C          ( @PathVariable("id") int filmId, @RequestBody  CommentsEntity commentRequest)
   {  return commentsService.createComment2(filmId, commentRequest) ;   }
   
   @PostMapping("/saveData3/{id}") // using map  with a response 
   public ResponseEntity<String> createComment3C         ( @PathVariable("id") int filmId, @RequestBody  CommentsEntity commentRequest)
   {  return commentsService.createComment3(filmId, commentRequest) ;   }
   
   @PostMapping("/saveData4/{id}") // using map  simple 
   public ResponseEntity<CommentsEntity> createComment4C ( @PathVariable("id") int filmId, @RequestBody  CommentsEntity commentRequest)
   {  return commentsService.createComment4(filmId, commentRequest) ;   }
   
   
   
   
   
   
             /**************************************   PUT    Mapping  *************************************/
   
   @PutMapping("/update1/{id}")
   public ResponseEntity<CommentsEntity> updateComment1C( @PathVariable ("id") int id, @RequestBody  CommentsEntity commentRequest)
   { return commentsService.updateComment1(id, commentRequest);   }
   
   
   
   
   
           /**************************************  DELETE  Mapping   *************************************/   
   
   @DeleteMapping("/deletebyid/{id}")
   public ResponseEntity<HttpStatus> deleteComment1( @PathVariable("id")  int id)
   {  return commentsService.deleteComment1(id);   }
   
   @DeleteMapping("/deletebyid2/{id}")
   public ResponseEntity<String> deleteComment2(@PathVariable("id")  int id)
   {  return commentsService.deleteComment2(id);   }
   
   @DeleteMapping("/deleteall/{id}")
   public ResponseEntity<List<CommentsEntity>> deleteAllCommentsOfFilm(@PathVariable("id")   int id)
   {  return commentsService.deleteAllCommentsOfFilm(id) ;   }
}

