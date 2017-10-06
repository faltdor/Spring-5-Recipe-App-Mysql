package com.faltdor.recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.faltdor.recipe.commands.RecipeCommand;
import com.faltdor.recipe.domain.Recipe;

import lombok.Synchronized;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand,Recipe>{
	
	private final NotesCommandToNotes notesCommandToNotes;
	private final CategoryCommandToCategory categoryConverter;
	private final IngredientCommandToIngredient ingredientConverter; 
	
	public RecipeCommandToRecipe(NotesCommandToNotes notesCommandToNotes, CategoryCommandToCategory categoryConverter,
			IngredientCommandToIngredient ingredientConverter) {
		super();
		this.notesCommandToNotes = notesCommandToNotes;
		this.categoryConverter = categoryConverter;
		this.ingredientConverter = ingredientConverter;
	}

	@Synchronized
    @Nullable
    @Override
	public Recipe convert(RecipeCommand source) {
		if (source == null) {return null;}
		
		final Recipe recipe = new Recipe();
		recipe.setId(source.getId());
        recipe.setCookTime(source.getCookTime());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setDescription(source.getDescription());
        recipe.setDifficulty(source.getDifficulty());
        recipe.setDirections(source.getDirections());
        recipe.setServings(source.getServings());
        recipe.setSource(source.getSource());
        recipe.setUrl(source.getUrl());
        
        recipe.setNote(notesCommandToNotes.convert(source.getNote()));
        
        if(source.getCategories() != null && source.getCategories().size() > 0) {
        	source.getCategories().forEach(category -> recipe.getCategories().add(categoryConverter.convert(category)));
        }
        
        if(source.getIngredients() != null && source.getCategories().size()>0) {
        	source.getIngredients().forEach(ingredient -> recipe.getIngredients().add(ingredientConverter.convert(ingredient)));
        }
        
		return recipe;
	}

}
