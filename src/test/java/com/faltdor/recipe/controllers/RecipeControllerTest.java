package com.faltdor.recipe.controllers;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.faltdor.recipe.domain.Recipe;
import com.faltdor.recipe.services.impl.RecipeServiceImpl;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class RecipeControllerTest {
	
	@Mock
	RecipeServiceImpl recipeService;
	
	RecipeController recipeController;
	

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		recipeController = new RecipeController(recipeService);
	}

	@Test
	public void getRecipeTest() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
		
		when(recipeService.findById(anyLong())).thenReturn(recipe);
		
		mockMvc.perform(get("/recipe/show/1"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("recipe/show"));
				
		
	}

}
