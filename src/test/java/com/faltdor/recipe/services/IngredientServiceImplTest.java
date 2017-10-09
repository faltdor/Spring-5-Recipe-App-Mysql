package com.faltdor.recipe.services;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.faltdor.recipe.commands.IngredientCommand;
import com.faltdor.recipe.converters.IngredientToIngredientCommand;
import com.faltdor.recipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.faltdor.recipe.domain.Ingredient;
import com.faltdor.recipe.domain.Recipe;
import com.faltdor.recipe.repositories.IRecipeRepository;
import com.faltdor.recipe.services.impl.IngredientServiceImpl;
import static org.mockito.Mockito.*;

import java.util.Optional;


public class IngredientServiceImplTest {
	
	@Mock
	IRecipeRepository recipeRepository;
	
	IngredientServiceImpl  ingredientServiceImpl;
	
	 private final IngredientToIngredientCommand ingredientToIngredientCommand;
	 
	
	public IngredientServiceImplTest() {
		this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());

	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		ingredientServiceImpl = new IngredientServiceImpl(recipeRepository, ingredientToIngredientCommand);
	}

	@Test
	public void testFindByRecipeIdAndIngredientId() {
		Long recipeId = 2L;
		Long ingredientId = 10L;
		
		//Given
		Recipe recipe = new Recipe();
		recipe.setId(recipeId);
		
		Ingredient ingredient1 = new Ingredient();
		ingredient1.setId(1L);
		recipe.addIngredient(ingredient1 );
		
		Ingredient ingredient2 = new Ingredient();
		ingredient1.setId(2L);
		recipe.addIngredient(ingredient2 );
		
		Ingredient ingredient3 = new Ingredient();
		ingredient1.setId(ingredientId);
		recipe.addIngredient(ingredient3 );
		
		Optional<Recipe> recipeOptional = Optional.of(recipe);
		
		when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
		
		//When
		IngredientCommand ingredientCommand = ingredientServiceImpl.findByRecipeIdAndIngredientId(ingredientId, recipeId);
		
		
		//Then
		assertEquals(ingredientId, ingredientCommand.getId());
		assertEquals(recipeId, ingredientCommand.getRecipeId());
		
		verify(recipeRepository,times(1)).findById(anyLong());
		
	}

}
