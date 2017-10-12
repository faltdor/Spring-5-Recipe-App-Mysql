package com.faltdor.recipe.controllers;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.faltdor.recipe.commands.RecipeCommand;
import com.faltdor.recipe.services.impl.RecipeServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class RecipeController {
	
	private static final String RECIPE_RECIPEFORM = "recipe/recipeform";
	private final RecipeServiceImpl recipeService;

	public RecipeController(RecipeServiceImpl recipeService) {
		this.recipeService = recipeService;
	}
	
	@GetMapping("/recipe/{id}/show")
	public String showById(@PathVariable String id,Model model) {
		model.addAttribute("recipe", recipeService.findById(new Long(id)));
		
		return "recipe/show";
	}
	
	@GetMapping("/recipe/new")
	public String newRecipe(Model model) {
		
		
		model.addAttribute("recipe",new RecipeCommand() );
		return RECIPE_RECIPEFORM;
		
	}
	
	@GetMapping("/recipe/{id}/update")
	public String updateRecipe(@PathVariable String id,Model model) {
		model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
		
		return RECIPE_RECIPEFORM;
	}
	
	
	@PostMapping("/recipe")
	public String saveOrUpdateRecipe(@Valid @ModelAttribute("recipe") RecipeCommand command , BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach(objectError -> {
				log.debug(objectError.toString());
			});
			return "recipe/recipeform";
		}
		
		
		RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
		
		return "redirect:/recipe/"+savedCommand.getId()+"/show";
	}
	
	@GetMapping("/recipe/{id}/delete")
	public String deleteRecipe(@PathVariable String id ) {
		
		recipeService.deleteById(Long.valueOf(id));
		
		return "redirect:/index";
	}

}
