package com.faltdor.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.faltdor.recipe.commands.IngredientCommand;
import com.faltdor.recipe.services.impl.IngredientServiceImpl;
import com.faltdor.recipe.services.impl.RecipeServiceImpl;
import com.faltdor.recipe.services.impl.UnitOfMeasureService;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class IngredientController {
	
	private final RecipeServiceImpl recipeService;
	
	private final IngredientServiceImpl ingredientService;
	
	private final UnitOfMeasureService uoMeasureService;
	
	public IngredientController(RecipeServiceImpl recipeService,
								IngredientServiceImpl ingredientService,
								UnitOfMeasureService uoMeasureService) {
		this.recipeService = recipeService;
		this.ingredientService = ingredientService;
		this.uoMeasureService = uoMeasureService;
	}
	
	@GetMapping
	@RequestMapping("/recipe/{recipeId}/ingredients")
	public String listIngredients(@PathVariable String recipeId,Model model) {
		log.debug("Gettitg Ingredient list for recipe id"+recipeId);
		
		model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(recipeId)));
		
		return "recipe/ingredient/list";
	}
	
	@GetMapping
	@RequestMapping("recipe/{recipeId}/ingredient/{ingredientId}/show")
	public String showIngredient(@PathVariable String recipeId,
								@PathVariable String ingredientId,Model model) {
		
		model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(ingredientId), Long.valueOf(recipeId)));
		
		return "recipe/ingredient/show";
		
	}
	
	@GetMapping
	@RequestMapping("recipe/{recipeId}/ingredient/{ingredientId}/update")
	public String  updateRecipeIngredient(@PathVariable String recipeId,
										  @PathVariable String ingredientId,Model model) {
		
		model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(ingredientId), Long.valueOf(recipeId)));
		model.addAttribute("uomList", uoMeasureService.findAll());
		return "recipe/ingredient/ingredientform";
	}
	
	@GetMapping
	@RequestMapping("recipe/{recipeId}/ingredient")
	public String saveOrUpdate(@ModelAttribute IngredientCommand ingredient) {
		
		IngredientCommand saveIngredient = ingredientService.saveIngredient(ingredient);
		
		return "redirect:/recipe/"+saveIngredient.getRecipeId()+"/ingredient/"+saveIngredient.getId()+"/show";
	}
	
	

}
