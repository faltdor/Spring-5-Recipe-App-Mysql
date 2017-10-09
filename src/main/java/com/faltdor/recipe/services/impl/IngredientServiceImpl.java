package com.faltdor.recipe.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.faltdor.recipe.commands.IngredientCommand;
import com.faltdor.recipe.converters.IngredientCommandToIngredient;
import com.faltdor.recipe.converters.IngredientToIngredientCommand;
import com.faltdor.recipe.domain.Ingredient;
import com.faltdor.recipe.domain.Recipe;
import com.faltdor.recipe.repositories.IRecipeRepository;
import com.faltdor.recipe.repositories.IUnitOfMeasureRepository;
import com.faltdor.recipe.services.IIngredientService;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class IngredientServiceImpl implements IIngredientService {
	
	private final IRecipeRepository recipeRepository;
	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	private final IngredientCommandToIngredient ingredientCommandToIngredient;
	private final IUnitOfMeasureRepository iUnitOfMeasureRepository;
	
				  
	public IngredientServiceImpl(IRecipeRepository recipeRepository, 
								 IngredientToIngredientCommand ingredientCommand,
								 IUnitOfMeasureRepository iUnitOfMeasureRepository,
								 IngredientCommandToIngredient ingredientCommandToIngredient) {
		this.recipeRepository = recipeRepository;
		this.ingredientToIngredientCommand = ingredientCommand;
		this.iUnitOfMeasureRepository = iUnitOfMeasureRepository;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
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
					.map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();
		
		if(!ingredientCommandOptional.isPresent()) {
			//TODO: Impl error Handlding
			log.debug("!Error recipe id not found "+idRecipe);
		}
		
		return ingredientCommandOptional.get();
	}

	public IngredientCommand saveIngredient(IngredientCommand saveCommand) {
		
		Optional<Recipe> recipeOptional = recipeRepository.findById(saveCommand.getRecipeId());
		
		if(!recipeOptional.isPresent()) {
			log.error("Recipe not found :"+saveCommand.getRecipeId());
			return new IngredientCommand();
		}else {
			Recipe recipe = recipeOptional.get();
			
			Optional<Ingredient> ingredientOptional = recipe.getIngredients()
															.stream()
															.filter(ingredient -> ingredient.getId().equals(saveCommand.getId()))
															.findFirst();
			if(ingredientOptional.isPresent()) {
				Ingredient ingredient = ingredientOptional.get();
				ingredient.setDescription(saveCommand.getDescription());
				ingredient.setMeasure(iUnitOfMeasureRepository.findById(saveCommand.getMeasure().getId())
																		.orElseThrow(() -> new RuntimeException("UOM NOT FOUND")));
				
			}else {
				Ingredient ingredient = ingredientCommandToIngredient.convert(saveCommand);
				ingredient.setRecipe(recipe);
				recipe.addIngredient(ingredient);
			}
			
			Recipe savedRecipe = recipeRepository.save(recipe);
			
			Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
                    .filter(recipeIngredients -> recipeIngredients.getId().equals(saveCommand.getId()))
                    .findFirst();

            //check by description
            if(!savedIngredientOptional.isPresent()){
                //not totally safe... But best guess
                savedIngredientOptional = savedRecipe.getIngredients().stream()
                        .filter(recipeIngredients -> recipeIngredients.getDescription().equals(saveCommand.getDescription()))
                        .filter(recipeIngredients -> recipeIngredients.getAmount().equals(saveCommand.getAmount()))
                        .filter(recipeIngredients -> recipeIngredients.getMeasure().getId().equals(saveCommand.getMeasure().getId()))
                        .findFirst();
}
			
          //to do check for fail
            return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
		}
		
		
			
	}

	@Override
	public void deleteById(Long recipeId, Long idToDelete) {
		log.debug("Deleting ingredient: " + recipeId + ":" + idToDelete);
		
		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if(recipeOptional.isPresent()){
            Recipe recipe = recipeOptional.get();
            log.debug("found recipe");

            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(idToDelete))
                    .findFirst();

            if(ingredientOptional.isPresent()){
                log.debug("found Ingredient");
                Ingredient ingredientToDelete = ingredientOptional.get();
                ingredientToDelete.setRecipe(null);
                recipe.getIngredients().remove(ingredientOptional.get());
                recipeRepository.save(recipe);
            }
        } else {
        	log.debug("Recipe Id Not found. Id:" + recipeId);
        }
		
	}

}
