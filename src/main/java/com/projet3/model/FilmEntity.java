package com.projet3.model;
/** @author Hasnae SAKHI */
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="film")
public class FilmEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(nullable = false)
	private String title;
	@Column
	private String description;
	@Column
	private boolean published ; 
	
	   
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL , mappedBy ="film" )
	private List<CommentsEntity> comments ; 
	
}
