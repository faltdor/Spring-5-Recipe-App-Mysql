package com.faltdor.recipe.controllers;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.faltdor.recipe.commands.IngredientCommand;
import com.faltdor.recipe.commands.RecipeCommand;
import com.faltdor.recipe.services.impl.IngredientServiceImpl;
import com.faltdor.recipe.services.impl.RecipeServiceImpl;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IngredientControllerTest {
	
	@Mock
	RecipeServiceImpl recipeService;
	
	@Mock
	IngredientServiceImpl ingredientService;
	
	IngredientController controller;
	
	MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		controller = new IngredientController(recipeService, ingredientService);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testListIngredients() throws Exception {
		//Given
		RecipeCommand recipeCommand = new RecipeCommand();
		
		when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);
		
		//when
		mockMvc.perform(get("/recipe/1/ingredients"))
						.andExpect(status().isOk())
						.andExpect(view().name("recipe/ingredient/list"))
						.andExpect(model().attributeExists("recipe"));
		
		//then
		verify(recipeService,times(1)).findCommandById(anyLong());
		
	}
	
	@Test
	public void testShowIngredient() throws Exception{
		//Given
		IngredientCommand ingredientCommand = new IngredientCommand();
		
		//When
		when(ingredientService.findByRecipeIdAndIngredientId(anyLong(),anyLong())).thenReturn(ingredientCommand);
		
		mockMvc.perform(get("/recipe/1/ingredient/2/show"))
        .andExpect(status().isOk())
        .andExpect(view().name("recipe/ingredient/show"))
        .andExpect(model().attributeExists("ingredient"));
		
	}

}
