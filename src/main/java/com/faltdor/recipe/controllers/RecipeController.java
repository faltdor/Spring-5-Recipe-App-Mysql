package com.faltdor.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.faltdor.recipe.commands.RecipeCommand;
import com.faltdor.recipe.services.impl.RecipeServiceImpl;

@Controller
public class RecipeController {
	
	private final RecipeServiceImpl recipeService;

	public RecipeController(RecipeServiceImpl recipeService) {
		this.recipeService = recipeService;
	}
	
	@RequestMapping("/recipe/{id}/show")
	public String showById(@PathVariable String id,Model model) {
		model.addAttribute("recipe", recipeService.findById(new Long(id)));
		
		return "recipe/show";
	}
	
	@RequestMapping("/recipe/new")
	public String newRecipe(Model model) {
		
		
		model.addAttribute("recipe",new RecipeCommand() );
		return "recipe/recipeform";
		
	}
	
	@RequestMapping("/recipe/{id}/update")
	public String updateRecipe(@PathVariable String id,Model model) {
		model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
		
		return "recipe/recipeform";
	}
	
	@PostMapping
	@RequestMapping("/recipe")
	public String saveOrUpdateRecipe(@ModelAttribute RecipeCommand command ) {
		
		RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
		
		return "redirect:/recipe/"+savedCommand.getId()+"/show";
	}
	
	@PostMapping
	@RequestMapping("/recipe/{id}/delete")
	public String deleteRecipe(@PathVariable String id ) {
		
		recipeService.deleteById(Long.valueOf(id));
		
		return "redirect:/index";
	}
	

}
