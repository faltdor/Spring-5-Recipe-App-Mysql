package com.faltdor.recipe.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.faltdor.recipe.services.impl.RecipeServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Controller
public class IndexController {

	private final RecipeServiceImpl recipeService;
	
	@Autowired
	public IndexController(RecipeServiceImpl recipeService) {
		this.recipeService = recipeService;
	}


	@RequestMapping({"","/","/index"})
	public String getIndexPage(Model model) {
	
		model.addAttribute("recipes", recipeService.getRecipes());	
		
		return "index";
	}
	
}
