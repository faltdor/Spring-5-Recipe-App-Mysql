package com.faltdor.recipe.services.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faltdor.recipe.domain.Recipe;
import com.faltdor.recipe.repositories.IRecipeRepository;
import com.faltdor.recipe.services.IRecipeService;

@Service
public class RecipeServiceImpl implements IRecipeService {

	private final IRecipeRepository recipeRepository;
	
	@Autowired
	public RecipeServiceImpl(IRecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@Override
	public Set<Recipe> getRecipes() {
		Set<Recipe> recipeSet = new HashSet<>();
		recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
		return recipeSet;
	}
	
	
	
	
	
}
