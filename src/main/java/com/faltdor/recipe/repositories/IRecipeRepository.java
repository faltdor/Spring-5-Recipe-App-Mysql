package com.faltdor.recipe.repositories;

import org.springframework.data.repository.CrudRepository;

import com.faltdor.recipe.domain.Recipe;

public interface IRecipeRepository extends CrudRepository<Recipe, Long> {

}
