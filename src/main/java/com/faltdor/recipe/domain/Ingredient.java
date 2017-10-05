package com.faltdor.recipe.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
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
		this.recipe = recipe;
		this.measure = measure;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public UnitOfMeasure getMeasure() {
		return measure;
	}

	public void setMeasure(UnitOfMeasure measure) {
		this.measure = measure;
	}
	
	
	
	
	
	
	
	
}