package com.projet3.model;
/** @author Hasnae SAKHI */
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="comments")

public class CommentsEntity  implements Serializable  {
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column
	@Lob 
	private String comment; 
	 
   @ManyToOne(fetch = FetchType.LAZY , optional = false)
   @JoinColumn(name="film_id", nullable = false)
   @OnDelete(action = OnDeleteAction.CASCADE)
   @JsonIgnore
	private FilmEntity film ; 
}
