package com.faltdor.recipe.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.faltdor.recipe.commands.IngredientCommand;
import com.faltdor.recipe.domain.Ingredient;

import lombok.Synchronized;

@Component 
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

	private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;
	
	@Autowired
	public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomConverter) {
		this.uomConverter = uomConverter;
	}
	
	@Synchronized
	@Nullable
	@Override
	public IngredientCommand convert(Ingredient source) {
		if(source == null) {return null;}
		
		final IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setId(source.getId());
		ingredientCommand.setDescription(source.getDescription());
		ingredientCommand.setAmount(source.getAmount());
		ingredientCommand.setMeasure(uomConverter.convert(source.getMeasure()));
		if(null != source.getRecipe()) {
			ingredientCommand.setRecipeId(source.getRecipe().getId());
		}
		
		
		
		return ingredientCommand;
	}

}
