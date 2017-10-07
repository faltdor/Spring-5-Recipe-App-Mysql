package com.faltdor.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.faltdor.recipe.services.impl.RecipeServiceImpl;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class IngredientController {
	
	private final RecipeServiceImpl recipeService;
	
	public IngredientController(RecipeServiceImpl service) {
		this.recipeService = service;
	}
	
	@GetMapping
	@RequestMapping("/recipe/{recipeId}/ingredients")
	public String listIngredients(@PathVariable String recipeId,Model model) {
		log.debug("Gettitg Ingredient list for recipe id"+recipeId);
		
		model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(recipeId)));
		
		return "recipe/ingredient/list";
	}

}
