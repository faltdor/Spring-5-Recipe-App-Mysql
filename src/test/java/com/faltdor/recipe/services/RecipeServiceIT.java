package com.faltdor.recipe.services;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.faltdor.recipe.commands.CategoryCommand;
import com.faltdor.recipe.commands.IngredientCommand;
import com.faltdor.recipe.commands.NoteCommand;
import com.faltdor.recipe.commands.RecipeCommand;
import com.faltdor.recipe.converters.RecipeCommandToRecipe;
import com.faltdor.recipe.converters.RecipeToRecipeCommand;
import com.faltdor.recipe.domain.Category;
import com.faltdor.recipe.domain.Ingredient;
import com.faltdor.recipe.domain.Note;
import com.faltdor.recipe.domain.Recipe;
import com.faltdor.recipe.enums.Difficulty;
import com.faltdor.recipe.repositories.IRecipeRepository;
import com.faltdor.recipe.services.impl.RecipeServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceIT {
	public static final Long RECIPE_ID = 1L;
	public static final Integer COOK_TIME = Integer.valueOf("5");
    public static final Integer PREP_TIME = Integer.valueOf("7");
    public static final String DESCRIPTION = "My Recipe";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Integer SERVINGS = Integer.valueOf("3");
    public static final String SOURCE = "Source";
    public static final String URL = "Some URL";
    public static final Long CAT_ID_1 = 1L;
    public static final Long CAT_ID2 = 2L;
    public static final Long INGRED_ID_1 = 3L;
    public static final Long INGRED_ID_2 = 4L;
    public static final Long NOTES_ID = 9L;
	
	@Autowired
	RecipeServiceImpl recipeServiceImpl;
	
	@Autowired 
	IRecipeRepository recipeRepository;
	
	@Autowired
	RecipeCommandToRecipe recipeCommandToRecipe;
	
	@Autowired
	RecipeToRecipeCommand recipeToRecipeCommand;

	@Before
	public void setUp() throws Exception {
	}
	
	@Transactional
	@Test
	public void testSaveRecipeCommand() {
		//Given
		RecipeCommand source = new RecipeCommand();
        source.setId(RECIPE_ID);
        source.setCookTime(COOK_TIME);
        source.setPrepTime(PREP_TIME);
        source.setDescription(DESCRIPTION);
        source.setDifficulty(DIFFICULTY);
        source.setDirections(DIRECTIONS);
        source.setServings(SERVINGS);
        source.setSource(SOURCE);
        source.setUrl(URL);
		
        NoteCommand notes = new NoteCommand();
        notes.setId(NOTES_ID);

        source.setNote(notes);
        CategoryCommand category = new CategoryCommand();
        category.setId(CAT_ID_1);

        CategoryCommand category2 = new CategoryCommand();
        category2.setId(CAT_ID2);

        source.getCategories().add(category);
        source.getCategories().add(category2);
        
        IngredientCommand ingredient = new IngredientCommand();
        ingredient.setId(INGRED_ID_1);

        IngredientCommand ingredient2 = new IngredientCommand();
        ingredient2.setId(INGRED_ID_2);

        source.getIngredients().add(ingredient);
        source.getIngredients().add(ingredient2);
        
		
		//When
		RecipeCommand recipeResult = recipeServiceImpl.saveRecipeCommand(source);
		
		assertNotNull(recipeResult);
        assertEquals(RECIPE_ID, recipeResult.getId());
        assertEquals(COOK_TIME, recipeResult.getCookTime());
        assertEquals(PREP_TIME, recipeResult.getPrepTime());
        assertEquals(DESCRIPTION, recipeResult.getDescription());
        assertEquals(DIFFICULTY, recipeResult.getDifficulty());
        assertEquals(DIRECTIONS, recipeResult.getDirections());
        assertEquals(SERVINGS, recipeResult.getServings());
        assertEquals(SOURCE, recipeResult.getSource());
        assertEquals(URL, recipeResult.getUrl());
        assertEquals(NOTES_ID, recipeResult.getNote().getId());
        assertEquals(2, recipeResult.getCategories().size());
        assertEquals(2, recipeResult.getIngredients().size());
	}

}
