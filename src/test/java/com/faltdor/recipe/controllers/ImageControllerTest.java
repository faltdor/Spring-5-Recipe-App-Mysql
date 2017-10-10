package com.faltdor.recipe.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.faltdor.recipe.commands.RecipeCommand;
import com.faltdor.recipe.services.impl.ImageServiceImpl;
import com.faltdor.recipe.services.impl.RecipeServiceImpl;

public class ImageControllerTest {

	@Mock
	ImageServiceImpl imageService;

	@Mock
	RecipeServiceImpl recipeService;

	ImageController imageController;

	MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		imageController = new ImageController(imageService, recipeService);
		mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();
	}

	@Test
	public void testHandleImages() throws Exception {
		MockMultipartFile mockMultipartFile = new MockMultipartFile("imagefile", "testing.txt", "text/plain",
				"Spring Framwork".getBytes());

		mockMvc.perform(multipart("/recipe/1/image").file(mockMultipartFile)).andExpect(status().is3xxRedirection())
				.andExpect(header().string("Location", "/recipe/1/show"));
		verify(imageService, times(1)).saveImageFile(anyLong(), any());
	}

	@Test
	public void getImageForm() throws Exception {
		// given
		RecipeCommand command = new RecipeCommand();
		command.setId(1L);

		when(recipeService.findCommandById(anyLong())).thenReturn(command);

		// when
		mockMvc.perform(get("/recipe/1/image")).andExpect(status().isOk()).andExpect(model().attributeExists("recipe"));

		verify(recipeService, times(1)).findCommandById(anyLong());

	}

	@Test
	public void handleImagePost() throws Exception {
		MockMultipartFile multipartFile = new MockMultipartFile("imagefile", "testing.txt", "text/plain",
				"Spring Framework Guru".getBytes());

		mockMvc.perform(multipart("/recipe/1/image").file(multipartFile)).andExpect(status().is3xxRedirection())
				.andExpect(header().string("Location", "/recipe/1/show"));

		verify(imageService, times(1)).saveImageFile(anyLong(), any());
	}

}
