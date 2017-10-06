package com.faltdor.recipe.services;

import java.util.Set;

import com.faltdor.recipe.domain.Recipe;

public interface IRecipeService {
	
	public Set<Recipe> getRecipes();
	public Recipe findById(long id);
}
