package com.faltdor.recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.faltdor.recipe.commands.RecipeCommand;
import com.faltdor.recipe.domain.Recipe;

import lombok.Synchronized;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand>{
	
	
	private final NoteToNoteCommand noteConverter;
	private final CategoryToCategoryCommand categoryConverter;
	private final IngredientToIngredientCommand ingredientConverter;
	
	public RecipeToRecipeCommand(NoteToNoteCommand noteConverter, CategoryToCategoryCommand categoryConverter,
			IngredientToIngredientCommand ingredientConverter) {
		this.noteConverter = noteConverter;
		this.categoryConverter = categoryConverter;
		this.ingredientConverter = ingredientConverter;
	}

	@Synchronized
	@Nullable
	@Override
	public RecipeCommand convert(Recipe source) {
		
		if(source == null) {return null;}
		
		final RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId(source.getId());
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setPrepTime(source.getPrepTime());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setDifficulty(source.getDifficulty());
        recipeCommand.setDirections(source.getDirections());
        recipeCommand.setServings(source.getServings());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setUrl(source.getUrl());
        
        recipeCommand.setNote(noteConverter.convert(source.getNote()));
        
        if(source.getCategories() != null && source.getCategories().size() > 0) {
        	source.getCategories().forEach(category -> recipeCommand.getCategories().add(categoryConverter.convert(category)));
        }
        
        if(source.getIngredients() != null && source.getIngredients().size() > 0) {
        	source.getIngredients().forEach(ingredient -> recipeCommand.getIngredients().add(ingredientConverter.convert(ingredient)));
        }
        
		return recipeCommand;
	}

}
