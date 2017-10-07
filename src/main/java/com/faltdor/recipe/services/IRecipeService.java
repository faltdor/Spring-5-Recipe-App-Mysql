package com.faltdor.recipe.services;

import java.util.Set;

import com.faltdor.recipe.commands.RecipeCommand;
import com.faltdor.recipe.domain.Recipe;

public interface IRecipeService {
	
	public Set<Recipe> getRecipes();
	public Recipe findById(long id);
	public RecipeCommand findCommandById(Long id);
	public RecipeCommand saveRecipeCommand(RecipeCommand command);
}
