package com.faltdor.recipe.services;

import com.faltdor.recipe.commands.IngredientCommand;

public interface IIngredientService {
	
	public IngredientCommand findByRecipeIdAndIngredientId(Long ingredientId,Long idRecipe) ;
	
}
