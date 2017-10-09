package com.faltdor.recipe.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.faltdor.recipe.commands.IngredientCommand;
import com.faltdor.recipe.converters.IngredientToIngredientCommand;
import com.faltdor.recipe.domain.Recipe;
import com.faltdor.recipe.repositories.IRecipeRepository;
import com.faltdor.recipe.services.IIngredientService;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class IngredientServiceImpl implements IIngredientService {
	
	private final IRecipeRepository recipeRepository;
	private final IngredientToIngredientCommand ingredientCommand;
				  
	public IngredientServiceImpl(IRecipeRepository recipeRepository, IngredientToIngredientCommand ingredientCommand) {
		this.recipeRepository = recipeRepository;
		this.ingredientCommand = ingredientCommand;
	}

	public IngredientCommand findByRecipeIdAndIngredientId(Long ingredientId, Long idRecipe ) {
		
		Optional<Recipe> recipeOptional =  recipeRepository.findById(idRecipe);
		
		if(!recipeOptional.isPresent()) {
			//TODO: Impl error Handlding
			log.debug("!Error recipe id not found "+idRecipe);
		}
		
		Recipe recipe = recipeOptional.get();
		
		Optional<IngredientCommand> ingredientCommandOptional =
					recipe.getIngredients().stream()
					.filter(ingredient -> ingredient.getId().equals(ingredientId))
					.map(ingredient -> ingredientCommand.convert(ingredient)).findFirst();
		
		if(!ingredientCommandOptional.isPresent()) {
			//TODO: Impl error Handlding
			log.debug("!Error recipe id not found "+idRecipe);
		}
		
		return ingredientCommandOptional.get();
	}

}
