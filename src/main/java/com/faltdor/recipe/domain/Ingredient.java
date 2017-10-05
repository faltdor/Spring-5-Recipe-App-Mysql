package com.faltdor.recipe.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Ingredient {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	private BigDecimal amount;
	
	@ManyToOne
	private Recipe recipe;
	
	
	@OneToOne(fetch = FetchType.EAGER)
	private UnitOfMeasure measure;
	
	public Ingredient(String description, BigDecimal amount, UnitOfMeasure measure) {
		this.description = description;
		this.amount = amount;
		this.measure = measure;
	}

	
	
	
	
	
	
	
	
	
}
