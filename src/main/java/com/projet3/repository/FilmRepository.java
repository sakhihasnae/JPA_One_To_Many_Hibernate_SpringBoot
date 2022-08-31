package com.projet3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet3.model.FilmEntity;

public interface FilmRepository extends JpaRepository<FilmEntity, Integer> {
	
	          /*********************************************************************************
	          **************** Autres methodes FindBy..... Personalisées ************************
	         ***********************************************************************************/
	
	FilmEntity        findByTitle           (String title);
	List<FilmEntity>  findByTitleContaining (String title);
	List<FilmEntity>  findByPublished       (boolean choix);
}
