package com.faltdor.recipe.services.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faltdor.recipe.domain.Recipe;
import com.faltdor.recipe.repositories.IRecipeRepository;
import com.faltdor.recipe.services.IRecipeService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RecipeServiceImpl implements IRecipeService {

	private final IRecipeRepository recipeRepository;
	
	@Autowired
	public RecipeServiceImpl(IRecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@Override
	@Transactional
	public Set<Recipe> getRecipes() {
		log.info("Retrieve data from Recipe");
		Set<Recipe> recipeSet = new HashSet<>();
		recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
		return recipeSet;
	}
	
	@Override
	@Transactional
	public Recipe findById(long id) {
		Optional<Recipe> recipeOptional = recipeRepository.findById(id);
		
		if(!recipeOptional.isPresent()) {
			throw new RuntimeException("Recipe Not Found");
		}
		
		return recipeOptional.get();
	}
	
	
	
	
	
}
