package com.faltdor.recipe.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.faltdor.recipe.domain.Recipe;
import com.faltdor.recipe.repositories.IRecipeRepository;
import com.faltdor.recipe.services.impl.RecipeServiceImpl;

public class RecipeServiceImplTest {
	
	RecipeServiceImpl recipeService;
	
	@Mock
	IRecipeRepository recipeRepository;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		recipeService = new RecipeServiceImpl(recipeRepository);
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

}
