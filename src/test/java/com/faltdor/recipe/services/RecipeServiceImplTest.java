package com.faltdor.recipe.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.faltdor.recipe.converters.RecipeCommandToRecipe;
import com.faltdor.recipe.converters.RecipeToRecipeCommand;
import com.faltdor.recipe.domain.Recipe;
import com.faltdor.recipe.exceptions.NotFoundException;
import com.faltdor.recipe.repositories.IRecipeRepository;
import com.faltdor.recipe.services.impl.RecipeServiceImpl;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {
	
	RecipeServiceImpl recipeService;
	
	@Mock
	IRecipeRepository recipeRepository;
	@Mock
	RecipeCommandToRecipe recipeCommandToRecipe;
	@Mock
	RecipeToRecipeCommand recipeToRecipeCommand;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		recipeService = new RecipeServiceImpl(recipeRepository,recipeCommandToRecipe,recipeToRecipeCommand);
	}
	
	@Test
	public void testGetRecipes() {
		Recipe recipe = new Recipe();		
		HashSet<Recipe> recipieData = new HashSet<>();
		recipieData.add(recipe);
		
		when(recipeService.getRecipes()).thenReturn(recipieData);
		
		Set<Recipe> recipies = recipeService.getRecipes();
		
		assertEquals(recipies.size(), 1);
		verify(recipeRepository, times(1)).findAll();
	}
	
	
	@Test
	public void getRecipeByIdTest() {
		Recipe recipe = new Recipe();
		Optional<Recipe> recipeOptional = Optional.of(recipe);
		
		when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
		
		Recipe recipeReturned = recipeService.findById(1L);
		
		assertNotNull("Null recipe",recipeReturned);
		verify(recipeRepository,times(1)).findById(anyLong());
		verify(recipeRepository, never()).findAll();
		
	}
	
	@Test 
	public void deleteRecipeTest() {
		//Given
		Long id = Long.valueOf(2L);
		//When
		recipeService.deleteById(id);
		//Then
		verify(recipeRepository,times(1)).deleteById(anyLong());
	}
	
	@Test(expected=NotFoundException.class)
	public void testGetRecipeByIdTestNotFound() throws Exception {
		Optional<Recipe> recipeOptiona = Optional.empty();
		
		when(recipeRepository.findById(anyLong())).thenReturn(recipeOptiona);
		
		Recipe recipe = recipeService.findById(1L);
	}
	
	
	
}
