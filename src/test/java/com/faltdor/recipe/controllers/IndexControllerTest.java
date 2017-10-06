package com.faltdor.recipe.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.faltdor.recipe.controller.IndexController;
import com.faltdor.recipe.domain.Recipe;
import com.faltdor.recipe.services.impl.RecipeServiceImpl;
import static org.mockito.Mockito.*;

public class IndexControllerTest {
	
	@Mock
	RecipeServiceImpl recipeService;
	
	@Mock
	Model model;
	
	IndexController controller;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		controller = new IndexController(recipeService);
	}

	@Test
	public void testGetIndexPage() {
		
		//Given
		Set<Recipe> recipes = new HashSet<>();
		recipes.add(new Recipe());
		Recipe secondSecipe = new Recipe();
		secondSecipe.setId(4L);
		recipes.add(secondSecipe);
		
		//When
		when(recipeService.getRecipes()).thenReturn(recipes);
		ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
		
		String viewName = controller.getIndexPage(model);
		
		//Then
		assertEquals("index", viewName);		
		verify(recipeService,times(1)).getRecipes();
		verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
		Set<Recipe> setInController = argumentCaptor.getValue();
		assertEquals(2, setInController.size());
		
	}

}
