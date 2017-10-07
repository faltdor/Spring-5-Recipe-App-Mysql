package com.faltdor.recipe.controllers;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.faltdor.recipe.commands.RecipeCommand;
import com.faltdor.recipe.services.impl.RecipeServiceImpl;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IngredientControllerTest {
	
	@Mock
	RecipeServiceImpl service;
	
	IngredientController controller;
	
	MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		controller = new IngredientController(service);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testListIngredients() throws Exception {
		//Given
		RecipeCommand recipeCommand = new RecipeCommand();
		
		when(service.findCommandById(anyLong())).thenReturn(recipeCommand);
		
		//when
		mockMvc.perform(get("/recipe/1/ingredients"))
						.andExpect(status().isOk())
						.andExpect(view().name("recipe/ingredient/list"))
						.andExpect(model().attributeExists("recipe"));
		
		//then
		verify(service,times(1)).findCommandById(anyLong());
		
	}

}
