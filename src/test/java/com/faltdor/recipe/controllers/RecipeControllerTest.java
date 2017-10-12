package com.faltdor.recipe.controllers;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.faltdor.recipe.commands.RecipeCommand;
import com.faltdor.recipe.domain.Recipe;
import com.faltdor.recipe.exceptions.NotFoundException;
import com.faltdor.recipe.services.impl.RecipeServiceImpl;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


public class RecipeControllerTest {
	
	@Mock
	RecipeServiceImpl recipeService;
	
	RecipeController recipeController;
	
	MockMvc mockMvc ;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		recipeController = new RecipeController(recipeService);
		mockMvc = MockMvcBuilders.standaloneSetup(recipeController)
				.setControllerAdvice(new ControllerExceptionHandler())
				 .build();
	}

	@Test
	public void testGetRecipe() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		
		when(recipeService.findById(anyLong())).thenReturn(recipe);
		
		mockMvc.perform(get("/recipe/1/show"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("recipe/show"));
	}
	
	@Test
	public void testNewRecipe() throws Exception {
		//Given
		 RecipeCommand recipeCommand = new RecipeCommand();
		 recipeCommand.setId(2L);
		 
		 when(recipeService.saveRecipeCommand(any())).thenReturn(recipeCommand);
		 
		 
		 mockMvc.perform(post("/recipe")
				 		.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				 		.param("id", "")
				 		.param("description", "Some Description"))
				 		.andExpect(status().is3xxRedirection())
				 		.andExpect(view().name("redirect:/recipe/2/show"));
		
	}
	
	@Test
	public void testSaveOrUpdateRecipe() throws Exception {
		//Given
		RecipeCommand command = new RecipeCommand();
		command.setId(2L);
		
		when(recipeService.findCommandById(anyLong())).thenReturn(command);
		
		//Then
		mockMvc.perform(get("/recipe/1/update"))
					    .andExpect(status().isOk())
					    .andExpect(view().name("recipe/recipeform"))
					    .andExpect(model().attributeExists("recipe"));
	}
	
	@Test
	public void testDeleteRecipe() throws Exception {
		//Given
		
		//when
		
		//Then
		mockMvc.perform(get("/recipe/1/delete"))
						.andExpect(status().is3xxRedirection())
				 		.andExpect(view().name("redirect:/index"))
					    ;
		
		verify(recipeService,times(1)).deleteById(anyLong());
	}
	
	@Test
	public void testGetRecipeNotFound() throws Exception {
		
		when(recipeService.findById(anyLong())).thenThrow(NotFoundException.class);
		
		mockMvc.perform(get("/recipe/1/show"))
		.andExpect(status().isNotFound())
		.andExpect(view().name("404error"));
	}
	
	@Test
	public void testGetRecipeNumberFormat() throws Exception {
		
		//when(recipeService.findById(anyLong())).thenThrow(NotFoundException.class);
		
		mockMvc.perform(get("/recipe/aaaa/show"))
		.andExpect(status().isBadRequest())
		.andExpect(view().name("400error"));
	}
	
}
