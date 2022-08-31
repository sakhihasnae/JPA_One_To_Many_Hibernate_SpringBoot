package com.projet3.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet3.model.CommentsEntity;

public interface CommentRepository extends JpaRepository<CommentsEntity, Integer> {

	/*********************************************************************************
     **************** Autres methodes FindBy..... Personalisées ************************
    ***********************************************************************************/
	
	 public List<CommentsEntity> findByFilmId (int id);   
	 public List<CommentsEntity> findByCommentContaining (String comment); 
	 
	 @Transactional
	 void deleteByFilmId(int id ); 
	
}
 